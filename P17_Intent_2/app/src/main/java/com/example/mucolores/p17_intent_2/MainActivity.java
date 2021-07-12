package com.example.mucolores.p17_intent_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button switch_pageBtn;
    private TextView StringText,IntText;
    private int storageInt;
    private String storageString;
    final static private int STATE_OK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch_pageBtn = findViewById(R.id.switch_pageBtn);
        switch_pageBtn.setOnClickListener(switch_pageBtnOCL);

        StringText = findViewById(R.id.receiveString);
        IntText = findViewById(R.id.receiveInt);


    }

    View.OnClickListener switch_pageBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent page2 = new Intent();
            page2.setClass(MainActivity.this,Main2Activity.class);
            startActivityForResult(page2,STATE_OK);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode != STATE_OK)
        {
            return;
        }
        switch (resultCode)
        {
            case RESULT_OK:
                Bundle bundle = data.getExtras();
                storageInt = bundle.getInt("Message_int");
                storageString = bundle.getString("Message_string");

                StringText.setText(storageString);
                IntText.setText(String.valueOf(storageInt));
                break;

            case RESULT_CANCELED:
                StringText.setText(R.string.errorText);
                IntText.setText(R.string.errorText);
                break;

        }

    }
}
