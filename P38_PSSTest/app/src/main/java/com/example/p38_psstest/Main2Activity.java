package com.example.p38_psstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    private TextView totalTimesTextView,winTimesTextView,evenTimesTextView,looseTimesTextView;
    private ListView resultSimpleListView,resultFullListView_ID;

    private ArrayList<String> playerHandArrayList,computerHandArrayList,resultArrayList;
    private int totalTimes = 0,winTimes = 0,looseTimes = 0,evenTimes = 0;
    private SimpleAdapter simpleAdapter;
    private ArrayList<Map<String,String>> adapterArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        totalTimesTextView = findViewById(R.id.totalTimesTextView_ID);
        winTimesTextView = findViewById(R.id.winTimesTextView_ID);
        evenTimesTextView = findViewById(R.id.evenTimesTextView_ID);
        looseTimesTextView = findViewById(R.id.looseTimesTextView_ID);

        resultSimpleListView = findViewById(R.id.resultSimpleListView_ID);

        playerHandArrayList = new ArrayList<>();
        computerHandArrayList = new ArrayList<>();
        resultArrayList = new ArrayList<>();
        adapterArrayList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        playerHandArrayList = bundle.getStringArrayList(getResources().getString(R.string.playerHandArrayList));
        computerHandArrayList = bundle.getStringArrayList(getResources().getString(R.string.computerHandArrayList));
        resultArrayList = bundle.getStringArrayList(getResources().getString(R.string.resultArrayList));

        totalTimes = bundle.getInt(getResources().getString(R.string.totalTimes));
        winTimes = bundle.getInt(getResources().getString(R.string.winTimes));
        looseTimes = bundle.getInt(getResources().getString(R.string.looseTimes));
        evenTimes = bundle.getInt(getResources().getString(R.string.evenTimes));

        totalTimesTextView.setText(String.valueOf(totalTimes));
        winTimesTextView.setText(String.valueOf(winTimes));
        evenTimesTextView.setText(String.valueOf(looseTimes));
        looseTimesTextView.setText(String.valueOf(evenTimes));

        for(int i=0;i<playerHandArrayList.size();i++)
        {
            Map<String,String> map = new HashMap<>();
            map.put(getResources().getString(R.string.playerHandMapKey),playerHandArrayList.get(i));
            map.put(getResources().getString(R.string.computerHandMapKey),computerHandArrayList.get(i));
            map.put(getResources().getString(R.string.resultMapKey),resultArrayList.get(i));
            adapterArrayList.add(map);
        }

        simpleAdapter = new SimpleAdapter(
                Main2Activity.this,
                adapterArrayList,
                R.layout.result_listview_layout,
                new String[]{getResources().getString(R.string.playerHandMapKey),getResources().getString(R.string.computerHandMapKey),getResources().getString(R.string.resultMapKey)},
                new int[]{R.id.playerHandTextView_ID,R.id.computerHandTextView_ID, R.id.resultTextView_ID}
        );

        resultSimpleListView.setAdapter(simpleAdapter);
        setResultSimpleListView();
    }

    private void setResultSimpleListView()
    {
        resultSimpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this,"第" + String.valueOf(position+1) + "場，結果為：" +  resultArrayList.get(position),Toast.LENGTH_LONG).show();
            }
        });
    }
}
