package com.example.taiwanrailway_booking_system;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TicketRecordActivity extends AppCompatActivity {

    EditText UserIDEditText;
    Button SearchButton;
    ListView TicketRecordListView;
    EditText TicketCodeEditText;
    Button RegressionButton;

    Map<Character,Integer> IDCheckMap = new HashMap<>();
    Map<String,String> StationIDToNameMap = new HashMap<>();
    ArrayList<Map<String,String>> data = new ArrayList<>();

    int testTime = 5;
    int SuccessTime = 0;
    int FailTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_record);

        MapInitial();

        InitialFindViewByID();

        setRegressionButton();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String cmd = getString(R.string.DB_Root_name) + "getStationList.php";
                String result = DB_Connector.Execute(cmd,TicketRecordActivity.this);
                try{
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject row = jsonArray.getJSONObject(i);
                        StationIDToNameMap.put(row.getString("station_ID"),row.getString("station_name"));
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Map<String,String> rowMap = new HashMap<>();
        rowMap.put("TicketDate","日期");
        rowMap.put("TrainNumber","車次");
        rowMap.put("SeatNumber","座號");
        rowMap.put("StartStation","起始站");
        rowMap.put("EndStation","目的站");
        rowMap.put("TicketID","");
        rowMap.put("OwnerID","");
        data.add(rowMap);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean available = true;
                if(UserIDEditText.getText().toString().equals(""))
                {
                    available = false;
                    Toast.makeText(TicketRecordActivity.this,"身份證字號不可為空",Toast.LENGTH_SHORT).show();
                }
                if(!CheckID(UserIDEditText.getText().toString()))
                {
                    available = false;
                    Toast.makeText(TicketRecordActivity.this,"身份證字號不合規定",Toast.LENGTH_SHORT).show();
                }
                if(TicketCodeEditText.getText().toString().equals(""))
                {
                    available = false;
                    Toast.makeText(TicketRecordActivity.this,"訂票代碼不可為空",Toast.LENGTH_SHORT).show();
                }
                if(available)
                {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String cmd = getString(R.string.DB_Root_name) + "getTicketRecord.php?" +
                                    "User_ID=" + UserIDEditText.getText().toString() + "&&"+
                                    "TicketID=" + TicketCodeEditText.getText().toString();
                            String result = DB_Connector.Execute(cmd,TicketRecordActivity.this);
                            Log.e("Command",cmd);
                            Log.e("Result",result);
                            if(result != "Null")
                            {
                                try
                                {
                                    JSONObject jsonObject = new JSONObject(result);
                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject row = jsonArray.getJSONObject(i);
                                        Map<String,String> rowMap = new HashMap<>();
                                        rowMap.put("TicketDate",row.getString("ticket_date"));
                                        rowMap.put("TrainNumber",row.getString("train_number"));
                                        rowMap.put("SeatNumber",row.getString("seat_number"));
                                        rowMap.put("StartStation",StationIDToNameMap.get(row.getString("starting_station_ID")));
                                        rowMap.put("EndStation",StationIDToNameMap.get(row.getString("ending_station_ID")));
                                        rowMap.put("OwnerID",UserIDEditText.getText().toString());
                                        rowMap.put("TicketID",TicketCodeEditText.getText().toString());
                                        data.add(rowMap);
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            TicketRecordListViewAdapter ticketRecordListViewAdapter = new TicketRecordListViewAdapter(data);
                                            TicketRecordListView.setAdapter(null);
                                            TicketRecordListView.setAdapter(ticketRecordListViewAdapter);
                                        }
                                    });
                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                            }
                        }
                    });
                    thread.start();
                }

            }
        });

    }

    void InitialFindViewByID()
    {
        UserIDEditText = findViewById(R.id.UserIDEditText_ID);
        SearchButton = findViewById(R.id.SearchButton_ID);
        TicketRecordListView = findViewById(R.id.TicketRecordListView_ID);
        TicketCodeEditText = findViewById(R.id.TicketCodeEditText_ID);
        RegressionButton = findViewById(R.id.RegressionButton_ID);
    }

    void MapInitial()
    {
        IDCheckMap.put('A',1);
        IDCheckMap.put('B',10);
        IDCheckMap.put('C',19);
        IDCheckMap.put('D',28);
        IDCheckMap.put('E',37);
        IDCheckMap.put('F',46);
        IDCheckMap.put('G',55);
        IDCheckMap.put('H',64);
        IDCheckMap.put('I',39);
        IDCheckMap.put('J',73);
        IDCheckMap.put('K',82);
        IDCheckMap.put('L',2);
        IDCheckMap.put('M',11);
        IDCheckMap.put('N',20);
        IDCheckMap.put('O',48);
        IDCheckMap.put('P',29);
        IDCheckMap.put('Q',38);
        IDCheckMap.put('R',47);
        IDCheckMap.put('S',56);
        IDCheckMap.put('T',65);
        IDCheckMap.put('U',74);
        IDCheckMap.put('V',83);
        IDCheckMap.put('W',21);
        IDCheckMap.put('X',3);
        IDCheckMap.put('Y',12);
        IDCheckMap.put('Z',30);
    }

    boolean CheckID(String inputID)
    {
        if(inputID.length()!=10)
        {
            return false;
        }else
        {
            int Result = IDCheckMap.get(inputID.charAt(0)) +
                    Character.getNumericValue(inputID.charAt(1)) * 8 +
                    Character.getNumericValue(inputID.charAt(2)) * 7 +
                    Character.getNumericValue(inputID.charAt(3)) * 6 +
                    Character.getNumericValue(inputID.charAt(4)) * 5 +
                    Character.getNumericValue(inputID.charAt(5)) * 4 +
                    Character.getNumericValue(inputID.charAt(6)) * 3 +
                    Character.getNumericValue(inputID.charAt(7)) * 2 +
                    Character.getNumericValue(inputID.charAt(8)) +
                    Character.getNumericValue(inputID.charAt(9));

            return Result % 10 == 0;

        }
    }

    void setRegressionButton()
    {
        RegressionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuccessTime = 0;
                FailTime = 0;
                Thread test =  new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<testTime;i++)
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UserIDEditText.setText("");
                                    TicketCodeEditText.setText("");
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Initial finish
                                    Toast.makeText(TicketRecordActivity.this,"Initial Finish",Toast.LENGTH_SHORT).show();
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UserIDEditText.setText("A123456789");
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TicketCodeEditText.setText("5951");
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String ExpectRequest = "http://192.168.43.19/taiwan_railway_system/getTicketRecord.php?User_ID=A123456789&&TicketID=5951";
                                    StringBuilder SB = new StringBuilder();
                                    SB.append("Expecting Request : "+ ExpectRequest + "\n");
                                    SB.append("Regression Request : ");
                                    String cmd = getString(R.string.DB_Root_name) + "getTicketRecord.php?" +
                                            "User_ID=" + UserIDEditText.getText().toString() + "&&"+
                                            "TicketID=" + TicketCodeEditText.getText().toString();
                                    SB.append(cmd);
                                    if(ExpectRequest.equals(cmd))
                                    {
                                        SB.append("\n\nResult : Success");
                                        SuccessTime++;
                                    }
                                    else
                                    {
                                        SB.append("Result : Failed");
                                        FailTime++;
                                    }
                                    Toast.makeText(TicketRecordActivity.this,SB.toString(),Toast.LENGTH_SHORT).show();
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TicketRecordActivity.this,"Test Result\n"+
                                        "Succes Time" + String.valueOf(SuccessTime) + ", Rate : " + String.valueOf((float)SuccessTime/testTime) + "\n" +
                                        "Failed Time" + String.valueOf(FailTime) + ", Rate : " + String.valueOf((float)FailTime/testTime) + "\n" +
                                        "In " + String.valueOf(testTime) + " times ",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                test.start();
                //testStart

            }
        });
    }

}
