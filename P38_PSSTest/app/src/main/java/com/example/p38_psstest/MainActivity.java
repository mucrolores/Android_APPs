package com.example.p38_psstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button scissorButton,stoneButton,paperButton;
    private Button checkRecordButton;
    private TextView computerHandTextView,resultTextView;
    private ArrayList<String> playerHandArrayList,computerHandArrayList,resultArrayList;
    private String scissorsString = "剪刀",stoneString = "石頭",paperString = "布";
    private String winString = "玩家贏",evenString = "平手",looseString = "玩家輸";
    private int totalTimes = 0,winTimes = 0,looseTimes = 0,evenTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scissorButton = findViewById(R.id.scissorButton_ID);
        stoneButton = findViewById(R.id.stoneButton_ID);
        paperButton = findViewById(R.id.paperButton_ID);
        computerHandTextView = findViewById(R.id.computerHandTextView_ID);
        resultTextView = findViewById(R.id.resultTextView_ID);
        checkRecordButton = findViewById(R.id.checkRecordButton_ID);

        playerHandArrayList = new ArrayList<>();
        computerHandArrayList = new ArrayList<>();
        resultArrayList = new ArrayList<>();

        setHandButtons();
        setCheckRecordButton();

    }

    private void setHandButtons()
    {
        scissorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTimes = totalTimes + 1;
                switch (getRandomNum())
                {
                    case 0:
                        playerHandArrayList.add(scissorsString);
                        computerHandArrayList.add(scissorsString);
                        resultArrayList.add(evenString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(evenString);
                        evenTimes = evenTimes + 1;
                        break;
                    case 1:
                        playerHandArrayList.add(scissorsString);
                        computerHandArrayList.add(stoneString);
                        resultArrayList.add(looseString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(looseString);
                        looseTimes = looseTimes + 1;
                        break;
                    case 2:
                        playerHandArrayList.add(scissorsString);
                        computerHandArrayList.add(paperString);
                        resultArrayList.add(winString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(winString);
                        winTimes = winTimes + 1;
                        break;
                }
                Toast.makeText(MainActivity.this,
                        "總共場數：" + totalTimes + "\n" +
                                "勝利場數：" + winTimes + "\n" +
                                "平手場數：" + evenTimes + "\n" +
                                "失敗場數：" + looseTimes,
                        Toast.LENGTH_LONG
                        ).show();
            }
        });

        stoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTimes = totalTimes + 1;
                switch (getRandomNum())
                {
                    case 0:
                        playerHandArrayList.add(stoneString);
                        computerHandArrayList.add(scissorsString);
                        resultArrayList.add(winString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(winString);
                        winTimes = winTimes + 1;
                        break;
                    case 1:
                        playerHandArrayList.add(stoneString);
                        computerHandArrayList.add(stoneString);
                        resultArrayList.add(evenString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(evenString);
                        evenTimes = evenTimes + 1;
                        break;
                    case 2:
                        playerHandArrayList.add(stoneString);
                        computerHandArrayList.add(paperString);
                        resultArrayList.add(looseString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(looseString);
                        looseTimes = looseTimes + 1;
                        break;
                }
                Toast.makeText(MainActivity.this,
                        "總共場數：" + totalTimes + "\n" +
                                "勝利場數：" + winTimes + "\n" +
                                "平手場數：" + evenTimes + "\n" +
                                "失敗場數：" + looseTimes,
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTimes = totalTimes + 1;
                switch (getRandomNum())
                {
                    case 0:
                        playerHandArrayList.add(paperString);
                        computerHandArrayList.add(scissorsString);
                        resultArrayList.add(looseString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(looseString);
                        looseTimes = looseTimes + 1;
                        break;
                    case 1:
                        playerHandArrayList.add(paperString);
                        computerHandArrayList.add(stoneString);
                        resultArrayList.add(winString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(winString);
                        winTimes = winTimes + 1;
                        break;
                    case 2:
                        playerHandArrayList.add(paperString);
                        computerHandArrayList.add(paperString);
                        resultArrayList.add(evenString);
                        computerHandTextView.setText(computerHandArrayList.get(computerHandArrayList.size()-1));
                        resultTextView.setText(evenString);
                        evenTimes = evenTimes + 1;
                        break;
                }
                Toast.makeText(MainActivity.this,
                        "總共場數：" + totalTimes + "\n" +
                                "勝利場數：" + winTimes + "\n" +
                                "平手場數：" + evenTimes + "\n" +
                                "失敗場數：" + looseTimes,
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void setCheckRecordButton()
    {
        checkRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(getResources().getString(R.string.playerHandArrayList),playerHandArrayList);
                bundle.putStringArrayList(getResources().getString(R.string.computerHandArrayList),computerHandArrayList);
                bundle.putStringArrayList(getResources().getString(R.string.resultArrayList),resultArrayList);
                bundle.putInt(getResources().getString(R.string.totalTimes),totalTimes);
                bundle.putInt(getResources().getString(R.string.winTimes),winTimes);
                bundle.putInt(getResources().getString(R.string.looseTimes),looseTimes);
                bundle.putInt(getResources().getString(R.string.evenTimes),evenTimes);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private Integer getRandomNum()//0剪刀，1石頭，2布
    {
        return (Integer) (int) (Math.random() * 3);
    }

}
