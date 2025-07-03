package ru.mirea.sladkinaaa.systemintentsapp;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCall = findViewById(R.id.btnCall);
        Button btnOpenBrowser = findViewById(R.id.btnOpenBrowser);
        Button btnOpenMap = findViewById(R.id.btnOpenMap);

        btnCall.setOnClickListener(v -> onClickCall());
        btnOpenBrowser.setOnClickListener(v -> onClickOpenBrowser());
        btnOpenMap.setOnClickListener(v -> onClickOpenMaps());
    }
    public void onClickCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:89811112233"));
        startActivity(intent);
    }
    public void onClickOpenBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://developer.android.com"));
        startActivity(intent);
    }
    public void onClickOpenMaps() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:55.749479,37.613944"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/@55.749479,37.613944,15z"));
            startActivity(browserIntent);
        }
    }
}