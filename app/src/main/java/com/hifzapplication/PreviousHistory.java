package com.hifzapplication;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;
import android.widget.RadioButton;

import java.util.List;

public class PreviousHistory extends AppCompatActivity {

    private TextView textViewStudentId, textViewStudentName, textViewStudentAge, textViewStudentClass, textViewTaskHistory;

    private int studentId;
    private String studentName;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previoushistory);

        // Retrieve the studentId and studentName from the intent
        studentId = getIntent().getIntExtra("studentId", -1);
        studentName = getIntent().getStringExtra("studentName");

        // Set the title of the activity as the student's name
        setTitle(studentName);
        databaseHelper = new DatabaseHelper(this);
        // Initialize views
        textViewStudentId = findViewById(R.id.textViewStudentId);
        textViewStudentName = findViewById(R.id.textViewStudentName);
        textViewStudentAge = findViewById(R.id.textViewStudentAge);
        textViewStudentClass = findViewById(R.id.textViewStudentClass);
        textViewTaskHistory = findViewById(R.id.textViewTaskHistory);


        // Retrieve and display the student details
        Student student = databaseHelper.getStudentById(studentId);
        if (student != null) {
            textViewStudentId.setText("Roll number: " +String.valueOf(student.getStudentId()));
            textViewStudentName.setText("Name: " +student.getName());
            textViewStudentAge.setText("Age: "+String.valueOf(student.getAge()));
            textViewStudentClass.setText("Class: "+student.getClassName());
        }

        List<Task> taskList = databaseHelper.getTasksByStudentIdAndTaskId(studentId);
        if (taskList.isEmpty()) {
            textViewTaskHistory.setText("No task history available");
        } else {
            StringBuilder taskHistoryBuilder = new StringBuilder();
            for (Task task : taskList) {
                taskHistoryBuilder.append("Task ID: ").append(task.getTaskId()).append("\n");
                taskHistoryBuilder.append("Sabaq Para: ").append(task.getSabaqPara()).append(", Surah: ").append(task.getSabaqSurah()).append(", Verse: ").append(task.getSabaqVerse()).append("\n");
                taskHistoryBuilder.append("Manzil Para: ").append(task.getManzilPara()).append("\n");
                taskHistoryBuilder.append("Sabaqi Para: ").append(task.getSabaqiPara()).append("\n\n");
            }
            textViewTaskHistory.setText(taskHistoryBuilder.toString());
        };

    }
}

