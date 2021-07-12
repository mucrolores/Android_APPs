package com.example.mucolores.p15_taipei_wait_bus;

import android.app.DatePickerDialog;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Train_time_activity extends AppCompatActivity {

    private SectionAdapter TrainTimeSeciontAdapter;
    ViewPager TrainTimeViewPager;
    private final String ITEM_NAME = "Item_Name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_time_activity);

        TrainTimeSeciontAdapter = new SectionAdapter(getSupportFragmentManager());
        TrainTimeViewPager = findViewById(R.id.trainTimeViewPager);
        TrainTimeViewPager.setAdapter(TrainTimeSeciontAdapter);

        TabLayout trainTabLayout = findViewById(R.id.trainTabLayout);

        trainTabLayout.setupWithViewPager(TrainTimeViewPager);

        ActionBar trainActionBar = getSupportActionBar();
        trainActionBar.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater trainMenuInflater = getMenuInflater();
        trainMenuInflater.inflate(R.menu.train_time_menu,menu);

        ImageButton datePick,locationPick;

        datePick = (ImageButton)menu.findItem(R.id.menu_button_1).getActionView();
        locationPick = (ImageButton)menu.findItem(R.id.menu_button_2).getActionView();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_button_1:
                Calendar now = Calendar.getInstance();
                DatePickerDialog mDatePickerDialog = new DatePickerDialog(Train_time_activity.this,datePickerOnDateSet,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
                mDatePickerDialog.setCancelable(false);
                mDatePickerDialog.show();
                break;

            case R.id.menu_button_2:
                /*
                ExpandableListView expandlabeList = findViewById(R.id.expandlabeList_1);

                List<Map<String,String>> sectionList = new ArrayList<Map<String, String>>();
                List<List<Map<String,String>>> stationList = new ArrayList<List<Map<String,String>>>();

                String[] sectionArray = getResources().getStringArray(R.array.station_section);
                String[] taipeiArray = getResources().getStringArray(R.array.taipei_section);
                String[] otherArray = getResources().getStringArray(R.array.other_section);

                for(int i = 0; !sectionArray[i].equals("default"); i++)
                {
                    Map<String,String> section = new HashMap<String, String>();
                    section.put(ITEM_NAME,sectionArray[i]);
                    sectionList.add(section);

                    Map<String,String> station = new HashMap<String,String>();

                    if(i==0)
                    {
                        for(int j=0;!taipeiArray[j].equals("default");j++)
                        {
                            station.put(ITEM_NAME,taipeiArray[i]);
                            stationList.add((List<Map<String, String>>) station);
                        }

                    }
                    if(i==1)
                    {
                        for(int j=0;!otherArray[j].equals("default");j++)
                        {
                            station.put(ITEM_NAME,taipeiArray[i]);
                            stationList.add((List<Map<String, String>>) station);
                        }
                    }

                    stationList.add((List<Map<String, String>>) station);

                }
                ExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(this,
                        sectionList, R.layout.train_expandalblelist_layout,new String[]{ITEM_NAME},
                        new int[]{R.id.message_text_ID},
                        stationList,R.layout.train_expandalblelist_layout,new String[]{ITEM_NAME},
                        new int[]{R.id.message_text_ID}
                        );

                expandlabeList.setAdapter(expListAdapter);
                */

        }
        return super.onOptionsItemSelected(item);
    }

    private DatePickerDialog.OnDateSetListener datePickerOnDateSet = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            setTitle((month+1)+"月"+dayOfMonth+"日");
        }
    };

    private class SectionAdapter extends FragmentPagerAdapter{

        public SectionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment viewPagerFragment = null;
            switch(position)
            {
                case 0:
                    viewPagerFragment = new TrainTimeFragment_TaipeiHSR();
                    break;
                case 1:
                    viewPagerFragment = new TrainTimeFragment_TaipeiHSR();
                    break;
                case 2:
                    viewPagerFragment = new TrainTimeFragment_TaipeiHSR();
                    break;
                case 3:
                    viewPagerFragment = new TrainTimeFragment_TaipeiHSR();
                    break;
                case 4:
                    viewPagerFragment = new TrainTimeFragment_TaipeiHSR();
                    break;
            }

            return viewPagerFragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position)
            {
                case 0:
                    return "高鐵台北站";
                case 1:
                    return "台北";
                case 2:
                    return "萬華";
                case 3:
                    return "松山";
                case 4:
                    return "高鐵南港站";
            }
            return super.getPageTitle(position);
        }
    }

}
