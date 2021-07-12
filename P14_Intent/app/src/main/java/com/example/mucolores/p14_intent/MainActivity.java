package com.example.mucolores.p14_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button showWeb,sendDataBtn;
    private EditText edtStringTxt;
    private EditText edtIntText;
    private String tmpString;
    private int tmpInt;
    private TextView testText1,TestText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showWeb = (Button)findViewById(R.id.webBtn);
        showWeb.setOnClickListener(webBtnOCL);

        edtStringTxt = (EditText)findViewById(R.id.edtTextString);
        edtIntText = (EditText)findViewById(R.id.edtTextInt);

        sendDataBtn = (Button)findViewById(R.id.sendDataBtn);
        sendDataBtn.setOnClickListener(sendDataBtnOCL);

        testText1 = (TextView)findViewById(R.id.textView2);
        TestText2 = (TextView)findViewById(R.id.textView3);
    }

    private View.OnClickListener webBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse("http://youtube.com.tw");
            Intent myintent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(myintent);

        }
    };

    private View.OnClickListener sendDataBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            tmpString = edtStringTxt.getText().toString();
            testText1.setText(tmpString);
            tmpInt = Integer.parseInt(edtIntText.getText().toString());
            tmpString  = edtIntText.getText().toString();
            TestText2.setText(tmpString);
            */
            tmpString = edtStringTxt.getText().toString();
            tmpInt = Integer.parseInt(edtIntText.getText().toString());

            Intent act2 = new Intent();

            Bundle bundle = new Bundle();
            bundle.putInt("deliver_int",tmpInt);
            bundle.putString("deliver_string",tmpString);

            act2.putExtras(bundle);

            act2.setClass(MainActivity.this,Main2Activity.class);
            startActivity(act2);




        }
    };


}
