package kurniaeliazar.datastorageproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kurniaeliazar.datastorageproject.data.HighScoreData;
import kurniaeliazar.datastorageproject.helper.SharedPreferencesHelper;

public class MainActivity extends AppCompatActivity {

    TextView txtUsername, txtScore;
    EditText editUser, editScore;
    Button btnStore, btnClear;
    SharedPreferencesHelper sharedPreferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesHelper = new SharedPreferencesHelper(getBaseContext());

        txtUsername = (TextView) findViewById(R.id.username);
        txtScore = (TextView) findViewById(R.id.score);

        editUser = (EditText) findViewById(R.id.editUsername);
        editScore = (EditText) findViewById(R.id.editScore);

        btnStore = (Button) findViewById(R.id.btnStore);
        btnClear = (Button) findViewById(R.id.btnClearStored);

        refreshData();

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "onClick: store");
                HighScoreData storeData = new HighScoreData();
                storeData.setUsername(editUser.getText().toString());
                storeData.setScore(Integer.parseInt(editScore.getText().toString()));
                sharedPreferencesHelper.storeHighscoreData(storeData);
                refreshData();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesHelper.removeHighscoreData();
            }
        });
    }

    public void refreshData(){
        HighScoreData highScoreData = sharedPreferencesHelper.getStoredHighscore();
        txtUsername.setText(highScoreData.getUsername());
        txtScore.setText(String.valueOf(highScoreData.getScore()));
    }
}
