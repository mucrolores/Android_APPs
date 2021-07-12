package com.example.taiwanrailway_booking_system;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class DB_Connector {

    private static StringBuilder SB = new StringBuilder();

    public static String Execute(String inputURL,final Context context)
    {
        Handler handler = new Handler(context.getMainLooper());
        try{
            String[] RP = inputURL.split("[?]");
            byte[] postData = {};
            Log.e("Url Part",RP[0]);
            if(RP.length>1)
            {
                Log.e("Parameter",RP[1]);
                postData = RP[1].getBytes(StandardCharsets.UTF_8);
            }
            int PostDataLength = postData.length;

            URL url = new URL(RP[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Contnet-Length",Integer.toString(PostDataLength));

            try( DataOutputStream wr = new DataOutputStream( httpURLConnection.getOutputStream())) {
                wr.write( postData );
            }

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader,16);

            String tmpLine;
            SB = new StringBuilder();
            while((tmpLine = bufferedReader.readLine()) != null)
            {
                SB.append(tmpLine);
            }
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SQL Error",e.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,"Internet Connection Error",Toast.LENGTH_LONG).show();
                }
            });
        }
        return SB.toString();
    }
}
