package com.example.app_samsat;

import androidx.appcompat.app.AppCompatActivity;
//create and develop by yuvi sandika
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class UserStandarPelayanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_standar_pelayanan);


        WebView web = (WebView) findViewById(R.id.web_view);

        web.setInitialScale(1);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setJavaScriptEnabled(true);

        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        web.loadUrl("http://jambisamsat.net/fotoslide.php?id=1");
        web.setWebViewClient(new WebViewClient());


    }
}