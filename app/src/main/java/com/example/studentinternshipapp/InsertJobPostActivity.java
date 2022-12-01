package com.example.studentinternshipapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.studentinternshipapp.Model.JobData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class InsertJobPostActivity extends AppCompatActivity {


    //private Toolbar toolbar;
    private EditText job_title;
    private EditText job_description;
    private EditText job_type;

    private Button btn_post_Job;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mJobPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);

        //toolbar=findViewById(R.id.insert_job_toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Post A Job");
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId=mUser.getUid();
        mJobPost=FirebaseDatabase.getInstance("https://student-internship-app-e309c-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Job Post").child(uId);
        InsertJob();

    }

    private void InsertJob(){
        job_title=findViewById(R.id.job_title);
        job_description=findViewById(R.id.job_description);
        job_type=findViewById(R.id.job_type);

        btn_post_Job=findViewById(R.id.btn_job_post);


        btn_post_Job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=job_title.getText().toString().trim();
                String description=job_description.getText().toString().trim();
                String type=job_type.getText().toString().trim();
                if(TextUtils.isEmpty(title)){
                    job_title.setError("Required");
                    return;
                }
                if(TextUtils.isEmpty(description)){
                    job_title.setError("Required");
                    return;
                }
                if(TextUtils.isEmpty(type)){
                    job_title.setError("Required");
                    return;
                }
                //push random key for the id of the job
                String id= mJobPost.push().getKey();
                //get Date
                String date= DateFormat.getDateInstance().format(new Date());
                JobData data=new JobData(title,description,type,id,date);
                mJobPost.child(id).setValue(data);
                Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));
            }
        });
    }


}