package com.example.mucolores.db_2019;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DB_Connecter {

    private static StringBuilder SB;

    static String updataingData(String inputUrl) {
        try {
            URL url = new URL(inputUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader,8);

            String tmpLine;
            SB = new StringBuilder();
            while((tmpLine = bufferedReader.readLine())!=null)
            {
                SB.append(tmpLine);
            }

            JSONObject allJsonObject = new JSONObject(SB.toString());
            JSONArray jsonArray = allJsonObject.getJSONArray("data");

            SB = null;
            SB = new StringBuilder();

            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SB
                .append("PokemonID : ").append(jsonObject.get("PokemonID")).append("\n")
                .append("PokemonName : ").append(jsonObject.get("PokemonName")).append("\n")
                .append("Type : ").append(jsonObject.get("Type")).append("\n")
                .append("Attack : ").append(jsonObject.get("Attack")).append("\n")
                .append("Defense : ").append(jsonObject.get("Defense")).append("\n")
                .append("HP : ").append(jsonObject.get("HP")).append("\n")
                .append("Level_x : ").append(jsonObject.get("Level_x")).append("\n")
                .append("SkillID : ").append(jsonObject.get("SkillID")).append("\n")
                .append("SkillName : ").append(jsonObject.get("SkillName")).append("\n")
                .append("DefSkill : ").append(jsonObject.get("DefSkill")).append("\n")
                .append("HitRate : ").append(jsonObject.get("HitRate")).append("\n")
                .append("SkillAttack : ").append(jsonObject.get("SkillAttack")).append("\n")
                .append("\n");
            }
            httpURLConnection.disconnect();
            inputStream.close();

        } catch (IOException e) {
            Log.e("SQL Error",e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("JSON Error",e.toString());
            e.printStackTrace();
        }
        return SB.toString();
    }
}
