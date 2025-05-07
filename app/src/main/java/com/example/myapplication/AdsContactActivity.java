package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AdsContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_contact);

        // Xử lý sự kiện click
        findViewById(R.id.ads_phone).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0945540303"));
            startActivity(intent);
        });

        findViewById(R.id.ads_email).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:quangcao@dantri.com.vn"));
            startActivity(intent);
        });

        findViewById(R.id.ads_website).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://quangcaodantri.vn"));
            startActivity(intent);
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}
