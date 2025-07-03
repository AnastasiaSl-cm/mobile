package ru.mirea.sladkinaaa.mireaproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
    private EditText editTextName, editTextAge;
    private SeekBar seekBarPower;
    private TextView textPowerLevel;
    private Button buttonSave;

    private static final String PREF_NAME = "user_profile";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_POWER = "power";

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editTextName = view.findViewById(R.id.editTextName);
        editTextAge = view.findViewById(R.id.editTextAge);
        seekBarPower = view.findViewById(R.id.seekBarPower);
        textPowerLevel = view.findViewById(R.id.textPowerLevel);
        buttonSave = view.findViewById(R.id.buttonSave);

        SharedPreferences preferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);


        editTextName.setText(preferences.getString(KEY_NAME, ""));
        editTextAge.setText(String.valueOf(preferences.getInt(KEY_AGE, 0)));
        int power = preferences.getInt(KEY_POWER, 0);
        seekBarPower.setProgress(power);
        textPowerLevel.setText("Уровень суперсилы: " + power);

        seekBarPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textPowerLevel.setText("Уровень суперсилы: " + progress);
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        buttonSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            int age = Integer.parseInt(editTextAge.getText().toString());
            int powerLevel = seekBarPower.getProgress();

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_NAME, name);
            editor.putInt(KEY_AGE, age);
            editor.putInt(KEY_POWER, powerLevel);
            editor.apply();

            Toast.makeText(getActivity(), "Данные сохранены!", Toast.LENGTH_SHORT).show();
        });
    }
}
