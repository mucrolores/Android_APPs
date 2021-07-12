package com.example.mucolores.p35_kill_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button generateBtn,clearBtn;
    private EditText peopleNum;
    private TextView result;
    private int peopleNumInt;
    private int[] order;
    private String resultOrder;
    private int randPlace;
    private int tmpNum;
    private int recorded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateBtn = findViewById(R.id.Button);
        peopleNum = findViewById(R.id.EdtText);
        result = findViewById(R.id.textView);
        clearBtn = findViewById(R.id.ClearBtn_ID);

        generateBtn.setOnClickListener(GenerOCL);
        clearBtn.setOnClickListener(ClearOCL);
        peopleNumInt = 0;
        recorded = 0;

    }

    View.OnClickListener GenerOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resultOrder = "Start : " + "\n";
            if(recorded==0)
            {
                if(peopleNum.getText().length()==0)
                {
                    result.setText(getResources().getString(R.string.stupid));
                }
                else
                {
                    recorded=1;
                    peopleNumInt = Integer.parseInt(peopleNum.getText().toString());
                    order = new int [peopleNumInt];
                    for(int i=0;i<peopleNumInt;i++)
                    {
                        order[i] = i+1;
                    }

                    for(int i=0;i<peopleNumInt;i++)
                    {
                        randPlace = (int)(Math.random()*(peopleNumInt));
                        tmpNum = order[i];
                        order[i] = order[randPlace];
                        order[randPlace] = tmpNum;
                        tmpNum = 0;
                    }

                    for(int i=0;i<peopleNumInt;i++)
                    {
                        if(order[i]<10)
                        {
                            resultOrder =resultOrder + "0" + order[i] + " eat ";
                        }
                        else
                        {
                            resultOrder = resultOrder + order[i] + " eat ";

                        }
                        if((i+1)%8==0)
                        {
                            resultOrder = resultOrder + "\n";
                        }
                    }
                    result.setText(resultOrder);
                }
            }
            else{
                Toast.makeText(MainActivity.this,"已有產生的序列 若要新組別請先按下CLEAR",Toast.LENGTH_LONG).show();
            }


        }

    };

    View.OnClickListener ClearOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(recorded==1)
            {
                peopleNum.setText("");
                result.setText("");
                peopleNumInt = 0;
                resultOrder = "";
                recorded = 0;

            }
            else
            {
                Toast.makeText(MainActivity.this,"no things to clear",Toast.LENGTH_LONG).show();

            }

        }
    };
}
