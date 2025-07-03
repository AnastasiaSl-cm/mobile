package ru.mirea.sladkinaaa.lesson6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextGroup, editTextNumber, editTextMovie;
    private static final String PREF_NAME = "mirea_settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGroup = findViewById(R.id.editTextGroup);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextMovie = findViewById(R.id.editTextMovie);
        Button buttonSave = findViewById(R.id.buttonSave);
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        editTextGroup.setText(preferences.getString("group", ""));
        editTextNumber.setText(preferences.getString("number", ""));
        editTextMovie.setText(preferences.getString("movie", ""));

        buttonSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("group", editTextGroup.getText().toString());
            editor.putString("number", editTextNumber.getText().toString());
            editor.putString("movie", editTextMovie.getText().toString());
            editor.apply();
        });
    }



}
