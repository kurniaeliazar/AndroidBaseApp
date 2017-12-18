package example.kurnia.storageapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtStorage;
    EditText inputStorage;
    Button btnSave;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStorage = findViewById(R.id.txtStorage);
        inputStorage = findViewById(R.id.inputText);
        btnSave = findViewById(R.id.btnSave);

        sharedPref = getApplicationContext().getSharedPreferences("storage", getApplicationContext().MODE_PRIVATE);
        editor = sharedPref.edit();

        String saveText = sharedPref.getString("title", "Storage Text?");
        txtStorage.setText(saveText);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtStorage.setText(inputStorage.getText().toString());
                editor.putString("title", inputStorage.getText().toString());
                editor.commit();
            }
        });

    }
}
