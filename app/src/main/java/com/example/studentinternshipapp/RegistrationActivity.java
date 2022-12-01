package com.example.studentinternshipapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegistrationActivity extends AppCompatActivity {

    private EditText emailReg;
    private EditText passReg;

    private Button btnReg;
    private Button btnLogin;
    //Firebase authentification
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();

        Registration();
    }


    private void Registration(){
        emailReg=findViewById(R.id.email_registration);
        passReg=findViewById(R.id.registration_password);
        btnReg=findViewById(R.id.btn_registration);
        btnLogin=findViewById(R.id.btn_login);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailReg.getText().toString().trim();
                String pass=passReg.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    emailReg.setError("requirred");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    passReg.setError("requirred");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
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