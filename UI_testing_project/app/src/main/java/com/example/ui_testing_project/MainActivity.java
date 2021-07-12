package com.example.ui_testing_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch testSwitch;
    private RadioButton RadioButtonPercentage,RadioButtonMilliliter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testSwitch = findViewById(R.id.testSwitch_ID);
        RadioButtonPercentage = findViewById(R.id.RadioButtonPercentage_ID);
        RadioButtonMilliliter = findViewById(R.id.RadioButtonMilliliter_ID);

        RadioButtonMilliliter.setChecked(true);


        testSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,String.valueOf(testSwitch.isChecked()),Toast.LENGTH_LONG).show();
            }
        });


    }
}
