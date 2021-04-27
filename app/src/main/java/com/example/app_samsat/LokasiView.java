package com.example.app_samsat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class LokasiView extends AppCompatActivity {

    TextView nama,alamat,nomor,info;


    VideoView imageView;

    Button btn_nav, btn_call;



    //maps metod
    Uri gmmIntentUri;
    String latitude;
    String longitude;

    Intent mapIntent;
    String goolgeMap = "com.google.android.apps.maps";

    String btn_nomor;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_view);


        imageView = findViewById(R.id.img);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        nomor = findViewById(R.id.nomor);
        info = findViewById(R.id.info);

        btn_call = findViewById(R.id.btn_call);


        ref = FirebaseDatabase.getInstance().getReference().child("Lokasi");
        String LokasiKey = getIntent().getStringExtra("LokasiKey"); //key
        ref.child(LokasiKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    String ImageUrl= dataSnapshot.child("ImageUrl").getValue().toString();

                    String Nama = dataSnapshot.child("Nama").getValue().toString();
                    String Alamat = dataSnapshot.child("Alamat").getValue().toString();
                    String Nomor = dataSnapshot.child("Nomor").getValue().toString();
                    String Info = dataSnapshot.child("Info").getValue().toString();


                    //implementation

                    String Video = dataSnapshot.child("Video").getValue().toString();
                    Uri uri = Uri.parse(Video);
                    imageView.setVideoURI(uri);
                    imageView.start();
                    imageView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });

                    nama.setText(Nama);
                    alamat.setText(Alamat);
                    nomor.setText(Nomor);
                    info.setText(Info);

                    //ext
                    btn_nomor = dataSnapshot.child("Nomor").getValue().toString();
                    latitude = dataSnapshot.child("Latitude").getValue().toString();
                    longitude = dataSnapshot.child("Longitude").getValue().toString();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void onDialBUtton(View view) {
        String number = btn_nomor;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }

    public void onNav(View view) {

        if (view.getId() == R.id.btn_nav) {
            gmmIntentUri = Uri.parse("google.navigation:q=" + latitude+","+longitude);
            mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage(goolgeMap);
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                Toast.makeText(LokasiView.this, "Google Maps Belum Terinstall", Toast.LENGTH_SHORT).show();
            }

        }

    }
}