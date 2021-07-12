package com.example.mucolores.p18_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCast_1 extends BroadcastReceiver  {
    @Override
    public void onReceive(Context context, Intent intent) {
        String sender = intent.getStringExtra("string_data");
        Toast.makeText(context,"收到" + sender + "發送的BroadCasr訊息",Toast.LENGTH_LONG).show();
    }
}
