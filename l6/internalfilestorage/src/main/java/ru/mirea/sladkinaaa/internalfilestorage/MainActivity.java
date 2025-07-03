package ru.mirea.sladkinaaa.internalfilestorage;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private EditText editTextData;
    private TextView textViewOutput;
    private static final String FILE_NAME = "russian_history.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextData = findViewById(R.id.editTextData);
        textViewOutput = findViewById(R.id.textViewOutput);
        Button buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(v -> {
            String data = editTextData.getText().toString();
            writeToFile(data);
            readFromFileInThread();
        });
    }

    private void writeToFile(String data) {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFileInThread() {
        new Thread(() -> {
            StringBuilder builder = new StringBuilder();
            try (FileInputStream fis = openFileInput(FILE_NAME)) {
                InputStream inputStream = new BufferedInputStream(fis);
                int byteData;
                while ((byteData = inputStream.read()) != -1) {
                    builder.append((char) byteData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> textViewOutput.setText(builder.toString()));
        }).start();
    }
}
