package com.example.mucolores.p36_android_mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    Button getDataBtn;
    ListView DataLV;

    private Get_SI SI = new Get_SI();
    private String PHPFileName = "modify_db.php?mode=1";
    private String All_url;
    private InternetSet setObject = new InternetSet();
    private String DataString;
    private ArrayList<String> IDData;
    private ArrayList<String> NameData;
    private ArrayList<Map<String,Object>> DataAL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For permission doing execution on MainThread
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        All_url = SI.GET_SERVER_URL() + PHPFileName;

        getDataBtn = findViewById(R.id.getDataBtn_ID);
        DataLV = findViewById(R.id.DataLV_ID);

        getDataBtn.setOnClickListener(getDataBtnOCL);

        IDData = new ArrayList<>();
        NameData = new ArrayList<>();
        setObject.asyncResponse = MainActivity.this;//pointing to MainActivity
        //setObject.execute("http://192.168.43.213/android/echo_test.php");
        setObject.execute("http://192.168.43.213/android/modify_db.php?mode=1");

    }

    View.OnClickListener getDataBtnOCL = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(IDData.isEmpty() && NameData.isEmpty())
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(DataString);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject tmpJSONObject = jsonArray.getJSONObject(i);
                        IDData.add(tmpJSONObject.getString("id"));
                        NameData.add(tmpJSONObject.getString("name"));
                    }

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
                DataAL = new ArrayList<>();

                for(int i = -1;i<IDData.size();i++)
                {
                    if(i==-1)
                    {
                        Map<String,Object> map = new LinkedHashMap<>();
                        map.put("id","id");
                        map.put("name","name");
                        DataAL.add(map);
                    }
                    else
                    {
                        Map<String,Object> map = new LinkedHashMap<>();
                        map.put("id",IDData.get(i));
                        map.put("name",NameData.get(i));
                        DataAL.add(map);
                    }
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,DataAL,R.layout.datalv_layout,
                        new String []{"id","name"},
                        new int[]{R.id.IDTV_ID,R.id.NameTV_ID});
                DataLV.setAdapter(simpleAdapter);
            }
            Toast.makeText(MainActivity.this, "connecting_"+All_url, Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void processFinish(String output) {
        DataString = output;
    }
}