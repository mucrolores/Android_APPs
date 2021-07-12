package com.example.mucolores.p24_list_test;


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
public class ListFragment extends Fragment {

    List<Map<String,Object>> mList;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ListView myListView = view.findViewById(R.id.myListViewID);
        mList = new ArrayList<Map<String,Object>>();
        String[] sectionLine = getResources().getStringArray(R.array.section_line_name);
        String[] sectionName = getResources().getStringArray(R.array.section_in);
        String[] alongView = getResources().getStringArray(R.array.along_view_point);
        String[] dataSource = getResources().getStringArray(R.array.data_source);

        for(int i=0;i<sectionLine.length;i++)
        {
            Map<String,Object>item = new HashMap<String,Object>();
            item.put("section_line_ID",sectionLine[i]);
            item.put("section_name_ID",sectionName[i]);
            item.put("along_view_ID",alongView[i]);
            item.put("data_source_ID",dataSource[i]);
            mList.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(getContext(),mList,R.layout.test_layout, new String[]{"section_line_ID","section_name_ID","along_view_ID","data_source_ID"}, new int[]{R.id.section_line_ID,R.id.section_name_ID,R.id.along_view_ID,R.id.data_source_ID});

        myListView.setAdapter(adapter);

        return view;
    }

}
