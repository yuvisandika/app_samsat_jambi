package com.example.app_samsat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//create and develop by yuvi sandika
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserLogin extends AppCompatActivity {

    EditText email, password;
    Button login;
    ImageView admin;
    TextView go_regis;
    FirebaseAuth auth;

    FirebaseUser firebaseUser;
    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //check if user is null
        if (firebaseUser != null){
            Intent intent = new Intent(UserLogin.this, UserHome.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();

        go_regis = findViewById(R.id.go_regis);
        go_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, UserRegis.class);
                startActivity(intent);
            }
        });

        admin = findViewById(R.id.admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this,AdminLogin.class);
                startActivity(intent);
            }
        });

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog pd = new ProgressDialog(UserLogin.this);

                String str_email = email.getText().toString();
                String str_password = password.getText().toString();

                if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)){
                    Toast.makeText(UserLogin.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {

                    auth.signInWithEmailAndPassword(str_email, str_password)
                            .addOnCompleteListener(UserLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users")
                                                .child(auth.getCurrentUser().getUid());


                                        pd.setMessage("Please wait...");
                                        pd.show();


                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                pd.dismiss();
                                                Intent intent = new Intent(UserLogin.this, UserHome.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                finish();
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                pd.dismiss();
                                            }
                                        });
                                    } else {
                                        pd.dismiss();
                                        Toast.makeText(UserLogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}