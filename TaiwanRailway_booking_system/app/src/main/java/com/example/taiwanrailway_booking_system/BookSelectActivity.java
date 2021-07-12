package com.example.taiwanrailway_booking_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookSelectActivity extends AppCompatActivity {

    ListView ResultListView;

    ArrayList<Map<String,String>> TrainMessageData = new ArrayList<>();
    BookSelectListViewAdapter bookSelectListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_select);

        ResultListView = findViewById(R.id.ResultListView_ID);

        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("data");

        try
        {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject row = jsonArray.getJSONObject(i);
                Map<String,String> RowMap = new HashMap<>();
                RowMap.put("TrainNumber",row.getString("train_number"));
                RowMap.put("TrainDate",row.getString("train_number_date"));
                RowMap.put("TrainTime",row.getString("arrive_time"));
                RowMap.put("TrainKind",row.getString("train_kind_ch"));
                RowMap.put("TrainStartStation",row.getString("train_start_station_name"));
                RowMap.put("TrainEndStation",row.getString("train_end_station_name"));
                TrainMessageData.add(RowMap);
            }

            Map<String,String> other = new HashMap<>();
            other.put("UserID",bundle.getString("UserID"));
            other.put("StartStationID",bundle.getString("selfStartStationID"));
            other.put("EndStationID",bundle.getString("SelfEndStationID"));

            bookSelectListViewAdapter = new BookSelectListViewAdapter(TrainMessageData,other);
            ResultListView.setAdapter(bookSelectListViewAdapter);

        }catch (JSONException e)
        {
            Log.e("JSON Error",e.toString());
        }




    }
}
