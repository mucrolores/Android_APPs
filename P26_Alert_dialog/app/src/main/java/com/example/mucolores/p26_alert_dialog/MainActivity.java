package com.example.mucolores.p26_alert_dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button altDlogBtn,datePickBtn,timePickBtn;
    private TextView resultText;
    private Dialog messageDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        altDlogBtn = findViewById(R.id.Button_ID);
        datePickBtn = findViewById(R.id.date_pick_btn_ID);
        timePickBtn = findViewById(R.id.time_pick_btn_ID);


        altDlogBtn.setOnClickListener(altDlogBtnOCL);
        datePickBtn.setOnClickListener(datePickBtnOCL);
        timePickBtn.setOnClickListener(timePickBtnOCL);

        resultText = findViewById(R.id.textView);

    }

    View.OnClickListener altDlogBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            messageDialog = new Dialog(MainActivity.this);
            messageDialog.setCancelable(false);
            messageDialog.setContentView(R.layout.alert_dialog_layout);

            Button cnacelBtn = (Button)messageDialog.findViewById(R.id.cancelBtn_ID);
            Button confirmBtn = (Button)messageDialog.findViewById(R.id.confirm_Btn_ID);

            cnacelBtn.setOnClickListener(cnacelBtnOCL);
            confirmBtn.setOnClickListener(confirmBtnOCL);

            messageDialog.show();
        }
    };

    View.OnClickListener datePickBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar now = Calendar.getInstance();
            DatePickerDialog mDatePickerDialog = new DatePickerDialog(MainActivity.this,
            datePickerOnDateSet,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH));
            mDatePickerDialog.setTitle("選擇日期");
            mDatePickerDialog.setMessage("請選擇日期");
            mDatePickerDialog.setCancelable(false);

            mDatePickerDialog.show();
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerOnDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            resultText.setText(Integer.toString(year)+"年，"+Integer.toString(month+1)+"月，"+Integer.toString(dayOfMonth)+"日");
        }
    };

    View.OnClickListener timePickBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar now = Calendar.getInstance();
            TimePickerDialog mTimePickerDialog = new TimePickerDialog(MainActivity.this,
                    timePickerDlgOnSet,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true);
            mTimePickerDialog.setTitle("選擇日期");
            mTimePickerDialog.setMessage("請選擇日期");
            mTimePickerDialog.setCancelable(false);

            mTimePickerDialog.show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerDlgOnSet = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            resultText.setText(Integer.toString(hourOfDay)+"時，"+Integer.toString(minute)+"分");
        }
    };

    View.OnClickListener cnacelBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Toast.makeText(MainActivity.this,R.string.cancel_toast_massage,Toast.LENGTH_LONG).show();

            messageDialog.cancel();
        }
    };

    View.OnClickListener confirmBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            EditText BusName = (EditText)messageDialog.findViewById(R.id.edtTextName_ID);
            EditText BusNum = (EditText)messageDialog.findViewById(R.id.edtTextBusNum_ID);
            resultText.setText("別名" + ":" + BusName.getText().toString() + "," + "車牌號碼" + ":" + BusNum.getText().toString());
            Toast.makeText(MainActivity.this,R.string.confirm_toast_message,Toast.LENGTH_LONG).show();
            messageDialog.cancel();

        }
    };

}
