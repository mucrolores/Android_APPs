package com.example.mucolores.p15_taipei_wait_bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton public_bike_Btn,mrt_Btn,taiwan_Btn
                    ,train_time_Btn,bus_track_Btn,data_Btn
                    ,message_Btn,read_me_Btn,setting_Btn
                    ,route_search_Btn,near_stop_Btn
                    ,route_Btn,usual_use_stop_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        public_bike_Btn = findViewById(R.id.public_bike_ID);
        mrt_Btn = findViewById(R.id.mrt_ID);
        taiwan_Btn = findViewById(R.id.taiwan_ID);
        train_time_Btn = findViewById(R.id.train_time_ID);
        bus_track_Btn = findViewById(R.id.bus_track_ID);
        data_Btn = findViewById(R.id.data_ID);
        message_Btn = findViewById(R.id.message_ID);
        read_me_Btn = findViewById(R.id.read_me_ID);
        setting_Btn = findViewById(R.id.setting_ID);

        route_search_Btn = findViewById(R.id.route_search_ID);
        near_stop_Btn = findViewById(R.id.near_stop_ID);
        route_Btn = findViewById(R.id.route_ID);
        usual_use_stop_Btn = findViewById(R.id.usual_use_stop_ID);


        public_bike_Btn.setOnTouchListener(public_bike_BtnOTL);
        mrt_Btn.setOnTouchListener(mrt_BtnOTL);
        taiwan_Btn.setOnTouchListener(taiwan_BtnOTL);
        train_time_Btn.setOnTouchListener(train_time_BtnOTL);
        bus_track_Btn.setOnTouchListener(bus_track_BtnOTL);
        data_Btn.setOnTouchListener(data_BtnOTL);
        message_Btn.setOnTouchListener(message_BtnOTL);
        read_me_Btn.setOnTouchListener(read_me_BtnOTL);
        setting_Btn.setOnTouchListener(setting_BtnOTL);

        route_search_Btn.setOnTouchListener(route_search_BtnOTL);
        near_stop_Btn.setOnTouchListener(near_stop_BtnOTL);
        route_Btn.setOnTouchListener(route_BtnOTL);
        usual_use_stop_Btn.setOnTouchListener(usual_use_stop_BtnOTL);


        public_bike_Btn.setOnClickListener(public_bike_BtnOCL);
        mrt_Btn.setOnClickListener(mrt_BtnOCL);
        taiwan_Btn.setOnClickListener(taiwan_BtnOCL);
        train_time_Btn.setOnClickListener(train_time_BtnOCL);
        bus_track_Btn.setOnClickListener(bus_track_BtnOCL);
        data_Btn.setOnClickListener(data_BtnOCL);
        message_Btn.setOnClickListener(message_BtnOCL);
        read_me_Btn.setOnClickListener(read_me_BtnOCL);
        setting_Btn.setOnClickListener(setting_BtnOCL);

        route_search_Btn.setOnClickListener(route_search_BtnOCL);
        near_stop_Btn.setOnClickListener(near_stop_BtnOCL);
        route_Btn.setOnClickListener(route_BtnOCL);
        usual_use_stop_Btn.setOnClickListener(usual_use_stop_BtnOCL);


    }

    View.OnTouchListener public_bike_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                public_bike_Btn.setBackgroundResource(R.drawable.public_bike_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                public_bike_Btn.setBackgroundResource(R.drawable.public_bike);
            }
            return false;
        }
    };

    View.OnTouchListener mrt_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                mrt_Btn.setBackgroundResource(R.drawable.mrt_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                mrt_Btn.setBackgroundResource(R.drawable.mrt);
            }
            return false;
        }
    };

    View.OnTouchListener taiwan_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                taiwan_Btn.setBackgroundResource(R.drawable.taiwan_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                taiwan_Btn.setBackgroundResource(R.drawable.taiwan);
            }
            return false;
        }
    };

    View.OnTouchListener train_time_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                train_time_Btn.setBackgroundResource(R.drawable.train_time_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                train_time_Btn.setBackgroundResource(R.drawable.train_time);
            }
            return false;
        }
    };

    View.OnTouchListener bus_track_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                bus_track_Btn.setBackgroundResource(R.drawable.bus_track_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                bus_track_Btn.setBackgroundResource(R.drawable.bus_track);
            }
            return false;
        }
    };

    View.OnTouchListener data_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                data_Btn.setBackgroundResource(R.drawable.data_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                data_Btn.setBackgroundResource(R.drawable.data);
            }
            return false;
        }
    };

    View.OnTouchListener message_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                message_Btn.setBackgroundResource(R.drawable.message_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                message_Btn.setBackgroundResource(R.drawable.message);
            }
            return false;
        }
    };

    View.OnTouchListener read_me_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                read_me_Btn.setBackgroundResource(R.drawable.read_me_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                read_me_Btn.setBackgroundResource(R.drawable.read_me);
            }
            return false;
        }
    };

    View.OnTouchListener setting_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                setting_Btn.setBackgroundResource(R.drawable.setting_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                setting_Btn.setBackgroundResource(R.drawable.setting);
            }
            return false;
        }
    };

    View.OnTouchListener route_search_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                route_search_Btn.setBackgroundResource(R.drawable.route_search_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                route_search_Btn.setBackgroundResource(R.drawable.route_search);
            }
            return false;
        }
    };

    View.OnTouchListener near_stop_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                near_stop_Btn.setBackgroundResource(R.drawable.near_stop_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                near_stop_Btn.setBackgroundResource(R.drawable.near_stop);
            }
            return false;
        }
    };

    View.OnTouchListener route_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                route_Btn.setBackgroundResource(R.drawable.route_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                route_Btn.setBackgroundResource(R.drawable.route);
            }
            return false;
        }
    };

    View.OnTouchListener usual_use_stop_BtnOTL = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                usual_use_stop_Btn.setBackgroundResource(R.drawable.usual_stop_oc);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                usual_use_stop_Btn.setBackgroundResource(R.drawable.usual_use_stop);
            }
            return false;
        }
    };

    View.OnClickListener public_bike_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener mrt_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent MRTIntent = new Intent();
            MRTIntent.setClass(MainActivity.this,MRT_route_activity.class);
            startActivity(MRTIntent);
        }
    };

    View.OnClickListener taiwan_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent TaiwanIntent = new Intent();
            TaiwanIntent.setClass(MainActivity.this,Taiwan_goodgo_activity.class);
            startActivity(TaiwanIntent);
        }
    };

    View.OnClickListener train_time_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent trainTimeIntent = new Intent();
            trainTimeIntent.setClass(MainActivity.this,Train_time_activity.class);
            startActivity(trainTimeIntent);
        }
    };

    View.OnClickListener bus_track_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent busTrackIntent = new Intent();
            busTrackIntent.setClass(MainActivity.this,Bus_track_activity.class);
            startActivity(busTrackIntent);
        }
    };

    View.OnClickListener data_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener message_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener read_me_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener setting_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener route_search_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener near_stop_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener route_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener usual_use_stop_BtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
