package ru.mirea.sladkinaaa.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import androidx.loader.content.AsyncTaskLoader;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class MyLoader extends AsyncTaskLoader<String> {
    private final Bundle args;
    public MyLoader(Context context, Bundle args) {
        super(context);
        this.args = args;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public String loadInBackground() {
        if (args == null) return null;
        try {
            byte[] encryptedBytes = Base64.decode(args.getString("encryptedText"), Base64.DEFAULT);
            byte[] keyBytes = Base64.decode(args.getString("key"), Base64.DEFAULT);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка дешифровки";
        }
    }
}
