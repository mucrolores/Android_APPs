package com.example.mucolores.image_project_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public ImageButton pBtn,mBtn;
    public TextView numberTxt;
    private int numberInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBtn = findViewById(R.id.pButtonID);
        pBtn.setOnClickListener(pBtnOCL);
        mBtn = findViewById(R.id.mButtonID);
        mBtn.setOnClickListener(mBtnOCL);

        numberTxt = findViewById(R.id.numberText);
    }

    private View.OnClickListener pBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            numberInt++;
            numberTxt.setText(String.valueOf(numberInt));
        }
    };

    private View.OnClickListener mBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            numberInt--;
            numberTxt.setText(String.valueOf(numberInt));
        }
    };
}
