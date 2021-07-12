package com.example.test_helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView ResultTextView;
    public Button SetButton;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResultTextView = findViewById(R.id.ResultTextView_ID);
        SetButton = findViewById(R.id.SetButton_ID);

        SetSetButton();
    }

    void SetSetButton()
    {
        SetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultTextView.setText(String.valueOf(counter));
                counter++;
            }
        });
    }
}
