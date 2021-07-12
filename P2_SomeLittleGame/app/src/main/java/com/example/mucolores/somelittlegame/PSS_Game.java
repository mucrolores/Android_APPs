package com.example.mucolores.somelittlegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PSS_Game extends AppCompatActivity {

    private TextView comResult,GR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pss__game);

        Button pButton,siButton,stButton;

        comResult = findViewById(R.id.comResultID);

        pButton = findViewById(R.id.pBtn);
        pButton.setOnClickListener(pOCL);

        siButton = findViewById(R.id.SiBtn);
        siButton.setOnClickListener(siOCL);

        stButton = findViewById(R.id.StBtn);
        stButton.setOnClickListener(stoneOCL);

        GR.findViewById(R.id.GameResult);
    }
    private View.OnClickListener pOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tmpNum = (int)(Math.random()*3+1);
            if(tmpNum==1)
            {
                comResult.setText(R.string.siTxt);
                GR.setText(R.string.looseTxt);
            }
            else if(tmpNum==2)
            {
                comResult.setText(R.string.stoneTxt);
                GR.setText(R.string.winTxt);
            }
            else if(tmpNum==3)
            {
                comResult.setText(R.string.paperTxt);
                GR.setText(R.string.evenTxt);
            }
        }
    };

    private View.OnClickListener siOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tmpNum = (int)(Math.random()*3+1);
            if(tmpNum==1)
            {
                comResult.setText(R.string.siTxt);
                GR.setText(R.string.evenTxt);
            }
            else if(tmpNum==2)
            {
                comResult.setText(R.string.stoneTxt);
                GR.setText(R.string.looseTxt);
            }
            else if(tmpNum==3)
            {
                comResult.setText(R.string.paperTxt);
                GR.setText(R.string.winTxt);
            }

        }
    };

    private View.OnClickListener stoneOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tmpNum = (int)(Math.random()*3+1);
            if(tmpNum==1)
            {
                comResult.setText(R.string.siTxt);
                GR.setText(R.string.winTxt);
            }
            else if(tmpNum==2)
            {
                comResult.setText(R.string.stoneTxt);
                GR.setText(R.string.evenTxt);
            }
            else if(tmpNum==3)
            {
                comResult.setText(R.string.paperTxt);
                GR.setText(R.string.looseTxt);
            }

        }
    };


}
