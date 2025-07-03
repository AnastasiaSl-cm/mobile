package ru.mirea.sladkinaaa.sharer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shareText("Mirea");
        pickFile();
    }
    private void shareText(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, "Выбор за вами!"));
    }
    private void pickFile() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("*/*");

        ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                    }
                });
        filePickerLauncher.launch(intent);
    }
    public void onShareTextClick(View view) {
        shareText("Пример текста для отправки");
    }
    public void onPickFileClick(View view) {
        pickFile();
    }
}

