package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EditorialInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial_info);

        // Click để gọi điện
        findViewById(R.id.contact_phone).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02437366491"));
            startActivity(intent);
        });

        findViewById(R.id.hotline_hn).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0973567567"));
            startActivity(intent);
        });

        findViewById(R.id.hotline_hcm).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0974567567"));
            startActivity(intent);
        });

        findViewById(R.id.contact_email).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:info@dantri.com.vn"));
            startActivity(intent);
        });

        // Nút back (nếu có)
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}

