package com.anukul.receivedatajson;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchDataBGTask extends AsyncTask<Void,Void,Void> {

    String jsondata = "";

    String singleParsed = "";
    String parsedData = "";
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://api.myjson.com/bins/160brn");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                jsondata = jsondata + line;
            }

            JSONArray jsonArray = new JSONArray(jsondata);
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                singleParsed =  "Name:" + jsonObject.get("name") + "\n"+
                                "Password:" + jsonObject.get("password") + "\n"+
                                "Contact:" + jsonObject.get("contact") + "\n"+
                                "Country:" + jsonObject.get("Country") + "\n";

                Log.e("data",singleParsed);
                parsedData = parsedData + singleParsed + "\n";

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.jsonDataTv.setText(this.parsedData);
    }
}
