package com.example.mucolores.p27_bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;

import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button BtOnBtn,BtOffBtn,DeviceVisibleBtn,ListDeviceBtn,sendBtn;
    private ListView connectedDeviceLV;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    private EditText typeText;
    private TextView receiveText;

    private OutputStream outputStream;
    private InputStream inputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtOnBtn = findViewById(R.id.BtOnButton_ID);
        BtOffBtn = findViewById(R.id.BtOffButton_ID);
        DeviceVisibleBtn = findViewById(R.id.DeviceVisible_Button_ID);
        ListDeviceBtn = findViewById(R.id.ListDeviceButton_ID);
        sendBtn = findViewById(R.id.sendTextBtn_ID);
        typeText = findViewById(R.id.typeIntText_ID);
        receiveText = findViewById(R.id.receive_text_ID);

        connectedDeviceLV = findViewById(R.id.connectedDev_ID);

        BtOnBtn.setOnClickListener(BtOnBtnOCL);
        BtOffBtn.setOnClickListener(BtOffBtnOCL);
        DeviceVisibleBtn.setOnClickListener(DeviceVisibleBtnOCL);
        ListDeviceBtn.setOnClickListener(ListDeviceBtnOCL);


        BA = BluetoothAdapter.getDefaultAdapter();
    }

    View.OnClickListener BtOnBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!BA.isEnabled()){
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn,0);
                Toast.makeText(getApplicationContext(),"Turned on", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Already on", Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener BtOffBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BA.disable();
            Toast.makeText(getApplicationContext(),"Turned off",Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener DeviceVisibleBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(getVisible,0);
        }
    };

    View.OnClickListener ListDeviceBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pairedDevices = BA.getBondedDevices();

            ArrayList list = new ArrayList();
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName());
            }
            Toast.makeText(getApplicationContext(),"Showing Paired Devices",Toast.LENGTH_LONG).show();
            final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,list);
            connectedDeviceLV .setAdapter(adapter);
        }
    };
/*
    View.OnClickListener sendBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            write();

        }
    };

    private void init()throws IOException{
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter != null)
        {
            if(bluetoothAdapter.isEnabled()){
                Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();

                if(bondedDevices.size()>0){
                    Object[] devices = (Object[]) bondedDevices.toArray();
                    BluetoothDevice device = (BluetoothDevice) devices[];
                    ParcelUuid[] uuids = device.getUuids();
                    BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(uuids[0].getUuid());
                    socket.connect();
                    outputStream = socket.getOutputStream();
                    inputStream = socket.getInputStream();
                }

                Log.e("error","no apporiate paired devices");
            }else{
                Log.e("error","Bluetooth is disabled");
            }
        }
    }

    public void write (String s)throws IOException{
        outputStream.write(s.getBytes());
    }

    public void run(){
        final int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytes = 0;
        int b = BUFFER_SIZE;

        while(true){
            try{
                bytes = inputStream.read(buffer,bytes,BUFFER_SIZE-bytes);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
*/
}
