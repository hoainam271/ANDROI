package com.example.myapplication;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    private TextView textSizeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getSharedPreferences("settings", MODE_PRIVATE);

        ImageButton backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(v -> finish());

        ImageView nut2 = findViewById(R.id.nut2);
        nut2.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, NotificationSettingsActivity.class);
            startActivity(intent);
        });

        // DARK MODE SETTINGS
        ImageView darkModeSetting = findViewById(R.id.nut3);
        ViewGroup parent = (ViewGroup) darkModeSetting.getParent();
        TextView darkModeStatus = parent.findViewById(R.id.dark_mode_status);

        int savedMode = prefs.getInt("night_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        AppCompatDelegate.setDefaultNightMode(savedMode);
        updateDarkModeStatusText(savedMode, darkModeStatus);

        darkModeSetting.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.dark_mode_dialog, null);
            BottomSheetDialog dialog = new BottomSheetDialog(SettingsActivity.this);
            dialog.setContentView(view);

            TextView off = view.findViewById(R.id.option_off);
            TextView on = view.findViewById(R.id.option_on);
            TextView system = view.findViewById(R.id.option_system);
            TextView cancel = view.findViewById(R.id.cancel_button);

            View.OnClickListener listener = v1 -> {
                int mode;
                if (v1 == off) {
                    mode = AppCompatDelegate.MODE_NIGHT_NO;
                } else if (v1 == on) {
                    mode = AppCompatDelegate.MODE_NIGHT_YES;
                } else {
                    mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                }

                AppCompatDelegate.setDefaultNightMode(mode);
                prefs.edit().putInt("night_mode", mode).apply();
                updateDarkModeStatusText(mode, darkModeStatus);
                recreate();
                dialog.dismiss();
            };

            off.setOnClickListener(listener);
            on.setOnClickListener(listener);
            system.setOnClickListener(listener);
            cancel.setOnClickListener(v1 -> dialog.dismiss());

            dialog.show();
        });

        // TEXT SIZE SETTINGS
        ImageView nut4 = findViewById(R.id.nut4);
        parent = (ViewGroup) nut4.getParent();
        textSizeStatus = parent.findViewById(R.id.text_size_status);

        // Cập nhật hiển thị kích thước chữ ban đầu
        float savedScale = prefs.getFloat("text_scale", 1.0f);
        updateTextSizeStatus(savedScale, textSizeStatus);

        nut4.setOnClickListener(v -> {
            View view = getLayoutInflater().inflate(R.layout.text_size_dialog, null);
            BottomSheetDialog dialog = new BottomSheetDialog(SettingsActivity.this);
            dialog.setContentView(view);

            TextView normal = view.findViewById(R.id.option_normal);
            TextView large = view.findViewById(R.id.option_large);
            TextView extraLarge = view.findViewById(R.id.option_extra_large);
            TextView cancel = view.findViewById(R.id.btn_cancel);

            View.OnClickListener listener = v1 -> {
                float scale;
                if (v1 == normal) scale = 1.0f;
                else if (v1 == large) scale = 1.15f;
                else scale = 1.3f;

                prefs.edit().putFloat("text_scale", scale).apply();
                updateTextSizeStatus(scale, textSizeStatus); // cập nhật ngay
                dialog.dismiss();
            };

            normal.setOnClickListener(listener);
            large.setOnClickListener(listener);
            extraLarge.setOnClickListener(listener);
            cancel.setOnClickListener(v1 -> dialog.dismiss());

            dialog.show();
        });
        ImageView feedbackIcon = findViewById(R.id.nut5); // giả sử ID là nut5
        feedbackIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });
        ImageView infoIcon = findViewById(R.id.nut6); // icon thông tin tòa soạn
        infoIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, EditorialInfoActivity.class);
            startActivity(intent);
        });
        ImageView adsIcon = findViewById(R.id.nut7); // icon liên hệ quảng cáo
        adsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, AdsContactActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.nut8).setOnClickListener(v -> showRatingDialog());

    }
    private void showRatingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_rating, null);
        builder.setView(dialogView);

        RatingBar ratingBar = dialogView.findViewById(R.id.rating_bar);
        TextView btnLater = dialogView.findViewById(R.id.btn_later);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            dialog.dismiss();
            // Nếu rating >= 4, mở link Play Store
            if (rating >= 4) {
                final String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
            // Có thể thêm xử lý nếu rating thấp (ví dụ: feedback)
        });

        btnLater.setOnClickListener(v -> dialog.dismiss());
    }

    private void updateDarkModeStatusText(int mode, TextView statusTextView) {
        switch (mode) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                statusTextView.setText("Tắt");
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                statusTextView.setText("Bật");
                break;
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
            default:
                statusTextView.setText("Hệ thống");
                break;
        }
    }

    private void updateTextSizeStatus(float scale, TextView statusTextView) {
        if (scale == 1.0f) {
            statusTextView.setText("Bình thường");
        } else if (scale == 1.15f) {
            statusTextView.setText("Lớn");
        } else {
            statusTextView.setText("Rất lớn");
        }

        statusTextView.setTextSize(16 * scale); // Điều chỉnh textSize tương ứng
    }
}
