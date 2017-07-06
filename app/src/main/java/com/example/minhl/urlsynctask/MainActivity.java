package com.example.minhl.urlsynctask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private Button btnShowImage;
    private DownloadImage downloadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.tv);
        btnShowImage = (Button) findViewById(R.id.btn_1);

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
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/003_Chile.jpg/320px-003_Chile.jpg";
        downloadImage = new DownloadImage(btnShowImage,imageView);
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

    public void stopSyncTask(View view) {
        downloadImage.cancel(true);
        btnShowImage.setEnabled(true);
    }
}
