package com.example.minhl.urlsynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by minhl on 25/06/2017.
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    private Button btnTemp;

    public DownloadImage(Button btnTemp, ImageView imageView) {

        this.imageView = imageView;
        this.btnTemp = btnTemp;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urlImage = params[0];
        InputStream in = null;
        try {
            URL url = new URL(urlImage);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int resCode = httpURLConnection.getResponseCode();
            if (resCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            in = httpURLConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUill.closeQuiety(in);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            this.imageView.setImageBitmap(bitmap);
        } else {
            Log.d("Thong bao", "Ko tim dc du lieu: ");
        }
        btnTemp.setEnabled(true);
    }
}
