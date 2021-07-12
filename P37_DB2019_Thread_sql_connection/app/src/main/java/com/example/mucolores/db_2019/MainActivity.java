package com.example.mucolores.db_2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button goBtn,PG2Button;
    private TextView textView;
    private static String AllData;
    private String httpUrl;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goBtn = findViewById(R.id.button_ID);
        PG2Button = findViewById(R.id.button2_ID);
        textView = findViewById(R.id.textView_ID);

        goBtn.setOnClickListener(goBtnOCL);
        PG2Button.setOnClickListener(PG2ButtonOCL);
        httpUrl = "http://192.168.43.213/db_class/get_data.php";
    }

    View.OnClickListener goBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    AllData = DB_Connecter.updataingData(httpUrl);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(AllData);
                        }
                    });
                }
            });
            thread.start();

        }
    };

    View.OnClickListener PG2ButtonOCL = new View.OnClickListener()
    {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }
    };
}
