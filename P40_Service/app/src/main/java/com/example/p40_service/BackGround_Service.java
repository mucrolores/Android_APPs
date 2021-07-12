package com.example.p40_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.Queue;

public class BackGround_Service extends Service {

    Queue<Byte> DataBuffer;

    @Override
    public void onCreate() {
        super.onCreate();

        ObjectInitial();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        while(true)
        {
            GenerateData();
        }

        //return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void ObjectInitial()
    {
        DataBuffer = new LinkedList<>();
    }

    private void GenerateData()
    {
        for(int i=0;i<100;i++)
        {
            DataBuffer.poll();
            DataBuffer.add((byte)((int)(Math.random()*100)));
        }
    }
}
