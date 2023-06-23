package com.hifzapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
public class StudentDetailsActivity extends AppCompatActivity {
    private Button buttonAssignTask;
    private Button buttonSeePreviousHistory;
    private int studentId;
    private String studentName;
    private String studentClass;
    private int studentAge;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdetails);

        buttonAssignTask = findViewById(R.id.buttonAssignTask);
        buttonSeePreviousHistory = findViewById(R.id.buttonSeePreviousHistory);

        // Retrieve the student details from the intent
        studentId = getIntent().getIntExtra("studentId", -1);
        studentName = getIntent().getStringExtra("studentName");
        studentClass = getIntent().getStringExtra("studentClass");
        studentAge = getIntent().getIntExtra("studentAge", -1);

        // Set the title of the activity as the student's name
        //setTitle(studentName);

        buttonAssignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailsActivity.this, AssignTasks.class);
                startActivity(intent);
            }
        });

        buttonSeePreviousHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click to see previous history
                seePreviousHistory();
            }
        });
    }


    private void seePreviousHistory() {
        // TODO: Implement the logic for seeing previous history
        // You can start a new activity to perform this functionality
        Intent intent = new Intent(StudentDetailsActivity.this, PreviousHistory.class);
        intent.putExtra("studentId", studentId);
        intent.putExtra("studentName", studentName);
        intent.putExtra("studentClass", studentClass);
        intent.putExtra("studentAge", studentAge);
        startActivity(intent);
    }
}
