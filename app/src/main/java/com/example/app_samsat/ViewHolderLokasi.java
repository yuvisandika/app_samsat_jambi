package com.example.app_samsat;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//create and develop by yuvi sandika
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.getbase.floatingactionbutton.FloatingActionButton;


public class ViewHolderLokasi extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView txtView1;
    TextView txtView2;
    Button row_edit, row_hapus;

    View v;

    public ViewHolderLokasi(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageview1);
        txtView1 = itemView.findViewById(R.id.textView1);
        txtView2 = itemView.findViewById(R.id.textView2);

        row_edit = itemView.findViewById(R.id.row_edit);
        row_hapus = itemView.findViewById(R.id.row_hapus);

        v=itemView;
    }
}