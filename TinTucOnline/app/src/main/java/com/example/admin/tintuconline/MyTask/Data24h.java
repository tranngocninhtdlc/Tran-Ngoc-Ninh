package com.example.admin.tintuconline.MyTask;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Data24h extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {

        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(strings[0]);

            InputStreamReader inputStreamReader =new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line=bufferedReader.readLine()) != null){
                content.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
