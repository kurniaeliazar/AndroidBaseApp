package com.kurniaeliazar.androidmqttexample;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kurniaeliazar.androidmqttexample.util.MqttConnector;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MQTTExample";
    private TextView subscribeMessage;
    private EditText publishMessage;
    private Button sendMessage, connectButton;
    private MqttConnector mqttConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subscribeMessage = (TextView) findViewById(R.id.submessage);
        publishMessage = (EditText) findViewById(R.id.editText);
        sendMessage = (Button) findViewById(R.id.button);
        connectButton = (Button) findViewById(R.id.connect);

        mqttConnector = new MqttConnector(MainActivity.this);
        connectButton.setBackgroundColor(Color.GREEN);

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Please wait")
                .content("Connecting to Server")
                .progress(true, 0);

        final MaterialDialog progressDialog = builder.showListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                mqttConnector.connect(10000, new MqttConnector.MqttConnectedCallBack() {
                    @Override
                    public void onSuccess() {
                        connectButton.setText("Disconnect");
                        connectButton.setBackgroundColor(Color.RED);
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure() {
                        connectButton.setText("Connect");
                        connectButton.setBackgroundColor(Color.GREEN);
                        dialog.dismiss();
                    }
                });
            }
        }).build();

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
            }
        });

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mqttConnector.sendMessage(publishMessage.getText().toString());
            }
        });
    }

}
