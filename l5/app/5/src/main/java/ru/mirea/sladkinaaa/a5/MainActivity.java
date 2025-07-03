package ru.mirea.sladkinaaa.a5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listSensor = findViewById(R.id.list_view);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for (Sensor sensor : sensors) {
            HashMap<String, Object> sensorMap = new HashMap<>();
            sensorMap.put("Name", sensor.getName());
            sensorMap.put("Value", "Макс. диапазон: " + sensor.getMaximumRange());
            arrayList.add(sensorMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                arrayList,
                android.R.layout.simple_list_item_2,
                new String[]{"Name", "Value"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );
        listSensor.setAdapter(adapter);
    }
}
