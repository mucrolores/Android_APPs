package com.example.mucolores.p24_list_test;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    List<Map<String,Object>> mList;
    private Button switchPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        SimpleAdapter adapter = new SimpleAdapter(this,mList,R.layout.test_layout, new String[]{"section_line_ID","section_name_ID","along_view_ID","data_source_ID"}, new int[]{R.id.section_line_ID,R.id.section_name_ID,R.id.along_view_ID,R.id.data_source_ID});

        setListAdapter(adapter);

        switchPageBtn = findViewById(R.id.button);
        switchPageBtn.setOnClickListener(switchPageBtnOCL);

     }

    View.OnClickListener switchPageBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intnent = new Intent();
            intnent.setClass(MainActivity.this,Main2Activity.class);
            startActivity(intnent);
        }
    };
}
