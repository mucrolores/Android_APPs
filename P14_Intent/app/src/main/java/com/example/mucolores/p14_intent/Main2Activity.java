package com.example.mucolores.p14_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private String act2String;
    private int act2Int;
    private TextView stringText,intText;
    private Button setBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        stringText = findViewById(R.id.sent_string_container);
        intText = findViewById(R.id.sent_int_container);
        setBtn = findViewById(R.id.setTextBtn);
        setBtn.setOnClickListener(setTextBtnOCL);

        Intent receiver = getIntent();
        Bundle bundle = receiver.getExtras();

        act2Int = bundle.getInt("deliver_int");
        act2String = bundle.getString("deliver_string");
    }

    View.OnClickListener setTextBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stringText.setText(act2String);
            intText.setText(String.valueOf(act2Int));
        }
    };
}
