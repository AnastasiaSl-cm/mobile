package ru.mirea.sladkinaaa.audiorecord;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

import ru.mirea.sladkinaaa.audiorecord.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final int REQUEST_CODE_PERMISSION = 200;
    private boolean isWork = false;
    private MediaRecorder recorder = null;
    private MediaPlayer player = null;
    private String fileName = null;
    private boolean isStartRecording = true;
    private boolean isStartPlaying = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fileName = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "audiorecordtest.3gp").getAbsolutePath();
        int permissionRecord = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int permissionStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionRecord == PackageManager.PERMISSION_GRANTED && permissionStorage == PackageManager.PERMISSION_GRANTED) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION);
        }
        binding.playButton.setEnabled(false);

        binding.recordButton.setOnClickListener(v -> {
            if (!isWork) {
                Toast.makeText(MainActivity.this, "Нет разрешений!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isStartRecording) {
                startRecording();
                binding.recordButton.setText("Остановить запись");
                binding.playButton.setEnabled(false);
            } else {
                stopRecording();
                binding.recordButton.setText("Начать запись. № студента, группа");
                binding.playButton.setEnabled(true);
            }
            isStartRecording = !isStartRecording;
        });

        binding.playButton.setOnClickListener(v -> {
            if (isStartPlaying) {
                startPlaying();
                binding.playButton.setText("Остановить воспроизведение");
                binding.recordButton.setEnabled(false);
            } else {
                stopPlaying();
                binding.playButton.setText("Воспроизвести");
                binding.recordButton.setEnabled(true);
            }
            isStartPlaying = !isStartPlaying;
        });
    }
    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(this, "Запись началась", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка при записи", Toast.LENGTH_SHORT).show();
        }
    }
    private void stopRecording() {
        if (recorder != null) {
            try {
                recorder.stop();
            } catch (RuntimeException stopException) {
                stopException.printStackTrace();
            }
            recorder.release();
            recorder = null;
            Toast.makeText(this, "Запись остановлена", Toast.LENGTH_SHORT).show();
        }
    }
    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
            Toast.makeText(this, "Воспроизведение началось", Toast.LENGTH_SHORT).show();

            player.setOnCompletionListener(mp -> {
                binding.playButton.setText("Воспроизвести");
                binding.recordButton.setEnabled(true);
                isStartPlaying = true;
                player.release();
                player = null;
            });

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка воспроизведения", Toast.LENGTH_SHORT).show();
        }
    }
    private void stopPlaying() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
            Toast.makeText(this, "Воспроизведение остановлено", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED;

            if (!isWork) {
                Toast.makeText(this, "Разрешения не предоставлены, приложение закрывается", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
