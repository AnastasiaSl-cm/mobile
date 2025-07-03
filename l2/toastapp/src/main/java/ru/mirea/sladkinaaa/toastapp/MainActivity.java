package ru.mirea.sladkinaaa.toastapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editStudentNumber, editGroupNumber, editTextInput;
    private Button buttonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editStudentNumber = findViewById(R.id.editStudentNumber);
        editGroupNumber = findViewById(R.id.editGroupNumber);
        editTextInput = findViewById(R.id.editTextInput);
        buttonCount = findViewById(R.id.buttonCount);

        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentNum = editStudentNumber.getText().toString().trim();
                String groupNum = editGroupNumber.getText().toString().trim();
                String inputText = editTextInput.getText().toString();

                if (TextUtils.isEmpty(studentNum) || TextUtils.isEmpty(groupNum)) {
                    Toast.makeText(getApplicationContext(),
                            "Пожалуйста, введите номер студента и группы",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int length = inputText.length();
                String message = "СТУДЕНТ № " + studentNum + " ГРУППА " + groupNum + " Количество символов - " + length;
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
