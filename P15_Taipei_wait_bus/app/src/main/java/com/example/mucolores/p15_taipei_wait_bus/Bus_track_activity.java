package com.example.mucolores.p15_taipei_wait_bus;

import android.app.Dialog;
import android.app.ListActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bus_track_activity extends AppCompatActivity {

    private Dialog messageDialog;
    private EditText busNameEdtText,busNumEdtText;
    String[] busName;
    String[] busNum;
    int dataOrderCount = 0;
    List<Map<String,Object>> busList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_track_activity);

        ActionBar busTrackActionBar = getSupportActionBar();
        busTrackActionBar.setDisplayHomeAsUpEnabled(true);
        busTrackActionBar.setTitle(R.string.bus_track_activity_title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater busMenuInflater = getMenuInflater();
        busMenuInflater.inflate(R.menu.bus_track_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.track_bus_menu_button_1:
                messageDialog = new Dialog(Bus_track_activity.this);
                messageDialog.setCancelable(false);
                messageDialog.setContentView(R.layout.add_track_bus_dialog_layout);

                Button cancelBtn = (Button)messageDialog.findViewById(R.id.cancelBtn_ID);
                Button storageBtn = (Button)messageDialog.findViewById(R.id.confirm_Btn_ID);

                cancelBtn.setOnClickListener(cancelBtnOCL);
                storageBtn.setOnClickListener(storageBtnOCL);

                busNameEdtText = (EditText)messageDialog.findViewById(R.id.edtTextName_ID);
                busNumEdtText = (EditText)messageDialog.findViewById(R.id.edtTextBusNum_ID);

                messageDialog.show();

                break;
            case R.id.track_bus_menu_button_2:


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener cancelBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            messageDialog.cancel();
        }
    };


    View.OnClickListener storageBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(busNameEdtText.getText().toString()=="")
            {
                busName[dataOrderCount] = "default";//get empty editText but something wrong
            }
            else
            {
                busName[dataOrderCount] = busNameEdtText.getText().toString();
            }
            if(busNumEdtText.getText().toString()=="")
            {
                busNum[dataOrderCount] = "default";
            }
            else{
                busNum[dataOrderCount] = busNumEdtText.getText().toString();
            }

            dataOrderCount++;

            busList = new ArrayList<Map<String, Object>>();

            for (int i = 0; i < dataOrderCount; i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("bus_num_text", busNum[i]);
                item.put("bus_name_text", busName[i]);
                busList.add(item);
            }

            SimpleAdapter adapter = new SimpleAdapter(Bus_track_activity.this, busList, R.layout.bus_message_layout,
                    new String[]{"bus_num_text","bus_name_text"},
                    new int[]{R.id.bus_num_text_ID,R.id.bus_name_text_ID});

            /*
            setListAdapter(adapter);// can't get my adapterClass right
            ListView listView = getListView();
            listView.setOnItemClickListener(listViewOICL);
            */

            messageDialog.cancel();
        }
    };


}
