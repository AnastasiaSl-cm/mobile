package ru.mirea.sladkinaaa.simplefragmentapp;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowInsets;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
public class MainActivity extends AppCompatActivity {
    private Fragment fragment1, fragment2;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                v.setPadding(
                        insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                        insets.getInsets(WindowInsets.Type.systemBars()).top,
                        insets.getInsets(WindowInsets.Type.systemBars()).right,
                        insets.getInsets(WindowInsets.Type.systemBars()).bottom
                );
            }
            return insets;
        });
        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment1).commit(); // показать фрагмент по умолчанию

        Button btnFirstFragment = findViewById(R.id.btnFirstFragment);
        btnFirstFragment.setOnClickListener(v ->
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment1).commit()
        );

        Button btnSecondFragment = findViewById(R.id.btnSecondFragment);
        btnSecondFragment.setOnClickListener(v ->
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment2).commit()
        );
    }
}
