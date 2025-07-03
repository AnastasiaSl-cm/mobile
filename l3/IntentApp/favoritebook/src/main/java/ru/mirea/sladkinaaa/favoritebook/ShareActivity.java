package ru.mirea.sladkinaaa.favoritebook;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class ShareActivity extends AppCompatActivity {
    private EditText editTextBook;
    private EditText editTextQuote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        TextView textViewDevBook = findViewById(R.id.textViewDevBook);
        TextView textViewQuote = findViewById(R.id.textViewQuote);
        editTextBook = findViewById(R.id.editTextUserBook);
        editTextQuote = findViewById(R.id.editTextUserQuote);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String book_name = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes_name = extras.getString(MainActivity.QUOTES_KEY);
            textViewDevBook.setText("Любимая книга разработчика: " + book_name);
            textViewQuote.setText("Цитата: " + quotes_name);
        }

        Button buttonSend = findViewById(R.id.buttonSendBack);
        buttonSend.setOnClickListener(v -> {
            String userBook = editTextBook.getText().toString();
            String userQuote = editTextQuote.getText().toString();

            String result = String.format("Название Вашей любимой книги: %s. Цитата: %s",
                    userBook, userQuote);

            Intent data = new Intent();
            data.putExtra(MainActivity.USER_MESSAGE, result);
            setResult(Activity.RESULT_OK, data);
            finish();
        });
    }
}
