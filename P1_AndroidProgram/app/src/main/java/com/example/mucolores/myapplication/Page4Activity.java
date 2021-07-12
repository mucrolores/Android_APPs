package com.example.mucolores.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Page4Activity extends AppCompatActivity {

private double RandMax,RandMin,RandNum,range;

private ImageButton adder;
private TextView counter;
private int counterInt;
private String Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        final TextView MaxSet = findViewById(R.id.editText4);
        final TextView MinSet = findViewById(R.id.editText3);
        final TextView Result = findViewById(R.id.textView7);



        Button produceNum = findViewById(R.id.button9);

        produceNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RandMax = Integer.parseInt(MaxSet.getText().toString());
                RandMin = Integer.parseInt(MinSet.getText().toString());
                range =RandMax-RandMin;
                if(RandMax<=RandMin)
                {
                    Result.setText("error");
                }
                else
                {
                    RandNum = Math.random()*(range+1) + RandMin;
                    RandNum = Math.floor(RandNum);
                    int intRandNum = (int) RandNum;
                    Result.setText(String.valueOf(intRandNum));
                }

            }
        });

       Button changePage = findViewById(R.id.button10);
        changePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(Page4Activity.this,Page3.class);
                startActivity(intent2);
                Page4Activity.this.finish();
            }
        });

        String showMessage = "你按了加一";
        final Toast t = Toast.makeText(Page4Activity.this,showMessage,Toast.LENGTH_LONG);

        counter = findViewById(R.id.textView8);

        adder = findViewById(R.id.imageButton2);
        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterInt = Integer.parseInt(counter.getText().toString());
                counterInt = counterInt+1;
                Value = String.valueOf(counterInt);
                counter.setText(Value);

                t.show();
            }
        });



    }
}
