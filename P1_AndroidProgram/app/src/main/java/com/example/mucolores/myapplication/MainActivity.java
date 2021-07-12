package com.example.mucolores.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String InitalSet ="Hello";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView myText = findViewById(R.id.textView);
        myText.setText(InitalSet);

        final EditText myEditText = findViewById(R.id.editText);
        final Switch mySwitch = findViewById(R.id.switch1);

        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(mySwitch.isChecked())
                {
                    myText.setText("good morning "+myEditText.getText());
                }
                else
                {
                    myText.setText("good night "+myEditText.getText());
                }
            }

        });

        final TextView statementText = findViewById(R.id.textView5);

        mySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count+1;
                if(count%2==0)
                {
                    statementText.setText("switch off");
                }
                if(count%2==1)
                {
                    statementText.setText("switch on");
                }
            }
        });

        Button pageButton = findViewById(R.id.button2);
        pageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Page2Activity.class);
                startActivity(intent);
                //MainActivity.this.finish();
            }
        });
    }
}