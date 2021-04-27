package com.example.app_samsat;

import androidx.appcompat.app.AppCompatActivity;
//create and develop by yuvi sandika
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UserWbs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wbs);

        WebView web = (WebView) findViewById(R.id.web_view);

        web.setInitialScale(1);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setJavaScriptEnabled(true);

        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        web.loadUrl("http://jambisamsat.net/whistle/index.php");
        web.setWebViewClient(new WebViewClient());

    }
}