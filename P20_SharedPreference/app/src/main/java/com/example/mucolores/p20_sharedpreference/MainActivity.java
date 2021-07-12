package com.example.mucolores.p20_sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button plusOneBtn,minusOneBtn,clearDataBtn,storageDataBtn,LoadDataBtn;
    private TextView myModifyTextView;
    private EditText myTypeText;
    private String tmpString;
    private int tmpInt=0;
    private boolean dataFull = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plusOneBtn = findViewById(R.id.plusOneBtn);
        minusOneBtn = findViewById(R.id.minusOneBtn);
        clearDataBtn = findViewById(R.id.clearDataBtnID);
        storageDataBtn = findViewById(R.id.storageDataBtnID);
        LoadDataBtn = findViewById(R.id.loadDataBtnID);

        plusOneBtn.setOnClickListener(plusOneBtnOCL);
        minusOneBtn.setOnClickListener(minusOneBtnOCL);
        clearDataBtn.setOnClickListener(clearDataBtnOCL);
        storageDataBtn.setOnClickListener(StorageDataBtnOCL);
        LoadDataBtn.setOnClickListener(LoadDataBtnOCL);

        myModifyTextView = (TextView) findViewById(R.id.modifyNumText);
        myTypeText = (EditText) findViewById(R.id.editText);

        SharedPreferences resultData = getSharedPreferences("RESULT_DATA",0);
        dataFull = resultData.getBoolean("data_existence",false);
    }

    View.OnClickListener plusOneBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tmpInt++;
            myModifyTextView.setText(String.valueOf(tmpInt));
        }
    };

    View.OnClickListener minusOneBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tmpInt--;
            myModifyTextView.setText(String.valueOf(tmpInt));
        }
    };

    View.OnClickListener clearDataBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(dataFull == true)
            {
                dataFull = false;
                SharedPreferences resultData = getSharedPreferences("RESULT_DATA",0);
                resultData.edit().clear().apply();
                Toast.makeText(MainActivity.this,"清除完成",Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(MainActivity.this,"無資料須清除",Toast.LENGTH_LONG).show();
            }


        }
    };

    View.OnClickListener StorageDataBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(dataFull==false)
            {
                dataFull = true;
                tmpString = myTypeText.getText().toString();
                SharedPreferences resultData = getSharedPreferences("RESULT_DATA",0);
                resultData.edit()
                        .putInt("tmp_int",tmpInt)
                        .putString("tmp_string",tmpString)
                        .putBoolean("data_existence",dataFull)
                        .apply();
                Toast.makeText(MainActivity.this,"儲存完成",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"已經有儲存的資料了 請先清除原先之資料",Toast.LENGTH_LONG).show();
            }

        }
    };

    View.OnClickListener LoadDataBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(dataFull==false)
            {
                Toast.makeText(MainActivity.this,"無儲存之資料",Toast.LENGTH_LONG).show();
            }
            else
            {
                SharedPreferences resultData = getSharedPreferences("RESULT_DATA",0);
                tmpInt = resultData.getInt("tmp_int",0);
                tmpString = resultData.getString("tmp_string","error");

                myModifyTextView.setText(String.valueOf(tmpInt));
                myTypeText.setText(tmpString);

                Toast.makeText(MainActivity.this,"載入完成",Toast.LENGTH_LONG).show();
            }

        }
    };

}
