package com.example.storefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText eTVotersID , eTPass;
    Button btnSubmit , btnadmin;
    TextView tVDirectTORegister;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://storefirebase-85a28-default-rtdb.firebaseio.com/");
    //Login activity if registered
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eTVotersID = (EditText) findViewById(R.id.eTVotersId);
        eTPass = (EditText) findViewById(R.id.eTPass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tVDirectTORegister = (TextView) findViewById(R.id.tVDirectTORegister);
        btnadmin = (Button)findViewById(R.id.btnadmin);

        //Login using the voter id and pwd he created.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String voteid = eTVotersID.getText().toString();
                String pwd = eTPass.getText().toString();
                boolean flag=true;
                if(voteid.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this , "Please fill all the fields" , Toast.LENGTH_SHORT).show();
                    flag=false;
                }
                if(flag){
                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if(snapshot.hasChild(voteid)){
                                String getpwd = snapshot.child(voteid).child("pwd").getValue(String.class);
                                if(getpwd.equals(pwd)){
                                    Toast.makeText(LoginActivity.this , "COrrect" , Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(LoginActivity.this , "wrong pwd" , Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(LoginActivity.this , "No such user" , Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }


            }
        });

        //if not an user direct to register page
        tVDirectTORegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this , com.example.storefirebase.MainActivity.class);
                startActivity(i);
            }
        });

        //if admin means click this button
        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(LoginActivity.this , com.example.storefirebase.adminActivity.class);
                startActivity(i1);
            }
        });



    }
}