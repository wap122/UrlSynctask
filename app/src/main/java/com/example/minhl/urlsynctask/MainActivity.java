package com.example.minhl.urlsynctask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.tv);
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this, "Mạng chưa bật", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Mạng chưa đc kết nốt", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Mạng sida rồi", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(this, "Xài Wifi chùa dc rồi đó", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void downloadImage(View view) {
        if (!checkConnection()) {
            return;
        }
        String url = "http://cms.kienthuc.net.vn/zoom/1000/uploaded/nguyenanhson/2016_04_29/4/hot-girl-tra-sua-dep-nhu-nang-mai-tai-da-lat-hinh-5.jpg";
        DownloadImage downloadImage = new DownloadImage(imageView);
        downloadImage.execute(url);

    }

    public void downloadText(View view) {
        if (!checkConnection()) {
            return;
        }
        String url = "https://pastebin.com/raw/0RHGYUnh";
        DownloadText downloadText = new DownloadText(textView);
        downloadText.execute(url);
    }
}
