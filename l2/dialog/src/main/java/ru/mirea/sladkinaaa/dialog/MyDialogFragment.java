package ru.mirea.sladkinaaa.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.widget.Toast;

public class MyDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Заголовок диалога")
                .setMessage("Это текст диалогового окна.")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Вы нажали: Да", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Вы нажали: Нет", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Вы нажали: Отмена", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}
