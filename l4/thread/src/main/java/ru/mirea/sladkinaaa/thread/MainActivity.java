package ru.mirea.sladkinaaa.thread;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.sladkinaaa.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.textViewInfo.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЯ ГРУППА: XX, НОМЕР: XX, ФИЛЬМ: XX");
        binding.textViewInfo.append("\nНовое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + android.util.Log.getStackTraceString(new Throwable()));

        binding.buttonCalculate.setOnClickListener(view -> {
            int lessons = Integer.parseInt(binding.editTextLessons.getText().toString());
            int days = Integer.parseInt(binding.editTextDays.getText().toString());

            Thread backgroundThread = new Thread(() -> {
                double avg = (double) lessons / days;
                runOnUiThread(() ->
                        binding.textViewInfo.setText("Среднее количество пар в день: " + avg));
            });
            backgroundThread.start();
        });
    }

}
