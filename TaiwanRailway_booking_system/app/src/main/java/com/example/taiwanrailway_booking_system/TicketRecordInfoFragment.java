package com.example.taiwanrailway_booking_system;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class TicketRecordInfoFragment extends Fragment {

    private TextView TrainDateTextView,StartStaionTextView,EndStationTextView;

    public TicketRecordInfoFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ticket_record_info_fragment_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TrainDateTextView = getView().findViewById(R.id.TicketDateTextView_ID);
        StartStaionTextView = getView().findViewById(R.id.StartStationTextView_ID);
        EndStationTextView = getView().findViewById(R.id.EndStationTextView_ID);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.YEAR);
        String yearString = String.valueOf(year);
        String monthString = String.valueOf(month);
        String dayString = String.valueOf(day);
        if(month < 10)
        {
            monthString = "0" + String.valueOf(month);
        }
        if(day<10)
        {
            dayString = "0" + String.valueOf(day);
        }
        TrainDateTextView.setText(yearString + "-" + monthString + "-" + dayString);
    }
}
