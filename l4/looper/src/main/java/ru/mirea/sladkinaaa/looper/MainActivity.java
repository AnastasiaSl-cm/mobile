package ru.mirea.sladkinaaa.looper;
import android.os.Bundle;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private MyLooper myLooper;
    private EditText editTextAge;
    private EditText editTextJob;
    private Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAge = findViewById(R.id.editTextAge);
        editTextJob = findViewById(R.id.editTextJob);
        buttonSend = findViewById(R.id.buttonSend);
        myLooper = new MyLooper();
        myLooper.start();
        buttonSend.setOnClickListener(v -> {
            String ageStr = editTextAge.getText().toString().trim();
            String job = editTextJob.getText().toString().trim();
            if (ageStr.isEmpty() || job.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, введите возраст и работу", Toast.LENGTH_SHORT).show();
                return;
            }
            int age;
            try {
                age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    Toast.makeText(this, "Возраст должен быть положительным числом", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введите корректный возраст", Toast.LENGTH_SHORT).show();
                return;
            }

            if (myLooper.handler != null) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("age", age);
                bundle.putString("job", job);
                msg.setData(bundle);

                myLooper.handler.sendMessage(msg);

                Log.d("MainActivity", "Отправлено сообщение: возраст = " + age + ", работа = " + job);
                Toast.makeText(this, "Сообщение отправлено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Обработчик еще не готов, попробуйте позже", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
