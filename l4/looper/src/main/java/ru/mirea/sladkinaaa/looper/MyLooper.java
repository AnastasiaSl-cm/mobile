package ru.mirea.sladkinaaa.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {

    public Handler handler;

    @Override
    public void run() {
        Looper.prepare();

        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                int age = bundle.getInt("age");
                String job = bundle.getString("job");

                try {
                    Thread.sleep(age * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d("MyLooper", "Возраст: " + age + ", Работа: " + job + " (обработка завершена)");
            }
        };

        Looper.loop();
    }
}
