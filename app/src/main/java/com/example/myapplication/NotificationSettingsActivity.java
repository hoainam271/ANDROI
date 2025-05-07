package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class NotificationSettingsActivity extends AppCompatActivity {

    private static final String PREF_NAME = "AppSettings";
    private static final String KEY_NOTIFICATIONS_ENABLED = "notifications_enabled";

    private SwitchCompat notificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_bao); // Đảm bảo tên file XML này đúng

        // Ánh xạ view
        notificationSwitch = findViewById(R.id.switch_notifications);
        ImageButton backButton = findViewById(R.id.back_icon);

        // Lấy trạng thái đã lưu từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS_ENABLED, true);
        notificationSwitch.setChecked(isEnabled);

        // Cập nhật khi người dùng bật/tắt switch
        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit()
                    .putBoolean(KEY_NOTIFICATIONS_ENABLED, isChecked)
                    .apply();
        });

        // Nút quay lại
        backButton.setOnClickListener(v -> onBackPressed());
    }
}
