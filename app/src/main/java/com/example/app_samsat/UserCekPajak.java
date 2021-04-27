package com.example.app_samsat;

import androidx.appcompat.app.AppCompatActivity;
//create and develop by yuvi sandika
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UserCekPajak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cek_pajak);

        WebView web = (WebView) findViewById(R.id.web_view);
        web.loadUrl("http://jambisamsat.net/infopkb.html");
        web.setWebViewClient(new WebViewClient());

    }
}