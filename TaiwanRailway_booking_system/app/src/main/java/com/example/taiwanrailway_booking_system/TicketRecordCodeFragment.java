package com.example.taiwanrailway_booking_system;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class TicketRecordCodeFragment extends Fragment {

    private EditText TicketCodeEditText;

    public TicketRecordCodeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ticket_record_code_fragment_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TicketCodeEditText = getView().findViewById(R.id.TicketCodeEditText_ID);
    }

    public String getResult()
    {
        return TicketCodeEditText.getText().toString();
    }
}
