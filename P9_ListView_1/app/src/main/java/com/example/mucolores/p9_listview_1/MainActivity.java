package com.example.mucolores.p9_listview_1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    private TextView mTxtResult;
    List<Map<String,Object>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtResult = (TextView)findViewById(R.id.txtResult);

        mList = new ArrayList<Map<String,Object>>();
        String[] listFromResources = getResources().getStringArray(R.array.weekday);

        for(int i=0;i<listFromResources.length;i++)
        {
            Map<String,Object>item = new HashMap<String,Object>();
            item.put("imgView",android.R.drawable.ic_menu_my_calendar);
            item.put("txtView",listFromResources[i]);
            mList.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,mList,R.layout.simple_list_item_1,
        new String[] {"imgView","txtView"}, new int[]{R.id.imgView,R.id.txtView});

        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(listViewOICL);
    }

    private AdapterView.OnItemClickListener listViewOICL = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String s = ((TextView)view.findViewById(R.id.txtView)).getText().toString();
            mTxtResult.setText(s);
        }
    };
}
