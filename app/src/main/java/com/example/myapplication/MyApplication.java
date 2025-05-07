package com.example.myapplication;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int mode = prefs.getInt("night_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(mode);

        float scale = prefs.getFloat("text_scale", 1.0f); // mặc định là bình thường
        android.content.res.Configuration config = getResources().getConfiguration();
        config.fontScale = scale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
