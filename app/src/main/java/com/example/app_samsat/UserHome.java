package com.example.app_samsat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
//create and develop by yuvi sandika
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class UserHome extends AppCompatActivity {

    Button exit, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        //lokasi
        RelativeLayout m1 = findViewById(R.id.m1);
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this,UserLokasiList.class);
                startActivity(intent);
            }
        });

        //cek pajak
        RelativeLayout m2 = findViewById(R.id.m2);
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this,UserCekPajak.class);
                startActivity(intent);
            }
        });

        //kontak
        RelativeLayout m3 = findViewById(R.id.m3);
        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this,About.class);
                startActivity(intent);
            }
        });

        //standar pelayanan
        RelativeLayout m4 = findViewById(R.id.m4);
        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHome.this,UserStandarPelayanan.class);
                startActivity(intent);
            }
        });


        logout = findViewById(R.id.m5);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserHome.this)
                        .setTitle("LOGOUT")
                        .setMessage("Kamu yakin ingin logout?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                Intent loginActivity = new Intent(getApplicationContext(),UserLogin.class);
                                startActivity(loginActivity);
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();


            }
        });

        //keluar
        exit = findViewById(R.id.m6);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserHome.this)
                        .setTitle("EXIT")
                        .setMessage("Kamu yakin ingin keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("EXIT")
                .setMessage("Kamu yakin ingin keluar?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}