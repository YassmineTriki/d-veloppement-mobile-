package com.example.studentinternshipapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {
    private Button btnAllJob;
    private Button btnPostJob;
    //private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAllJob=findViewById(R.id.btn_allJob);
        btnPostJob=findViewById(R.id.btn_postJob);
        //toolbar=findViewById(R.id.toolbar_home);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Student Internship App");
        btnAllJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AllJobActivity2.class));
            }
        });
        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));
            }
        });

    }



}