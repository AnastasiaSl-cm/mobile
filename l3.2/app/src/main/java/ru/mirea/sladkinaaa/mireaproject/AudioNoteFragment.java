package ru.mirea.sladkinaaa.mireaproject;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
public class AudioNoteFragment extends Fragment {
    private MediaRecorder mediaRecorder;
    private String fileName;
    private boolean isRecording = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_note, container, false);
        Button recordButton = view.findViewById(R.id.recordButton);
        Button playButton = view.findViewById(R.id.playButton);
        fileName = requireContext().getExternalCacheDir().getAbsolutePath() + "/audiorecord.3gp";
        recordButton.setOnClickListener(v -> {
            if (!isRecording) {
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(fileName);
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    isRecording = true;
                    recordButton.setText("Остановить");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                isRecording = false;
                recordButton.setText("Записать");
                Toast.makeText(getContext(), "Аудио сохранено", Toast.LENGTH_SHORT).show();
            }
        });
        playButton.setOnClickListener(v -> {
            MediaPlayer player = new MediaPlayer();
            try {
                player.setDataSource(fileName);
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return view;
    }
}
