package com.example.taiwanrailway_booking_system;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class TicketRecordListViewAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private ArrayList<Map<String,String>> data;

    public TicketRecordListViewAdapter(ArrayList<Map<String,String>> InData){
        this.data = InData;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(context == null)
        {
            context = parent.getContext();
        }
        if(convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_record_listview_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.TicketDateTextView = convertView.findViewById(R.id.TicketDateTextView_ID);
            viewHolder.TrainNumberTextView = convertView.findViewById(R.id.TrainNumberTextView_ID);
            viewHolder.SeatNumberTextView = convertView.findViewById(R.id.SeatNumberTextView_ID);
            viewHolder.StartStationTextView = convertView.findViewById(R.id.StartStationTextView_ID);
            viewHolder.EndStationTextView = convertView.findViewById(R.id.EndStationTextView_ID);
            viewHolder.CancelButton = convertView.findViewById(R.id.CancelButton_ID);
            viewHolder.CancelButton.setTag(R.id.CancelButton_ID,position-1);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)convertView.getTag();
        Map<String,String> Row = data.get(position);
        viewHolder.TicketDateTextView.setText(Row.get("TicketDate"));
        viewHolder.TrainNumberTextView.setText(Row.get("TrainNumber"));
        viewHolder.SeatNumberTextView.setText(Row.get("SeatNumber"));
        viewHolder.StartStationTextView.setText(Row.get("StartStation"));
        viewHolder.EndStationTextView.setText(Row.get("EndStation"));
        if(Row.get("TicketDate") == "日期")
        {
            viewHolder.CancelButton.setText("");
            viewHolder.CancelButton.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
        else
        {
            viewHolder.CancelButton.setOnClickListener(this);
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CancelButton_ID:
                final int tag = (int)v.getTag(R.id.CancelButton_ID);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String cmd = context.getResources().getString(R.string.DB_Root_name) + "deleteTicketRecord.php?"+
                                "TicketID=" + data.get(tag+1).get("TicketID") + "&&" +
                                "OwnerID=" + data.get(tag+1).get("OwnerID");
                        Log.e("Delete",cmd);
                        DB_Connector.Execute(cmd,context);
                    }
                });
                thread.start();
        }
    }

    static class ViewHolder{
        TextView TicketDateTextView,TrainNumberTextView,SeatNumberTextView,StartStationTextView,EndStationTextView;
        Button CancelButton;
    }
}
