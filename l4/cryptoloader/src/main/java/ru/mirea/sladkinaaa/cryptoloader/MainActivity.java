package ru.mirea.sladkinaaa.cryptoloader;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;


import androidx.loader.content.AsyncTaskLoader;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText editTextInput;
    private Button buttonEncrypt;

    private static final int LOADER_ID = 101;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        buttonEncrypt = findViewById(R.id.buttonEncrypt);

        buttonEncrypt.setOnClickListener(v -> {
            String text = editTextInput.getText().toString();
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show();
                return;
            }

            try {

                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(128, new SecureRandom());
                SecretKey secretKey = keyGen.generateKey();


                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedBytes = cipher.doFinal(text.getBytes());


                bundle = new Bundle();
                bundle.putString("encryptedText", Base64.encodeToString(encryptedBytes, Base64.DEFAULT));
                bundle.putString("key", Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT));


                LoaderManager.getInstance(this).restartLoader(LOADER_ID, bundle, this);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Ошибка шифрования", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public androidx.loader.content.Loader<String> onCreateLoader(int id, Bundle args) {
        return new MyLoader(this, args);
    }



    @Override
    public void onLoaderReset(@NonNull androidx.loader.content.Loader<String> loader) {

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Toast.makeText(this, "Дешифрованный текст: " + data, Toast.LENGTH_LONG).show();
    }



}
