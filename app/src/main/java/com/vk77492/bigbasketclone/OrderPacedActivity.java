package com.vk77492.bigbasketclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class OrderPacedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_paced);
        startBgThread();
    }

    private void startBgThread() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(1000);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(OrderPacedActivity.this, BottomNavigation.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    };
}