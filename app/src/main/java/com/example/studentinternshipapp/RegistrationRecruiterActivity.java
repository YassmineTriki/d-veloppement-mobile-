package com.example.studentinternshipapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;

public class RegistrationRecruiterActivity extends AppCompatActivity {

    private EditText emailReg;
    private EditText passReg;
    private EditText companyName;
    private Button btnReg;
    private Button btnLogin;
    //Firebase authentification
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_recruiter);
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance("https://student-internship-app-e309c-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Recruiters");
        RegistrationRecruiter();
    }


    private void RegistrationRecruiter(){
        emailReg=findViewById(R.id.email_registration);
        passReg=findViewById(R.id.registration_password);
        btnReg=findViewById(R.id.btn_registration);
        btnLogin=findViewById(R.id.btn_login);
        companyName=findViewById(R.id.comp_name);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailReg.getText().toString().trim();
                String pass=passReg.getText().toString().trim();
                String compName=companyName.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    emailReg.setError("requirred");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    passReg.setError("requirred");
                    return;
                }
                if(TextUtils.isEmpty(compName)){
                    passReg.setError("requirred");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @SuppressLint("Wrongconstant")
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUser=task.getResult().getUser();
                                DatabaseReference nouveau=mDatabase.child(firebaseUser.getUid());
                                nouveau.child("email").setValue(email);
                                nouveau.child("pass").setValue(pass);
                                nouveau.child("compName").setValue(compName);
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            //Toast.makeText(getApplicationContext(),"Enregistrement réussi", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}