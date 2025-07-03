package ru.mirea.sladkinaaa.mireaproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileWorkFragment extends Fragment {

    private LinearLayout filesContainer;

    public FileWorkFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filework, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        filesContainer = view.findViewById(R.id.filesContainer);
        FloatingActionButton fab = view.findViewById(R.id.fabAddFile);

        loadFiles();

        fab.setOnClickListener(v -> {
            new FileDialogFragment(this::saveEncryptedFile).show(getParentFragmentManager(), "file_dialog");
        });
    }

    private void loadFiles() {
        filesContainer.removeAllViews();
        File dir = requireContext().getFilesDir();
        for (File file : dir.listFiles()) {
            TextView tv = new TextView(getContext());
            tv.setText(file.getName());
            tv.setTextSize(18);
            tv.setPadding(8, 8, 8, 8);
            tv.setOnClickListener(v -> {
                String content = readDecryptedFile(file.getName());
                tv.setText(file.getName() + "\n" + content);
            });
            filesContainer.addView(tv);
        }
    }
    private void saveEncryptedFile(String fileName, String plainText) {
        try {
            String encrypted = Base64.encodeToString(plainText.getBytes(), Base64.DEFAULT);
            FileOutputStream fos = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(encrypted.getBytes());
            fos.close();
            loadFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String readDecryptedFile(String fileName) {
        try {
            FileInputStream fis = requireContext().openFileInput(fileName);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            return new String(Base64.decode(bytes, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка чтения";
        }
    }
}
