package com.hifzapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;

import java.util.List;

public class AssignTasks extends AppCompatActivity {

    private TextView textViewStudentId, textViewStudentName, textViewStudentAge, textViewStudentClass, textViewSabaqTask, textViewSabaqi, textViewManzil;
    private EditText editTextSabaqPara, editTextSabaqSurah, editTextSabaqVerse, editTextManzilPara, editTextSabaqiPara;

    private int studentId;
    private String studentName;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigntasks);

        Intent intent = getIntent();
        if (intent != null) {
            studentId = intent.getIntExtra("studentId", -1);
            studentName = intent.getStringExtra("studentName");
            String studentClass = intent.getStringExtra("studentClass");
            int studentAge = intent.getIntExtra("studentAge", -1);
        }

        databaseHelper = new DatabaseHelper(this);

        // Initialize views
        textViewStudentId = findViewById(R.id.textViewStudentId);
        textViewStudentName = findViewById(R.id.textViewStudentName);
        textViewStudentAge = findViewById(R.id.textViewStudentAge);
        textViewStudentClass = findViewById(R.id.textViewStudentClass);
        editTextSabaqPara = findViewById(R.id.editTextSabaqPara);
        editTextSabaqSurah = findViewById(R.id.editTextSabaqSurah);
        editTextSabaqVerse = findViewById(R.id.editTextSabaqVerse);
        editTextManzilPara = findViewById(R.id.editTextManzilPara);
        editTextSabaqiPara = findViewById(R.id.editTextSabaqiPara);
        textViewSabaqTask = findViewById(R.id.textViewSabaq);
        textViewManzil = findViewById(R.id.textViewManzil);
        textViewSabaqi = findViewById(R.id.textViewSabaqi);

        // Retrieve and display the student details
        Student student = databaseHelper.getStudentById(studentId);
        if (student != null) {
            textViewStudentId.setText("Roll number: " + String.valueOf(student.getStudentId()));
            textViewStudentName.setText("Name: " + student.getName());
            textViewStudentAge.setText("Age: " + String.valueOf(student.getAge()));
            textViewStudentClass.setText("Class: " + student.getClassName());
        }

        List<Task> tasks = databaseHelper.getTasksByStudentIdAndTaskId(studentId);
        if (tasks.isEmpty()) {
            // If no tasks found, set the text to "No assigned task yet"
            textViewSabaqTask.setText("No assigned task yet");
            textViewManzil.setText("No assigned task yet");
            textViewSabaqi.setText("No assigned task yet");
            // Update the status text based on the sabaqStatus, manzilStatus, and sabaqiStatus
        } else {
            // Get the last task from the list (assuming tasks are ordered by entry date)
            Task lastTask = tasks.get(tasks.size() - 1);

            // Set the corresponding task values to the TextView elements
            textViewSabaqTask.setText("Para: " + lastTask.getSabaqPara() + ", Surah:" + lastTask.getSabaqSurah() + ", Verse: " + lastTask.getSabaqVerse());
            textViewManzil.setText("Para: " + lastTask.getManzilPara());
            textViewSabaqi.setText("Para: " + lastTask.getSabaqiPara());
        }

        Button buttonAssignTask = findViewById(R.id.buttonAssignTask);
        buttonAssignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sabaqParaNumber = editTextSabaqPara.getText().toString();
                String sabaqSurah = editTextSabaqSurah.getText().toString();
                String sabaqVerse = editTextSabaqVerse.getText().toString();
                String manzilParaNumber = editTextManzilPara.getText().toString();
                String sabaqiParaNumber = editTextSabaqiPara.getText().toString();

                // Update the task values in the database
                Task task = new Task();
                task.setStudentId(studentId);
                task.setSabaqPara(sabaqParaNumber);
                task.setSabaqSurah(sabaqSurah);
                task.setSabaqVerse(sabaqVerse);
                task.setManzilPara(manzilParaNumber);
                task.setSabaqiPara(sabaqiParaNumber);

                long taskId = databaseHelper.insertTask(AssignTasks.this, task);
                if (taskId != -1) {
                    Toast.makeText(AssignTasks.this, "Task assigned successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AssignTasks.this, "Failed to assign task.", Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });
    }
}