package com.example.mucolores.newpss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PssGameActivity extends AppCompatActivity {

    private Button sciButton,stoButton,papButton,resetButton;
    private TextView computerHand,gameResultShow,playerPoint,computerPoint;
    private int playerPt=0,computerPt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pss_game);

        computerHand = findViewById(R.id.comResult);
        gameResultShow = findViewById(R.id.GmResult);

        sciButton = findViewById(R.id.scissorButton);
        sciButton.setOnClickListener(scissorOCL);

        stoButton = findViewById(R.id.stoneButton);
        stoButton.setOnClickListener(stoneOCL);

        papButton = findViewById(R.id.paperButton);
        papButton.setOnClickListener(paperOCL);

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(resetOCL);

        computerPoint =findViewById(R.id.comPID);
        playerPoint = findViewById(R.id.plyPID);
        playerPoint.setText("0");
        computerPoint.setText("0");

    }

    private View.OnClickListener scissorOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int rndNum = (int)(Math.random()*3+1);

            if(rndNum==1)
            {
                computerHand.setText(R.string.scissorButton);
                gameResultShow.setText(R.string.evenResult);

            }
            else if(rndNum==2)
            {
                computerHand.setText(R.string.stoneButton);
                gameResultShow.setText(R.string.looseResult);
                computerPt++;
                computerPoint.setText(String.valueOf(computerPt));
            }
            else if(rndNum==3)
            {
                computerHand.setText(R.string.paperButton);
                gameResultShow.setText(R.string.winResult);
                playerPt++;
                playerPoint.setText(String.valueOf(playerPt));

            }
        }
    };

    private View.OnClickListener stoneOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int rndNum = (int)(Math.random()*3+1);
            if(rndNum==1)
            {
                computerHand.setText(R.string.scissorButton);
                gameResultShow.setText(R.string.winResult);
                playerPt++;
                playerPoint.setText(String.valueOf(playerPt));

            }
            else if(rndNum==2)
            {
                computerHand.setText(R.string.stoneButton);
                gameResultShow.setText(R.string.evenResult);
            }
            else if(rndNum==3)
            {
                computerHand.setText(R.string.paperButton);
                gameResultShow.setText(R.string.looseResult);
                computerPt++;
                computerPoint.setText(String.valueOf(computerPt));

            }
        }
    };

    private View.OnClickListener paperOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int rndNum = (int)(Math.random()*3+1);
            if(rndNum==1)
            {
                computerHand.setText(R.string.scissorButton);
                gameResultShow.setText(R.string.looseResult);
                computerPt++;
                computerPoint.setText(String.valueOf(computerPt));

            }
            else if(rndNum==2)
            {
                computerHand.setText(R.string.stoneButton);
                gameResultShow.setText(R.string.winResult);
                playerPt++;
                playerPoint.setText(String.valueOf(playerPt));

            }
            else if(rndNum==3)
            {
                computerHand.setText(R.string.paperButton);
                gameResultShow.setText(R.string.evenResult);
            }
        }
    };

    private View.OnClickListener resetOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            playerPt=0;
            computerPt=0;
            playerPoint.setText(String.valueOf(playerPt));
            computerPoint.setText(String.valueOf(computerPt));
        }
    };
}
