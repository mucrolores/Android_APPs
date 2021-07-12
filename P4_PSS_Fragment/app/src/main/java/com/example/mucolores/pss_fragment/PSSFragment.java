package com.example.mucolores.pss_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PSSFragment extends Fragment {

    private Button scissorBtn,stoneBtn,paperBtn,resetBtn;
    private TextView computerHandTxt,gameResultTxt;
    private TextView gameCountText,playerWinText,evenText,computerWinText;

    private int gameCountInt=0,
                playerWinInt=0,
                evenInt=0,
                computerWinInt=0;

    public PSSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ps, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        scissorBtn = getView().findViewById(R.id.scissorBtnID);
        scissorBtn.setOnClickListener(sciOCL);
        paperBtn = getView().findViewById(R.id.paperBtnID);
        paperBtn.setOnClickListener(papOCL);
        stoneBtn = getView().findViewById(R.id.stoneBtnID);
        stoneBtn.setOnClickListener(stoOCL);

        resetBtn = getView().findViewById(R.id.resetBtnID);
        resetBtn.setOnClickListener(resOCL);

        computerHandTxt = getView().findViewById(R.id.computerResultTxtID);
        gameResultTxt = getView().findViewById(R.id.gameResultTxtID);

        gameCountText = getView().findViewById(R.id.gameTimeCountID);
        playerWinText = getView().findViewById(R.id.winTimeCountID);
        evenText = getView().findViewById(R.id.evenTimeCountID);
        computerWinText = getView().findViewById(R.id.looseTimeCountID);
    }

    private View.OnClickListener sciOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tmpNum = (int)(Math.random()*3+1);
            gameCountInt++;
            if(tmpNum==1)
            {
                evenInt++;
                computerHandTxt.setText(R.string.scissorString);
                gameResultTxt.setText(R.string.evenString);
            }
            else if(tmpNum==2)
            {
                computerWinInt++;
                computerHandTxt.setText(R.string.stoneString);
                gameResultTxt.setText(R.string.looseString);

            }
            else if(tmpNum==3)
            {
                playerWinInt++;
                computerHandTxt.setText(R.string.paperString);
                gameResultTxt.setText(R.string.winString);

            }
            gameCountText.setText(String.valueOf(gameCountInt));
            playerWinText.setText(String.valueOf(playerWinInt));
            evenText.setText(String.valueOf(evenInt));
            computerWinText.setText(String.valueOf(computerWinInt));

        }
    };

    private View.OnClickListener stoOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tmpNum = (int)(Math.random()*3+1);
            gameCountInt++;
            if(tmpNum==1)
            {
                playerWinInt++;
                computerHandTxt.setText(R.string.scissorString);
                gameResultTxt.setText(R.string.winString);

            }
            else if(tmpNum==2)
            {
                evenInt++;
                computerHandTxt.setText(R.string.stoneString);
                gameResultTxt.setText(R.string.evenString);


            }
            else if(tmpNum==3)
            {
                computerWinInt++;
                computerHandTxt.setText(R.string.paperString);
                gameResultTxt.setText(R.string.looseString);

            }
            gameCountText.setText(String.valueOf(gameCountInt));
            playerWinText.setText(String.valueOf(playerWinInt));
            evenText.setText(String.valueOf(evenInt));
            computerWinText.setText(String.valueOf(computerWinInt));
        }
    };

    private View.OnClickListener papOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int tmpNum = (int)(Math.random()*3+1);
            gameCountInt++;
            if(tmpNum==1)
            {
                computerWinInt++;
                computerHandTxt.setText(R.string.scissorString);
                gameResultTxt.setText(R.string.looseString);

            }
            else if(tmpNum==2)
            {
                playerWinInt++;
                computerHandTxt.setText(R.string.stoneString);
                gameResultTxt.setText(R.string.winString);


            }
            else if(tmpNum==3)
            {
                evenInt++;
                computerHandTxt.setText(R.string.paperString);
                gameResultTxt.setText(R.string.evenString);


            }
            gameCountText.setText(String.valueOf(gameCountInt));
            playerWinText.setText(String.valueOf(playerWinInt));
            evenText.setText(String.valueOf(evenInt));
            computerWinText.setText(String.valueOf(computerWinInt));
        }
    };

    private View.OnClickListener resOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            gameCountInt = 0;
            playerWinInt = 0;
            evenInt = 0;
            computerWinInt = 0;

            gameCountText.setText(String.valueOf(gameCountInt));
            playerWinText.setText(String.valueOf(playerWinInt));
            evenText.setText(String.valueOf(evenInt));
            computerWinText.setText(String.valueOf(computerWinInt));
        }
    };

}
