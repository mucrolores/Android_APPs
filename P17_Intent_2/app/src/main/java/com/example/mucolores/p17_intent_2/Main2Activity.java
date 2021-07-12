package com.example.mucolores.p17_intent_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText EdtTextString,EdtTextInt;
    private int tmpInt;
    private String tmpString;
    private Button cancelBtn,checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EdtTextString = findViewById(R.id.EdtString);
        EdtTextInt = findViewById(R.id.EdtInt);

        cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(cancelBtnOCL);
        checkBtn = findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(checkBtnOCL);

    }

    View.OnClickListener cancelBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED);
            finish();
        }
    };

    View.OnClickListener checkBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            tmpInt = Integer.parseInt(EdtTextInt.getText().toString());
            tmpString = EdtTextString.getText().toString();

            Intent page2Intent = new Intent();

            Bundle bundle = new Bundle();
            bundle.putInt("Message_int",tmpInt);
            bundle.putString("Message_string",tmpString);

            page2Intent.putExtras(bundle);
            setResult(RESULT_OK,page2Intent);
            finish();
        }
    };
}
