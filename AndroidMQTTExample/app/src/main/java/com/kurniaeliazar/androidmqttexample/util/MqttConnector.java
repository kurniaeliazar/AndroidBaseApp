package com.kurniaeliazar.androidmqttexample.util;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by kurniaeliazar on 12/23/16.
 */

public class MqttConnector {
    private static final String TAG = "MqttConnector";
    private final Context mContext;
    private static MqttAndroidClient mClient;

    //fill the topic
    private String mTopic = "......";

    //fill the tcp server
    private String mQTTBroker = "tcp://.....";

    private static boolean mConnected = false;

    public interface MqttConnectedCallBack {
        void onSuccess();
        void onFailure();
    }

    public MqttConnector(Context context){
        mContext = context;
    }

    public void connectAsync(IMqttActionListener iMqttActionListener){
        String clientId = MqttClient.generateClientId();
        mClient = new MqttAndroidClient(mContext.getApplicationContext(), mQTTBroker, clientId);

        try {
            IMqttToken token = mClient.connect();
            token.setActionCallback(iMqttActionListener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(final MqttConnectedCallBack mqttConnectedCallBack){
        try {
            IMqttToken token = mClient.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    mConnected = false;
                    Log.d(TAG, "disconnect onSuccess");
                    mqttConnectedCallBack.onSuccess();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "disconnect onFailure");
                    mqttConnectedCallBack.onSuccess();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public boolean connect(long waitMs, final MqttConnectedCallBack mqttConnectedCallBack){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] result = new boolean[1];
        result[0] = false;
        connectAsync(
                new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        // We are connected
                        result[0] = true;
                        countDownLatch.countDown();
                        Log.d(TAG, "Connect onSuccess");
                        mqttConnectedCallBack.onSuccess();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        // Something went wrong e.g. connection timeout or firewall problems
                        result[0] = false;
                        countDownLatch.countDown();
                        Log.d(TAG, "Connect onFailure");
                        mqttConnectedCallBack.onFailure();
                    }
                });


        try {
            countDownLatch.await(waitMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.e(TAG, "Error waiting for result", e);
        }
        mConnected = result[0];

        return result[0];
    }

    public void sendMessage(String message){
        if(!isConnected()) return;
        byte[] encodedPayload;
        try {
            Log.e(TAG, "Sending message: "+message);
            encodedPayload = message.getBytes("UTF-8");
            MqttMessage mqttMessage = new MqttMessage(encodedPayload);
            mClient.publish(mTopic, mqttMessage);
        } catch (UnsupportedEncodingException | MqttException e) {
            Log.e(TAG, "Error sending message", e);
        }
    }

    public boolean isConnected() {
        return mConnected;
    }
}
