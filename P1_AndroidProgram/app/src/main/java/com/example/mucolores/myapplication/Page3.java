package com.example.mucolores.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Page3 extends AppCompatActivity {


    private String mealResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        final TextView result = findViewById(R.id.textView4);

        final RadioGroup radGrp = findViewById(R.id.radGrp);

        Button checkChoose = findViewById(R.id.button7);
        checkChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radGrp.getCheckedRadioButtonId()){
                    case R.id.radioButton_aiMusic:
                        result.setText("Correct");
                        break;
                    case R.id.radioButton_blouse:
                        result.setText("Not a person");
                        break;
                    case R.id.radioButton_allWrong:
                        result.setText("Answer Exist");
                        break;
                }
            }
        });
        final CheckBox mealBF, mealLun, mealDin;


        mealBF = findViewById(R.id.checkBox1);
        mealLun = findViewById(R.id.checkBox2);
        mealDin = findViewById(R.id.checkBox3);

        final TextView mealResultText = findViewById(R.id.textView6);

        Button mealCheck = findViewById(R.id.button8);
        mealCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (mealBF.isChecked() && mealLun.isChecked() &&mealDin.isChecked())
                {
                    mealResult = getString(R.string.meal_Taken) + getString(R.string.chkBox1) +"，"+ getString(R.string.chkBox2) +"，"+ getString(R.string.chkBox3);
                }
                else if(mealBF.isChecked() && mealLun.isChecked())
                {
                    mealResult = getString(R.string.meal_Taken) + getString(R.string.chkBox1) +"，"+ getString(R.string.chkBox2);
                }
                else if(mealBF.isChecked() && mealDin.isChecked())
                {
                    mealResult = getString(R.string.meal_Taken) + getString(R.string.chkBox1) +"，"+ getString(R.string.chkBox3);
                }
                else if(mealLun.isChecked() && mealDin.isChecked())
                {
                    mealResult = getString(R.string.meal_Taken) + getString(R.string.chkBox2) +"，"+ getString(R.string.chkBox3);
                }
                else if(mealBF.isChecked())
                {
                    mealResult = getString(R.string.meal_Taken) + getString(R.string.chkBox1);
                }
                else if(mealLun.isChecked())
                {
                    mealResult = getString(R.string.meal_Taken) + getString(R.string.chkBox2);
                }
                else if(mealDin.isChecked())
                {
                    mealResult = getString(R.string.meal_Taken) + getString(R.string.chkBox3);
                }

                mealResultText.setText(mealResult);
            }
        });

        Button GoPg4 = findViewById(R.id.button11);
        GoPg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenPg4 = new Intent();
                OpenPg4.setClass(Page3.this,Page4Activity.class);
                startActivity(OpenPg4);
                Page3.this.finish();
            }
        });

        Button goBack = findViewById(R.id.button6);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent();
                goBack.setClass(Page3.this,Page2Activity.class);
                startActivity(goBack);
                Page3.this.finish();
            }
        });
    }
}
