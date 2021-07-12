package com.example.mucolores.p19_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startServiceBtn,stopServiceBtn,connectServiceBtn,disconnectServiceBtn,callMyMethodBtn;
    private final String LOG_TAG = "serviceDemo";

    private MyService mMyService = null;

    private ServiceConnection mServConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(LOG_TAG,"onServiceConnected" + name.getClassName());
            mMyService = ((MyService.LocalBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.d(LOG_TAG,"onServiceDisconnected" + name.getClassName());

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceBtn = findViewById(R.id.startServiceBtn);
        stopServiceBtn = findViewById(R.id.stopServiceBtn);
        connectServiceBtn = findViewById(R.id.connectServiceBtn);
        disconnectServiceBtn = findViewById(R.id.disconnectServiceBtn);
        callMyMethodBtn = findViewById(R.id.callMyMethodBtn);

        startServiceBtn.setOnClickListener(startSerViceBtnOCL);
        stopServiceBtn.setOnClickListener(stopServiceBtnOCL);
        connectServiceBtn.setOnClickListener(connectServiceBtnOCL);
        disconnectServiceBtn.setOnClickListener(disconnectServiceBtnOCL);
        callMyMethodBtn.setOnClickListener(callMyMethodBtnOCL);

    }

    View.OnClickListener startSerViceBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMyService = null;
            Intent it = new Intent(MainActivity.this,MyService.class);
            startService(it);
        }
    };

    View.OnClickListener stopServiceBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMyService = null;
            Intent it = new Intent(MainActivity.this,MyService.class);
            stopService(it);
        }
    };

    View.OnClickListener connectServiceBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMyService = null;
            Intent it = new Intent(MainActivity.this,MyService.class);
            bindService(it,mServConn,BIND_AUTO_CREATE);
        }
    };

    View.OnClickListener disconnectServiceBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMyService = null;
            unbindService(mServConn);
        }
    };

    View.OnClickListener callMyMethodBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mMyService != null)
            {
                mMyService.myMethod();
            }
        }
    };
}
