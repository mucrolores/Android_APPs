package com.example.mucolores.p34_log_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button testBtn;
    private TextView Text;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testBtn = findViewById(R.id.Button);
        Text = findViewById(R.id.textView);
        testBtn.setOnClickListener(BtnOCL);

    }

    View.OnClickListener BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Text.setText("Clicked");
            Log.i(TAG,"Button_On_Clicked");
        }
    };
}
