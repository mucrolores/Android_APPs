package com.example.mucolores.p15_taipei_wait_bus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MRT_route_activity extends AppCompatActivity {

    private Spinner ticket_option_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrt_route_activity);

        ticket_option_spinner = findViewById(R.id.spinner);

        ActionBar MRTActionBar = getSupportActionBar();
        MRTActionBar.setDisplayHomeAsUpEnabled(true);


    }


}
