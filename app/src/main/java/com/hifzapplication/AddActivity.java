package com.hifzapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextAge;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        EditText editTextClass = findViewById(R.id.editTextClass);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        db = new DatabaseHelper(this);

        buttonSubmit.setOnClickListener(v -> addsudent());




    }
    public void addsudent(){
        String name = editTextName.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());
        int className = Integer.parseInt(editTextAge.getText().toString());



        Student obj = new Student(age , className,1,1,0,10,0,0,name);
        db.insertStudent(obj);

        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();




        db.close();

    }
}