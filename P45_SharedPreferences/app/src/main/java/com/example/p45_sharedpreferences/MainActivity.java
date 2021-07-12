package com.example.p45_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView MessageTextView;

    private long TimePast = 0;
    private long TimeTag = 0;
    private boolean SystemRunning = true;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimeTag = System.currentTimeMillis();

        MessageTextView = findViewById(R.id.MessageTextView_ID);

        sharedPreferences = getSharedPreferences(getString(R.string.SP_sharedPreferences),MODE_PRIVATE);
        TimePast = sharedPreferences.getLong(getString(R.string.SP_time_past),0);

        MessageTextView.setText(String.valueOf(TimePast));

        Thread thread = new Thread(()->{
            while (SystemRunning) {
                try {
                    Thread.sleep(1000);
                    TimePast += System.currentTimeMillis()-TimeTag;
                    TimeTag = System.currentTimeMillis();
                    runOnUiThread(()->{MessageTextView.setText(String.valueOf(TimePast));});


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.edit().putLong(getString(R.string.SP_time_past),TimePast).apply();
        SystemRunning = false;
    }
}