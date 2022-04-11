package com.example.storefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//Admin login page
public class adminActivity extends AppCompatActivity {
    EditText eTAdminUn , eTAdminPwd;
    Button btnAdminSubmit;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://storefirebase-85a28-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        eTAdminUn = (EditText) findViewById(R.id.eTAdminUn);
        eTAdminPwd = (EditText) findViewById(R.id.eTAdminPwd);
        btnAdminSubmit = (Button) findViewById(R.id.btnAdminSubmit);

    //Validate the admin
        btnAdminSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = eTAdminUn.getText().toString();
                String pwd = eTAdminPwd.getText().toString();
                boolean flag=true;
                if(un.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(adminActivity.this , "Please fill all the fields" , Toast.LENGTH_SHORT).show();
                    flag=false;
                }

                if(flag){
                    databaseReference.child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if(snapshot.hasChild(un)){
                                String getpwd = snapshot.child(un).child("pwd").getValue(String.class);
                                if(getpwd.equals(pwd)){
                                    Toast.makeText(adminActivity.this , "COrrect" , Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(adminActivity.this , "wrong pwd" , Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(adminActivity.this , "No such user" , Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                }

            }
        });






    }
}