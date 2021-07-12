package com.example.mucolores.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Page2Activity extends AppCompatActivity {

    private Spinner mSpnOpt;
    private String msOption;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        final TextView myText = findViewById(R.id.textView2);
        myText.setText("nothing here...");
        mSpnOpt = findViewById(R.id.spinner);
        mSpnOpt.setOnItemSelectedListener(SpnSelect);


        Button toMain = findViewById(R.id.button3);
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent();
                intentMain.setClass(Page2Activity.this,MainActivity.class);
                //startActivity(intentMain);
                Page2Activity.this.finish();
            }
        });
        Button setting = findViewById(R.id.button4);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    result="you picked "+msOption;

                    myText.setText(result);
            }
        });

        Button changeP3 = findViewById(R.id.button5);
        changeP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPThree = new Intent();
                intentPThree.setClass(Page2Activity.this,Page3.class);
                startActivity(intentPThree);
                Page2Activity.this.finish();
            }
        });

    }
    private AdapterView.OnItemSelectedListener SpnSelect = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            msOption = adapterView.getSelectedItem().toString();
        }
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}


