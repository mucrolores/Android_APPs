package com.example.taiwanrailway_booking_system;

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

public class BookSelectListViewAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private ArrayList<Map<String,String>> data;
    private Map<String,String> OtherData;
    public BookSelectListViewAdapter(ArrayList<Map<String,String>> InData,Map<String,String> InOtherData){
        this.OtherData = InOtherData;
        this.data = InData;
    }

    @Override
    public int getCount() { return data.size();}

    @Override
    public Object getItem(int position) { return data.get(position);}

    @Override
    public long getItemId(int position) { return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(context == null)
        {
            context = parent.getContext();
        }
        if(convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_message_listview_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.TrainNumberTextView = convertView.findViewById(R.id.TrainNumberTextView_ID);
            viewHolder.TrainDateTextView = convertView.findViewById(R.id.TrainDateTextView_ID);
            viewHolder.TrainTimeTextView = convertView.findViewById(R.id.TrainTimeTextView_ID);
            viewHolder.TrainKindTextView = convertView.findViewById(R.id.TrainKindTextView_ID);
            viewHolder.TicketStartStationTextView = convertView.findViewById(R.id.TicketStartStationTextView_ID);
            viewHolder.TicketEndStationTextView = convertView.findViewById(R.id.TicketEndStationTextView_ID);
            viewHolder.BookButton = convertView.findViewById(R.id.BookButton_ID);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)convertView.getTag();
        Map<String,String> Row = data.get(position);
        viewHolder.TrainNumberTextView.setText(Row.get("TrainNumber"));
        viewHolder.TrainDateTextView.setText(Row.get("TrainDate"));
        viewHolder.TrainTimeTextView.setText(Row.get("TrainTime"));
        viewHolder.TrainKindTextView.setText(Row.get("TrainKind"));
        viewHolder.TicketStartStationTextView.setText(Row.get("TrainStartStation"));
        viewHolder.TicketEndStationTextView.setText(Row.get("TrainEndStation"));
        viewHolder.BookButton.setOnClickListener(this);
        viewHolder.BookButton.setTag(R.id.BookButton_ID,position);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.BookButton_ID)
        {
            final int position = (int)v.getTag(R.id.BookButton_ID);
            final String TicketID = String.valueOf((int)(Math.random()*10000));
            final String PaymentCode = String.valueOf((int)(Math.random()*10000));

            Toast.makeText(context,"訂票代碼：" + TicketID,Toast.LENGTH_LONG).show();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String cmd = context.getResources().getString(R.string.DB_Root_name) + "bookTicket.php?" +
                            "TicketID=" + TicketID +
                            "&&" + "TrainNumberDate=" + data.get(position).get("TrainDate") +
                            "&&" + "UserID=" + OtherData.get("UserID") +
                            "&&" + "TrainNumber=" + data.get(position).get("TrainNumber") +
                            "&&" + "StartStationID=" + OtherData.get("StartStationID") +
                            "&&" + "EndStationID=" + OtherData.get("EndStationID") +
                            "&&" + "PaymentCode=" + PaymentCode;

                    String Result = DB_Connector.Execute(cmd,context);
                    Log.e("BookResult",cmd);
                }
            });
            thread.start();

        }
    }

    static class ViewHolder{
        TextView TrainNumberTextView,TrainDateTextView,TrainTimeTextView,TrainKindTextView,TicketStartStationTextView,TicketEndStationTextView;
        Button BookButton;
    }
}
