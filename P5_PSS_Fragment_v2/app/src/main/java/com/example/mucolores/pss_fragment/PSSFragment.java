package com.example.mucolores.pss_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PSSFragment extends Fragment {

    private TextView computerHandTxt,gameResultTxt;
    public TextView gameCountText,playerWinText,evenText,computerWinText;

    public int gameCountInt=0,
                playerWinInt=0,
                evenInt=0,
                computerWinInt=0;

    private boolean ShowResultBool = false;

    private final static String TAG_FRAGMENT_RESULT_1 = "RESULT 1",
                                TAG_FRAGMENT_RESULT_2 = "RESULT 2";

    public ResultFragment fragGameResult = new ResultFragment();
    public resultFragment_2 fragGameResult2 = new resultFragment_2();

    private final static String TAG = "Result";
    private int mTagCount = 0;

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

        Button scissorBtn,stoneBtn,paperBtn,resetBtn,showResultBtn,showResult2Btn,hideResultBtn;

        scissorBtn = getView().findViewById(R.id.scissorBtnID);
        scissorBtn.setOnClickListener(sciOCL);
        paperBtn = getView().findViewById(R.id.paperBtnID);
        paperBtn.setOnClickListener(papOCL);
        stoneBtn = getView().findViewById(R.id.stoneBtnID);
        stoneBtn.setOnClickListener(stoOCL);

        resetBtn = getView().findViewById(R.id.resetBtnID);
        resetBtn.setOnClickListener(resOCL);

        showResultBtn = getView().findViewById(R.id.button);
        showResultBtn.setOnClickListener(showResBtn);
        hideResultBtn = getView().findViewById(R.id.button2);
        hideResultBtn.setOnClickListener(hideResBtn);
        showResult2Btn = getView().findViewById(R.id.button3);
        showResult2Btn.setOnClickListener(showRes2Btn);

        computerHandTxt = getView().findViewById(R.id.computerResultTxtID);
        gameResultTxt = getView().findViewById(R.id.gameResultTxtID);


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

            if(ShowResultBool == true){
                gameCountText.setText(String.valueOf(gameCountInt));
                playerWinText.setText(String.valueOf(playerWinInt));
                evenText.setText(String.valueOf(evenInt));
                computerWinText.setText(String.valueOf(computerWinInt));
            }




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
            if(ShowResultBool == true){
                gameCountText.setText(String.valueOf(gameCountInt));
                playerWinText.setText(String.valueOf(playerWinInt));
                evenText.setText(String.valueOf(evenInt));
                computerWinText.setText(String.valueOf(computerWinInt));
            }

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
            if(ShowResultBool == true){
                gameCountText.setText(String.valueOf(gameCountInt));
                playerWinText.setText(String.valueOf(playerWinInt));
                evenText.setText(String.valueOf(evenInt));
                computerWinText.setText(String.valueOf(computerWinInt));
            }

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

    private View.OnClickListener showResBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mTagCount++;
            String sFragTag = TAG + String.valueOf(mTagCount);

            if(ShowResultBool == false) {
                FragmentTransaction fragTran = getFragmentManager().beginTransaction();
                fragTran.replace(R.id.frameLay,fragGameResult,sFragTag);
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();

                ShowResultBool = true;
            }
        }


    };

    private View.OnClickListener showRes2Btn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mTagCount++;
            String sFragTag = TAG + String.valueOf(mTagCount);

            if(ShowResultBool == false) {
                FragmentTransaction fragTran = getFragmentManager().beginTransaction();
                fragTran.replace(R.id.frameLay,fragGameResult2,sFragTag);
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();

                ShowResultBool = true;
            }


        }
    };

    private View.OnClickListener hideResBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ShowResultBool == true)
            {
                ShowResultBool = false;

                FragmentManager fragMgr = getFragmentManager();

                String sFragTag = TAG + String.valueOf(mTagCount);
                Fragment fragGameResult = fragMgr.findFragmentByTag(sFragTag);
                FragmentTransaction fragTran = fragMgr.beginTransaction();
                fragTran.remove(fragGameResult);
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();

            }
        }

    };

}
