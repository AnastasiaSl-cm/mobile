package ru.mirea.sladkinaaa.mireaproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraNoteFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private EditText captionEditText;
    private Bitmap capturedImage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera_note, container, false);
        imageView = view.findViewById(R.id.imageView);
        captionEditText = view.findViewById(R.id.captionEditText);
        Button takePhotoButton = view.findViewById(R.id.takePhotoButton);
        Button saveNoteButton = view.findViewById(R.id.saveNoteButton);

        takePhotoButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
        saveNoteButton.setOnClickListener(v -> {
            String caption = captionEditText.getText().toString();
            Toast.makeText(getContext(), "Заметка сохранена", Toast.LENGTH_SHORT).show();
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            capturedImage = (Bitmap) extras.get("data");
            imageView.setImageBitmap(capturedImage);
        }
    }
}
