package ru.mirea.sladkinaaa.mireaproject;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class FileDialogFragment extends DialogFragment {

    public interface OnFileSavedListener {
        void onFileSaved(String fileName, String content);
    }

    private final OnFileSavedListener listener;

    public FileDialogFragment(OnFileSavedListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditText inputText = new EditText(getContext());
        inputText.setHint("Введите текст");
        EditText inputFile = new EditText(getContext());
        inputFile.setHint("Имя файла");
        inputFile.setInputType(InputType.TYPE_CLASS_TEXT);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        layout.addView(inputFile);
        layout.addView(inputText);

        return new AlertDialog.Builder(requireContext())
                .setTitle("Создать зашифрованный файл")
                .setView(layout)
                .setPositiveButton("Сохранить", (dialog, which) -> {
                    String name = inputFile.getText().toString();
                    String text = inputText.getText().toString();
                    if (!name.isEmpty() && !text.isEmpty()) {
                        listener.onFileSaved(name, text);
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
