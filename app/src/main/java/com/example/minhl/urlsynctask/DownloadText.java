package com.example.minhl.urlsynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by minhl on 25/06/2017.
 */

public class DownloadText extends AsyncTask<String, Void, String> {
    private TextView textView;

    public DownloadText(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlText = params[0];
        InputStream in = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlText);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpURLConnection.getInputStream();
                br = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String s = null;
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
                return sb.toString();
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUill.closeQuiety(in);
            IOUill.closeQuiety(br);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s != null) {
            textView.setText(s);
        } else {
            Log.d("Thong bao", "Ko co du lieu ");
        }
    }
}
