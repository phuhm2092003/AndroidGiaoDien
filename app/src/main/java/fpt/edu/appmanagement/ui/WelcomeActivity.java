package fpt.edu.appmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import fpt.edu.appmanagement.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // MÀNG HÌNH SẼ CHẠY SAU 2 GIÂY
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        }, 1000);
    }
}