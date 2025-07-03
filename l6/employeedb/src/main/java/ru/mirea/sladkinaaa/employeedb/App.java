package ru.mirea.sladkinaaa.employeedb;


import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "superhero_db").build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
