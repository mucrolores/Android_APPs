package com.example.taiwanrailway_booking_system;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BookingActivity extends AppCompatActivity {

    EditText IDEEdiText;
    TextView startStationTextView,destinationStationTextView;

    RadioButton trainNumberRadioButton,trainTimeRadioButton;
    Button decreaseTicketAmountButton,increaseTicketAmountButton;
    TextView tickAmountTextView;

    BookByTrainNumberFragment BBTNFragment;
    BookByTrainTimeFragment BBTTFragment;
    boolean currentStateIsNumber = true;
    FragmentTransaction fragmentTransaction;
    Integer ticketAmount;

    Button BookButton;

    Button RegressionButton;

    Map<Character,Integer> IDCheckMap = new HashMap<>();

    String TicketMessage;

    Map<String,String> StationNametoIDMap = new HashMap<>();
    ArrayList<String> StationNameArrayList = new ArrayList<>();

    int testTime = 5;
    int SuccessTime = 0;
    int FailTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        InitialFindViewById();

        MapInitial();

        BBTNFragment = new BookByTrainNumberFragment();
        BBTTFragment = new BookByTrainTimeFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FragmentContainer_ID,BBTNFragment);
        fragmentTransaction.commit();


        setTrainNumberRadioButton();
        setTrainTimeRadioButton();

        setBookButton();

        setRegressionButton();

        setDecreaseTicketAmountButton();
        setIncreaseTicketAmountButton();

        trainNumberRadioButton.setChecked(true);
        ticketAmount = Integer.valueOf(tickAmountTextView.getText().toString());

        getStationInfo();
    }

    void InitialFindViewById() {
        IDEEdiText = findViewById(R.id.IDEditText_ID);

        startStationTextView = findViewById(R.id.startStationTextView_ID);
        destinationStationTextView = findViewById(R.id.destinationStationTextView_ID);

        trainNumberRadioButton = findViewById(R.id.trainNumberRadioButton_ID);
        trainTimeRadioButton = findViewById(R.id.trainTimeRadioButton_ID);

        decreaseTicketAmountButton = findViewById(R.id.decreaseTicketAmountButton_ID);
        increaseTicketAmountButton = findViewById(R.id.increaseTicketAmountButton_ID);
        tickAmountTextView = findViewById(R.id.tickAmountTextView_ID);

        BookButton = findViewById(R.id.BookButton_ID);
        RegressionButton = findViewById(R.id.RegressionButton_ID);
    }

    void setStartStationTextView() {
        startStationTextView.setText("臺北");
        startStationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingActivity.this);
                builder.setTitle("選擇起始站");

                String[] station = new String[StationNameArrayList.size()];
                station = StationNameArrayList.toArray(station);

                builder.setItems(station, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startStationTextView.setText(StationNameArrayList.get(which));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    void setDestinationStationTextView() {
        destinationStationTextView.setText("高雄");
        destinationStationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingActivity.this);
                builder.setTitle("選擇終點站");

                String[] station = new String[StationNameArrayList.size()];
                station = StationNameArrayList.toArray(station);

                builder.setItems(station, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        destinationStationTextView.setText(StationNameArrayList.get(which));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    void setTrainNumberRadioButton() {
        trainNumberRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentTrainKind(true);
            }
        });
    }
    void setTrainTimeRadioButton() {
        trainTimeRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentTrainKind(false);
            }
        });
    }
    void setFragmentTrainKind(boolean ByNumber) {
        if(ByNumber && !currentStateIsNumber)
        {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(BBTTFragment);
            fragmentTransaction.add(R.id.FragmentContainer_ID,BBTNFragment);
            fragmentTransaction.commit();
            currentStateIsNumber = true;
        }
        else if(!ByNumber && currentStateIsNumber)
        {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(BBTNFragment);
            fragmentTransaction.add(R.id.FragmentContainer_ID,BBTTFragment);
            fragmentTransaction.commit();
            currentStateIsNumber = false;
        }
    }

    void setDecreaseTicketAmountButton() {
        decreaseTicketAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ticketAmount - 1 >= 1)
                {
                    ticketAmount = ticketAmount - 1;
                    tickAmountTextView.setText(String.valueOf(ticketAmount));
                }
            }
        });
    }
    void setIncreaseTicketAmountButton() {
        increaseTicketAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ticketAmount + 1 <= 10)
                {
                    ticketAmount = ticketAmount + 1;
                    tickAmountTextView.setText(String.valueOf(ticketAmount));
                }
            }
        });
    }

    void setBookButton() {
        BookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                boolean available = true;
                if(startStationTextView.getText().toString().equals(destinationStationTextView.getText().toString()))
                {
                    Toast.makeText(BookingActivity.this,"出發站與終點站不可同站",Toast.LENGTH_SHORT).show();
                    available = false;
                }
                if(IDEEdiText.getText().toString().equals(""))
                {
                    available = false;
                    Toast.makeText(BookingActivity.this,"身份證字號不可為空",Toast.LENGTH_SHORT).show();
                }
                if(!CheckID(IDEEdiText.getText().toString()))
                {
                    available = false;
                    Toast.makeText(BookingActivity.this,"身份證字號不合規定",Toast.LENGTH_SHORT).show();
                }
                if(currentStateIsNumber && BBTNFragment.getValue().get("TrainNum").equals(""))
                {
                    available = false;
                    Toast.makeText(BookingActivity.this,"車次不可為空",Toast.LENGTH_SHORT).show();

                }
                if(!currentStateIsNumber && BBTTFragment.getValue().get("TimeStart").equals(BBTTFragment.getValue().get("TimeEnd")))
                {
                    available = false;
                    Toast.makeText(BookingActivity.this,"時間範圍不可為0",Toast.LENGTH_SHORT).show();
                }
                if(available == true)
                {
                    if(currentStateIsNumber)
                    {
                        final ArrayList<String> TrainNumArrayList = new ArrayList<>(Arrays.asList(BBTNFragment.getValue().get("TrainNum").split(",")));
                        sb.append("Date : " + BBTNFragment.getValue().get("Date"));
                        for(int i=0;i<TrainNumArrayList.size();i++)
                        {
                            sb.append("\n" + "TrainNumber" + String.valueOf(i+1) + " : " + TrainNumArrayList.get(i));
                        }
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String cmd = getString(R.string.DB_Root_name) + "getTrainDataByNumber.php?" +
                                        "StartStation=" + StationNametoIDMap.get(startStationTextView.getText().toString()) + "&&" +
                                        "EndStation=" + StationNametoIDMap.get(destinationStationTextView.getText().toString()) + "&&" +
                                        "TrainNumberDate=" + BBTNFragment.getValue().get("Date");
                                for(int i=0;i<TrainNumArrayList.size();i++)
                                {
                                    cmd = cmd + "&&TrainNumber" + String.valueOf(i+1) + "=" + TrainNumArrayList.get(i);
                                }
                                Log.e("Request",cmd);
                                final String result = DB_Connector.Execute(cmd,BookingActivity.this);
                                Log.e("Result",result);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent();
                                        intent.setClass(BookingActivity.this,BookSelectActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("data",result);
                                        bundle.putString("UserID",IDEEdiText.getText().toString());
                                        bundle.putString("selfStartStationID",StationNametoIDMap.get(startStationTextView.getText().toString()));
                                        bundle.putString("SelfEndStationID",StationNametoIDMap.get(destinationStationTextView.getText().toString()));
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                        thread.start();
                    }
                    else if(!currentStateIsNumber)
                    {
                        sb.append("Date : " + BBTTFragment.getValue().get("Date") + "\n" +
                                "SATime : " + BBTTFragment.getValue().get("SATime") + "\n" +
                                "TimeStart : " + BBTTFragment.getValue().get("TimeStart") + "\n" +
                                "TimeEnd : " + BBTTFragment.getValue().get("TimeEnd") + "\n" +
                                "TrainKind : " + BBTTFragment.getValue().get("TrainKind") + "\n");

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String cmd = getString(R.string.DB_Root_name) + "getTrainDataByTime.php?" +
                                        "StartStation=" + StationNametoIDMap.get(startStationTextView.getText().toString()) +
                                        "&&" + "EndStation=" + StationNametoIDMap.get(destinationStationTextView.getText().toString()) +
                                        "&&" + "TrainNumberDate=" + BBTTFragment.getValue().get("Date") +
                                        "&&" + "SATime=" + BBTTFragment.getValue().get("SATime") +
                                        "&&" + "StartTime=" + BBTTFragment.getValue().get("TimeStart") +
                                        "&&" + "EndTime=" + BBTTFragment.getValue().get("TimeEnd") +
                                        "&&" + "TrainKind=" + BBTTFragment.getValue().get("TrainKind");
                                final String result = DB_Connector.Execute(cmd,BookingActivity.this);
                                Log.e("BookResult",result);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent();
                                        intent.setClass(BookingActivity.this,BookSelectActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("data",result);
                                        bundle.putString("UserID",IDEEdiText.getText().toString());
                                        bundle.putString("selfStartStationID",StationNametoIDMap.get(startStationTextView.getText().toString()));
                                        bundle.putString("SelfEndStationID",StationNametoIDMap.get(destinationStationTextView.getText().toString()));
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                        thread.start();
                    }
                    Toast.makeText(BookingActivity.this,sb.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    void getStationInfo() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            try {
                String Result = DB_Connector.Execute(getString(R.string.DB_Root_name) + "getStationList.php",BookingActivity.this);
                JSONObject jsonObject = new JSONObject(Result);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject row = jsonArray.getJSONObject(i);
                    String stationName = row.getString("station_name");
                    String stationID = row.getString("station_ID");
                    StationNametoIDMap.put(stationName,stationID);
                    StationNameArrayList.add(stationName);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setStartStationTextView();
                        setDestinationStationTextView();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        });
        thread.start();
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
                                    IDEEdiText.setText("");
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
                                    startStationTextView.setText("");
                                    destinationStationTextView.setText("");
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
                                    trainNumberRadioButton.setChecked(false);
                                    trainNumberRadioButton.setBackground(getDrawable(R.drawable.radio_button_unselected_background));
                                    trainNumberRadioButton.setTextColor(getResources().getColor(R.color.systemLightBlue_00BCD2));
                                    BBTNFragment.ClearValue();
                                    //Initial finish
                                    Toast.makeText(BookingActivity.this,"Initial Finish",Toast.LENGTH_SHORT).show();
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
                                    IDEEdiText.setText("A123456789");
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
                                    startStationTextView.setText("臺北");
                                    destinationStationTextView.setText("板橋");
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
                                    trainNumberRadioButton.callOnClick();
                                    trainNumberRadioButton.setBackground(getDrawable(R.drawable.radio_button_selected_background));
                                    trainNumberRadioButton.setTextColor(getResources().getColor(R.color.systemWhite));
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
                                    BBTNFragment.SetValue();

                                    String ExpectRequest = "http://192.168.43.19/taiwan_railway_system/getTrainDataByNumber.php?StartStation=1000&&EndStation=1020&&TrainNumberDate=2020-01-09&&TrainNumber1=1121";
                                    StringBuilder SB = new StringBuilder();
                                    SB.append("Expecting Request :  http://192.168.43.19/taiwan_railway_system/getTrainDataByNumber.php?StartStation=1000&&EndStation=1020&&TrainNumberDate=2020-01-09&&TrainNumber1=1121\n");
                                    SB.append("Regression Request : ");
                                    String cmd =  getString(R.string.DB_Root_name) + "getTrainDataByNumber.php?" +
                                            "StartStation=" + StationNametoIDMap.get(startStationTextView.getText().toString()) + "&&" +
                                            "EndStation=" + StationNametoIDMap.get(destinationStationTextView.getText().toString()) + "&&" +
                                            "TrainNumberDate=" + BBTNFragment.getValue().get("Date");
                                    ArrayList<String> TrainNumArrayList = new ArrayList<>(Arrays.asList(BBTNFragment.getValue().get("TrainNum").split(",")));
                                    for(int i=0;i<TrainNumArrayList.size();i++)
                                    {
                                        cmd = cmd + "&&TrainNumber" + String.valueOf(i+1) + "=" + TrainNumArrayList.get(i);
                                    }
                                    SB.append(cmd + "\n");

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
                                    Toast.makeText(BookingActivity.this,SB.toString(),Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(BookingActivity.this,"Test Result\n"+
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
