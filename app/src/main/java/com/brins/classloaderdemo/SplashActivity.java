package com.brins.classloaderdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    private String[] permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 23) {
            for (String per : permission) {
                if (checkSelfPermission(per) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                            permission,
                            1
                    );
                    break;
                } else {
                    readDex();
                }
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            boolean granted = false;
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    granted = true;
                    break;
                }
            }
            if (granted) {
                readDex();
            } else
                finish();
        }
    }

    private void readDex() {
        if (FixDexUtil.isGoingToFix(this)) {
            FixDexUtil.loadFixedDex(this, new File(Environment.getExternalStorageDirectory(), "aa"));
        }
    }

    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
