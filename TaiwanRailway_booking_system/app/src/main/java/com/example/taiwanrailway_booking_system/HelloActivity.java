package com.example.taiwanrailway_booking_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HelloActivity extends AppCompatActivity {

    TextView SystemMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        SystemMessageTextView = findViewById(R.id.SystemMessageTextView_ID);

        Toast.makeText(HelloActivity.this,"歡迎",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.setClass(HelloActivity.this,MainActivity.class);
/*
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        startActivity(intent);
        this.finish();

    }
}
