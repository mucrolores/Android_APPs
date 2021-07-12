package com.example.taiwanrailway_booking_system;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookByTrainTimeFragment extends Fragment {

    private TextView DateTextView;
    private RadioButton StartTimeRadioButton,ArriveRadioButton;
    private TextView TimeStartTextView,TimeEndTextView;
    private RadioButton LocalRadioButton,TyRoKoRadioButton,ZiChanRadioButton,ChuGuanRadioButton,FuShinRadioButton;

    private Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);

    public BookByTrainTimeFragment (){};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_by_train_time_fragment_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DateTextView =getView().findViewById(R.id.DateTextView_ID);
        StartTimeRadioButton = getView().findViewById(R.id.StartTimeRadioButton_ID);
        ArriveRadioButton = getView().findViewById(R.id.ArriveRadioButton_ID);
        TimeStartTextView = getView().findViewById(R.id.TimeStartTextView_ID);
        TimeEndTextView = getView().findViewById(R.id.TimeEndTextView_ID);
        LocalRadioButton = getView().findViewById(R.id.LocalRadioButton_ID);
        TyRoKoRadioButton = getView().findViewById(R.id.TyRoKoRadioButton_ID);
        ZiChanRadioButton = getView().findViewById(R.id.ZiChanRadioButton_ID);
        ChuGuanRadioButton = getView().findViewById(R.id.ChuGuanRadioButton_ID);
        FuShinRadioButton = getView().findViewById(R.id.FuShinRadioButton_ID);

        StartTimeRadioButton.setChecked(true);
        LocalRadioButton.setChecked(true);

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
                        if(month<10)
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

        TimeStartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourString  = String.valueOf(hourOfDay);
                        String minuteString = String.valueOf(minute);
                        if(hourOfDay < 10)
                        {
                            hourString = "0" + String.valueOf(hourOfDay);
                        }
                        if(minute < 10)
                        {
                            minuteString = "0" + String.valueOf(minute);
                        }

                        TimeStartTextView.setText(hourString+ ":" + minuteString);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });

        TimeEndTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hourString  = String.valueOf(hourOfDay);
                        String minuteString = String.valueOf(minute);
                        if(hourOfDay < 10)
                        {
                            hourString = "0" + String.valueOf(hourOfDay);
                        }
                        if(minute < 10)
                        {
                            minuteString = "0" + String.valueOf(minute);
                        }

                        TimeEndTextView.setText(hourString+ ":" + minuteString);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });
    }

    public Map<String,String> getValue()
    {
        String TimeState;
        if(StartTimeRadioButton.isChecked())
        {
            TimeState = "start";
        }
        else
        {
            TimeState = "arrive";
        }

        String TrainKind = "";
        if(LocalRadioButton.isChecked())
        {
            TrainKind = "Local%20Train";
        }
        if(TyRoKoRadioButton.isChecked())
        {
            TrainKind = "Taroko%20Express";
        }
        if(ZiChanRadioButton.isChecked())
        {
            TrainKind = "Tze-Chiang%20Limited%20Express";
        }
        if(ChuGuanRadioButton.isChecked())
        {
            TrainKind = "Chu-Kuang%20Express";
        }
        if(FuShinRadioButton.isChecked())
        {
            TrainKind = "Fu-Hsing%20Semi%20Express";
        }

        Map<String,String> ReturnMap = new HashMap<>();
        ReturnMap.put("Date",DateTextView.getText().toString());
        ReturnMap.put("SATime",TimeState);
        ReturnMap.put("TimeStart",TimeStartTextView.getText().toString());
        ReturnMap.put("TimeEnd",TimeEndTextView.getText().toString());
        ReturnMap.put("TrainKind",TrainKind);

        return ReturnMap;
    }
}
