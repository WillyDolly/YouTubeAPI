package com.popland.pop.youtubeapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ListYouTube extends AppCompatActivity {
String API_key = "AIzaSyAyrdiDf0tLplPdxjioYiIv_ZEw42eARyc";
    String playlistID ="PLnV5gfO-gDDYbgffOLe76PZSqBsMjfiNa";
//https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLnV5gfO-gDDYbgffOLe76PZSqBsMjfiNa&key=AIzaSyAyrdiDf0tLplPdxjioYiIv_ZEw42eARyc
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_you_tube);
        tv = (TextView)findViewById(R.id.Tv);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new DocJSON().execute("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+playlistID+"$maxResult=50"+"&key="+API_key);
            }
        });
    }
    class DocJSON extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }
    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}
