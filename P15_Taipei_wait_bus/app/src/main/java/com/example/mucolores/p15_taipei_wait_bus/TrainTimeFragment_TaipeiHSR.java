package com.example.mucolores.p15_taipei_wait_bus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrainTimeFragment_TaipeiHSR extends Fragment {

    List<Map<String,Object>>mList;

    public TrainTimeFragment_TaipeiHSR() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_train_time_fragment__taipei_hsr, container, false);

        ListView trainTimeListView = view.findViewById(R.id.train_time_list_ID);
        mList = new ArrayList<Map<String, Object>>();

        String[] trainArriveTime = getResources().getStringArray(R.array.train_arrive_time);
        String[] trainCode = getResources().getStringArray(R.array.train_code);
        String[] startToDestination = getResources().getStringArray(R.array.start_to_destination);
        String[] midStop = getResources().getStringArray(R.array.mid_stop);
        String[] trainStyle = getResources().getStringArray(R.array.train_style);
        String[] upDownRecord = getResources().getStringArray(R.array.up_down_record);


        for(int i=0;i<trainArriveTime.length;i++)
        {
            Map<String,Object>item = new HashMap<String,Object>();
            item.put("comming_time_ID",trainArriveTime[i]);
            item.put("train_style_text_ID",trainStyle[i]);
            item.put("train_code_text_ID",trainCode[i]);
            item.put("destination_ID",startToDestination[i]);
            item.put("went_threw_ID",midStop[i]);
            mList.add(item);
        }


        SimpleAdapter adapter = new SimpleAdapter(getContext(),mList,R.layout.train_time_upward_layout,
                new String[]{"comming_time_ID","train_style_text_ID","train_code_text_ID","destination_ID","went_threw_ID"},
                new int[]{R.id.comming_time_ID,R.id.train_style_text_ID,R.id.train_code_text_ID,R.id.destination_ID,R.id.went_threw_ID,});

        trainTimeListView.setAdapter(adapter);

        return view;
    }

}
