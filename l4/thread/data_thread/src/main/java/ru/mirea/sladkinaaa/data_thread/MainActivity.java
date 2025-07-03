package ru.mirea.sladkinaaa.data_thread;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private TextView textViewLog;
    private Handler handler = new Handler();
    private void log(String msg) {
        textViewLog.append(msg + "\n");
        Log.d("THREAD_LOG", msg);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewLog = findViewById(R.id.textViewLog);
        Button buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("Кнопка нажата. Начало последовательности");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        log("1. handler.post() → выполняется сразу в очереди UI потока");
                    }
                });
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        log("2. handler.postDelayed() → выполняется с задержкой 2 сек");
                    }
                }, 2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        log("3. runOnUiThread() → помещено в UI очередь (приоритетно)");
                    }
                });
                log("Код вне потоков выполнен сразу");
            }
        });
    }
}
