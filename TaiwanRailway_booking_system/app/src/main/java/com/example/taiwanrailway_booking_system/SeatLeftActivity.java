package com.example.taiwanrailway_booking_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SeatLeftActivity extends AppCompatActivity {

    TextView TrainMessageTextView;
    Button BookingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_left);

    }
}
