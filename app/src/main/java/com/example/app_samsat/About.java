package com.example.app_samsat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//create and develop by yuvi sandika
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //menu 1 web
        RelativeLayout km1 = findViewById(R.id.km1);
        km1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://jambisamsat.net/index.html"));
                startActivity(intent);
            }
        });

        //menu 2 phone dibawah

        //menu 3 wa
        RelativeLayout km3 = findViewById(R.id.km3);
        km3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=628117404761"));
                startActivity(intent);
            }
        });

        //menu 4 ig
        RelativeLayout km4 = findViewById(R.id.km4);
        km4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/samsat.kota.jambi/"));
                startActivity(intent);
            }
        });

        //menu 5 wbs
        RelativeLayout km5 = findViewById(R.id.km5);
        km5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About.this,UserWbs.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(About.this, UserHome.class);
        startActivity(intent);
    }

    public void onDialBUtton(View view) {
        String number = "08117404761";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }
}