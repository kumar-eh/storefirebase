package com.example.storefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText eTvotersid , eTName , eTPwd , eTPhone , eTMail;
Button btnSubmitReg;
TextView tVRegtoLogin;
FirebaseDatabase rootnode;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Register activity
        eTvotersid = findViewById(R.id.eTVotersIdReg);
        eTName = findViewById(R.id.etNameReg);
        eTPwd = findViewById(R.id.eTPassReg);
        eTPhone = findViewById(R.id.eTPhoneReg);
        eTMail = findViewById(R.id.eTMailReg);
        btnSubmitReg = findViewById(R.id.btnSubmitReg);
        tVRegtoLogin = findViewById(R.id.tVRegtoLogin);


//While clicking the register button
        btnSubmitReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eTName.getText().toString();
                String pwd = eTPwd.getText().toString();
                String phone = eTPhone.getText().toString();
                String mail = eTMail.getText().toString();
                String voteid = eTvotersid.getText().toString();
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("Users");
                Boolean temp = true;
                //Checking if any field is empty
                if((name.isEmpty() || pwd.isEmpty() || phone.isEmpty() || mail.isEmpty() || voteid.isEmpty())){
                    Toast.makeText(MainActivity.this , "Please fill all the fields" , Toast.LENGTH_SHORT).show();
                    temp = false;
                }
                if(pwd.length()<6){
                    Toast.makeText(MainActivity.this , "Password length should be atleast 6 characters" , Toast.LENGTH_SHORT).show();
                    temp = false;
                }
                if(temp) {
                    //TO add to db calling hte model class userhelper
                    UserHelper helperclass = new UserHelper(name, voteid, pwd, phone, mail);
                    reference.child(voteid).setValue(helperclass);
                    startActivity(new Intent(MainActivity.this , LoginActivity.class));
                }
            }
        });
                //If already an user we can call login page
                tVRegtoLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // Toast.makeText(MainActivity.this , "Login" , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this , LoginActivity.class));

                    }
                });

    }
}