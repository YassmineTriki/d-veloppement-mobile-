package com.example.studentinternshipapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class StudentLoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnLogin;
    private Button btnRegistration;
    private Button btnRegistrationRecruiter;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter_login);
        mAuth=FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);
        LoginFunction();
    }
    private void  LoginFunction(){
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.login_password);
        btnLogin=findViewById(R.id.btn_login);
        btnRegistration=findViewById(R.id.btn_reg);
        btnRegistrationRecruiter=findViewById(R.id.btn_reg_rec);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(mEmail)) {
                    email.setError("Required");
                }
                if (TextUtils.isEmpty(pass)) {
                    email.setError("Required");
                }
                mAuth.signInWithEmailAndPassword(mEmail,pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Successful you did login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        mDialog.dismiss();
                    }else{
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });
        btnRegistrationRecruiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrationRecruiterActivity.class));
            }
        });
    }



}