package com.mqttrobot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements MqttCallback {

    Button btnUp, btnDown, btnLeft,
            btnRight, btnConnect, btnDisconnect,
            btnSubscribe, btnUnsubscribe;

    TextView txtStatus, txtSubscribeInput;
    EditText txtServer, txtTopic;

    private static MqttAndroidClient mClient;
    private String mTopic = "ledStatus";
    private String mQTTBroker = "tcp://192.168.3.14:1883";
    private static boolean ISCONNECTED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUp = (Button) findViewById(R.id.btnUp);
        btnDown = (Button) findViewById(R.id.btnDown);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
        btnSubscribe = (Button) findViewById(R.id.btnSubscribe);
        btnUnsubscribe = (Button) findViewById(R.id.btnUnsubscribe);

        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtServer = (EditText) findViewById(R.id.txtServer);
        txtTopic = (EditText) findViewById(R.id.txtTopic);
        txtSubscribeInput = (TextView) findViewById(R.id.txtSubscribe);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(txtTopic.getText().toString(),"0");
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(txtTopic.getText().toString(),"1");
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(txtTopic.getText().toString(), "2");
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(txtTopic.getText().toString(), "3");
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQTTBroker = "tcp://"+txtServer.getText().toString();
                mTopic = txtTopic.getText().toString();
                Log.d("MQTTROBOT", "mQTTBroker: " + mQTTBroker);
                connect();
            }
        });

        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTopic = txtTopic.getText().toString();
                subscribe(mClient, mTopic);
            }
        });

        btnUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTopic = txtTopic.getText().toString();
                unsubscribe(mClient, mTopic);
            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disconnect();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void  connect(){
        Log.d("MQTTROBOTLOG", "connect() ");
        //final CountDownLatch countDownLatch = new CountDownLatch(1);
        String clientId = MqttClient.generateClientId();
        Log.d("MQTTROBOTLOG", clientId);
        mClient = new MqttAndroidClient(getApplicationContext(), mQTTBroker, clientId);
        mClient.setCallback(this);

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);

        try {
            mClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //countDownLatch.countDown();
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mClient.setBufferOpts(disconnectedBufferOptions);
                    txtStatus.setText("Connected");
                    Log.d("MQTTROBOTLOG", "connected");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    //countDownLatch.countDown();
                    txtStatus.setText("Error to Connect device");
                    Log.d("MQTTROBOTLOG", "Error to Connect device");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

//        try {
//            countDownLatch.await(10000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//           e.printStackTrace();
//        }
    }

    private void  disconnect(){
        Log.d("MQTTROBOTLOG", "disconnect() ");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            IMqttToken token = mClient.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    countDownLatch.countDown();
                    txtStatus.setText("Disconnected");
                    Log.d("MQTTROBOTLOG", "diconnected");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    countDownLatch.countDown();
                    txtStatus.setText("Error to Connect device");
                    Log.d("MQTTROBOTLOG", "Error to Connect device");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        try {
            countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void subscribe(MqttAndroidClient mClient, final String topic){
        IMqttToken subToken = null;
        try {
            subToken = mClient.subscribe(topic, 1);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //txtStatus.setText("Subsctibe Success");
                    Toast.makeText(getApplicationContext(), "Subsctibe " + topic + " Success",
                            Toast.LENGTH_LONG ).show();
                    Log.d("MQTTROBOTLOG", "Subsctibe " + topic + " Success");
                    ISCONNECTED = true;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    //txtStatus.setText("Subsctibe" + topic + "Failed");
                    Log.d("MQTTROBOTLOG", "Subsctibe" + topic + "Failed");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void unsubscribe(MqttAndroidClient mClient, final String topic){
        IMqttToken subToken = null;
        try {
            subToken = mClient.unsubscribe(topic);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //txtStatus.setText("Subsctibe Success");
                    Toast.makeText(getApplicationContext(), "Unsubsctibe " + topic + " Success",
                            Toast.LENGTH_LONG ).show();
                    Log.d("MQTTROBOTLOG", "Unsubsctibe " + topic + " Success");
                    ISCONNECTED = true;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    txtStatus.setText("Subsctibe Failed");
                    Log.d("MQTTROBOTLOG", "Subsctibe Failed");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String TopicMessage, String message){

        Log.d("MQTTRobot", "TopicMessage : " + TopicMessage);
        Log.d("MQTTRobot", "message : " + message);
        Log.d("MQTTRobot", "ISCONNECTED : " + ISCONNECTED);

        if(!ISCONNECTED) return;
        byte[] encodedPayload;
        try {
            encodedPayload = message.getBytes("UTF-8");
            MqttMessage mqttMessage = new MqttMessage(encodedPayload);
            mClient.publish(TopicMessage, mqttMessage);

        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        txtSubscribeInput.setText(message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
