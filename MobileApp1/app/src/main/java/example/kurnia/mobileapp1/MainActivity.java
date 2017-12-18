package example.kurnia.mobileapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import example.kurnia.mobileapp1.AccessingAPI.APIAccessActivity;
import example.kurnia.mobileapp1.SavingData.StoringDataActivity;

public class MainActivity extends AppCompatActivity {

    Button btnSavingData;
    Button btnRequestAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSavingData = findViewById(R.id.DataStoreBtn);
        btnRequestAPI = findViewById(R.id.RestApiBtn);
        final Intent savingIntent = new Intent(this, StoringDataActivity.class);
        final Intent requestIntent = new Intent(this, APIAccessActivity.class);

        btnSavingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(savingIntent);
            }
        });
        btnRequestAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(requestIntent);
            }
        });
    }
}
