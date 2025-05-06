package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // nhớ là file XML phải trùng tên

        ImageButton backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Đã bấm nút quay lại", Toast.LENGTH_SHORT).show();
            finish(); // quay lại màn trước
        });
    }
}
