package com.example.taiwanrailway_booking_system;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookByTrainNumberFragment extends Fragment {

    private TextView DateTextView;
    private EditText TrainNumEditText1,TrainNumEditText2,TrainNumEditText3;

    private Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);

    public BookByTrainNumberFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_by_train_number_fragment_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DateTextView = getView().findViewById(R.id.DateTextView_ID);
        TrainNumEditText1 = getView().findViewById(R.id.TrainNumEditText1_ID);
        TrainNumEditText2 = getView().findViewById(R.id.TrainNumEditText2_ID);
        TrainNumEditText3 = getView().findViewById(R.id.TrainNumEditText3_ID);

        String todayMonth = String.valueOf(month+1);
        String todayDay = String.valueOf(day);
        if(month+1<10)
        {
            todayMonth = "0" + String.valueOf(month+1);
        }
        if(day<10)
        {
            todayDay = "0" + String.valueOf(day);
        }

        DateTextView.setText(String.valueOf(year) + "-" + todayMonth + "-" + todayDay);

        DateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date;
                        String monthString = String.valueOf(month+1);
                        String dayString = String.valueOf(day);
                        if(month+1<10)
                        {
                            monthString = "0" + String.valueOf(month+1);
                        }
                        if(day<10)
                        {
                            dayString =  "0" + String.valueOf(day);
                        }
                        date = String.valueOf(year)+"-"+monthString+"-"+dayString;
                        DateTextView.setText(date);

                    }

                }, year, month, day).show();
            }
        });
    }

    public Map<String,String> getValue()
    {
        String AllTrainNumber = "";
        if(TrainNumEditText1.getText().toString().length()>0)
        {
            AllTrainNumber = AllTrainNumber + TrainNumEditText1.getText().toString();
        }
        if(TrainNumEditText2.getText().toString().length()>0)
        {
            AllTrainNumber = AllTrainNumber + "," + TrainNumEditText2.getText().toString();
        }
        if(TrainNumEditText3.getText().toString().length()>0)
        {
            AllTrainNumber = AllTrainNumber + "," + TrainNumEditText3.getText().toString();
        }
        Map<String,String> ReturnMap = new HashMap<>();
        ReturnMap.put("Date",DateTextView.getText().toString());
        ReturnMap.put("TrainNum",AllTrainNumber);
        return ReturnMap;
    }

    public void SetValue()
    {
        DateTextView.setText("2020-01-09");
        TrainNumEditText1.setText("1121");
    }

    public void ClearValue()
    {
        DateTextView.setText("");
        TrainNumEditText1.setText("");
    }
}
