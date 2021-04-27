package com.example.app_samsat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//create and develop by yuvi sandika
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class UserRegis extends AppCompatActivity {


    private EditText userEmail,userPassword,userPassword2,userName,userPlat,userNomor;
    private ProgressBar loadingProgress;
    private Button regBtn;

    private FirebaseAuth mAuth;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regis);


        ref = FirebaseDatabase.getInstance().getReference().child("Users");

        userEmail = findViewById(R.id.email);
        userName = findViewById(R.id.username);

        userNomor = findViewById(R.id.nohp);

        userPlat = findViewById(R.id.noplat);

        userPassword = findViewById(R.id.password);
        userPassword2 = findViewById(R.id.password2);

        regBtn = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regBtn.setVisibility(View.INVISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();
                final String plat = userPlat.getText().toString();
                final String nomor = userNomor.getText().toString();


                if( email.isEmpty() || name.isEmpty() || plat.isEmpty() || password.isEmpty()  || !password.equals(password2)) {
                    // something goes wrong : all fields must be filled
                    // we need to display an error message
                    showMessage("Please Verify all fields") ;
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);

                }
                else {
                    // everything is ok and all fields are filled now we can start creating user account
                    // CreateUserAccount method will try to create the user if the email is valid

                    CreateUserAccount(email,name,plat,nomor,password);
                    upload(email,name,plat,nomor,password);
                }
            }
        });
    }

    private void upload(String email, String name, String plat, String nomor, String password) {
        final String key = ref.push().getKey();
        //progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Data Sedang Di Upload . . . . ");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("Email", email);
        hashMap.put("Nama", name);
        hashMap.put("Plat_kendaraan", plat);
        hashMap.put("No_Telp", nomor);


        ref.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), UserHome.class));
            }
        });
    }



    //membuat user
    private void CreateUserAccount(String email, final String name,final String plat,  String nomor,String password) {
        // this method create user account with specific email and password
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // user account created successfully
                            showMessage("Account created");
                            // after we created user account we need to update his profile picture and name
                            updateUserInfo(name,mAuth.getCurrentUser());
                        }
                        else
                        {
                            // account creation failed
                            showMessage("account creation failed" + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    //mengupdate user info
    private void updateUserInfo(String name, FirebaseUser currentUser) {
        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        currentUser.updateProfile(profleUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            // user info updated successfully
                            showMessage("Register Complete");
                            updateUI();
                        }

                    }
                });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(),UserHome.class);
        startActivity(homeActivity);
        finish();
    }

    // simple method to show toast message
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}