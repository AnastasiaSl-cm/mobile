package ru.mirea.sladkinaaa.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class MyWorkerFragment extends Fragment {
    private TextView statusTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_worker, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button startWorkBtn = view.findViewById(R.id.action_settings);
        statusTextView = view.findViewById(R.id.text_slideshow);
        startWorkBtn.setOnClickListener(v -> startBackgroundWork());
    }
    private void startBackgroundWork() {
        statusTextView.setText("Статус: задача запущена");

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(false)
                .build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ru.mirea.sladkinaaa.mireaproject.UploadWorker.class)
                .setConstraints(constraints)
                .build();

        WorkManager workManager = WorkManager.getInstance(requireContext());

        workManager.getWorkInfoByIdLiveData(workRequest.getId())
                .observe(getViewLifecycleOwner(), workInfo -> {
                    if (workInfo != null) {
                        if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            statusTextView.setText("Статус: задача выполнена");
                        } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                            statusTextView.setText("Статус: ошибка выполнения");
                        } else if (workInfo.getState() == WorkInfo.State.RUNNING) {
                            statusTextView.setText("Статус: задача выполняется");
                        }
                    }
                });

        workManager.enqueue(workRequest);
    }
}
