package com.example.taiwanrailway_booking_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BookingButton,TicketRecordButton,TrainNumberButton,TicketPriceButton,SeatLeftButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookingButton = findViewById(R.id.BookingButton_ID);
        TicketRecordButton = findViewById(R.id.TicketRecordButton_ID);/*
        TrainNumberButton = findViewById(R.id.TrainNumberButton_ID);
        TicketPriceButton = findViewById(R.id.TicketPriceButton_ID);
        SeatLeftButton = findViewById(R.id.SeatLeftButton_ID);*/

        setBookingButton();
        setTicketRecordButton();/*
        setTrainNumberButton();
        setTicketPriceButton();
        setSeatLeftButton();*/

    }

    void setBookingButton() {
        BookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,BookingActivity.class);
                startActivity(intent);
            }
        });
    }
    void setTicketRecordButton() {
        TicketRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TicketRecordActivity.class);
                startActivity(intent);
            }
        });
    }
    void setTrainNumberButton() {
        TrainNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TrainNumberActivity.class);
                startActivity(intent);
            }
        });
    }
    void setTicketPriceButton() {
        TicketPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TicketPriceActivity.class);
                startActivity(intent);
            }
        });
    }
    void setSeatLeftButton() {
        SeatLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SeatLeftActivity.class);
                startActivity(intent);
            }
        });
    }


}
