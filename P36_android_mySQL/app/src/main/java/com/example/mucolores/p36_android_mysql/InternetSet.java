package com.example.mucolores.p36_android_mysql;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetSet extends AsyncTask<String,Void,String> //AsyncTask<傳入值型態, 更新進度型態, 結果型態>
{
    private StringBuilder sb = new StringBuilder();
    public AsyncResponse asyncResponse = null;

    @Override
    protected String doInBackground(String... strings) {
        try
        {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader BR = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String tmpLine;
            while ((tmpLine = BR.readLine()) != null)
            {
                sb.append(tmpLine);
                Log.i("Data", tmpLine);
            }
            urlConnection.disconnect();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        asyncResponse.processFinish(result);
    }
}
