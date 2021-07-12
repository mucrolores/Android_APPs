package com.example.mucolores.p18_broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button registBtn,unregistBtn,publish1Btn,publish2Btn;

    private MyBroadCast_2 mMyReceiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registBtn = findViewById(R.id.registBtn);
        unregistBtn = findViewById(R.id.unregistBtn);
        publish1Btn = findViewById(R.id.publisBroadCast1Btn);
        publish2Btn = findViewById(R.id.publisBroadCast2Btn);

        registBtn.setOnClickListener(registBtnOCL);
        unregistBtn.setOnClickListener(unregistBtnOCL);
        publish1Btn.setOnClickListener(publish1BtnOCL);
        publish2Btn.setOnClickListener(publish2BtnOCL);
    }

    View.OnClickListener registBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            IntentFilter myitFilter = new IntentFilter("com.app.android.MY_BROADCAST_2");
            mMyReceiver2 = new MyBroadCast_2();
            registerReceiver(mMyReceiver2,myitFilter);
        }
    };

    View.OnClickListener unregistBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            unregisterReceiver(mMyReceiver2);
        }
    };

    View.OnClickListener publish1BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.app.android.MY_BROADCAST_1");
            intent.putExtra("sender_name","主程式");
            sendBroadcast(intent);
        }
    };

    View.OnClickListener publish2BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.app.android.MY_BROADCAST_2");
            intent.putExtra("sender_name","主程式");
            sendBroadcast(intent);
        }
    };
}

