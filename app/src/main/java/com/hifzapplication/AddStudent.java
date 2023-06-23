package com.hifzapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextAge, editTextClass;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Initialize views

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextClass = findViewById(R.id.editTextClass);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        // Initialize database helper
        databaseHelper = new DatabaseHelper(this);

        buttonAdd.setOnClickListener(v -> {
            // Retrieve entered student details

            String name = editTextName.getText().toString().trim();
            String ageString = editTextAge.getText().toString().trim();
            int age;
            String studentClass = editTextClass.getText().toString().trim();

            // Validate the inputs
            if ( name.isEmpty() || ageString.isEmpty() || studentClass.isEmpty()) {
                Toast.makeText(AddStudent.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Parse age
            try {
                age = Integer.parseInt(ageString);
            } catch (NumberFormatException e) {
                Toast.makeText(AddStudent.this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save student to the database
            boolean success = databaseHelper.addStudent( name, age, studentClass);
            if (success) {
                Toast.makeText(AddStudent.this, "Student added successfully", Toast.LENGTH_SHORT).show();

                finish(); // Finish the activity and go back to the previous screen
            } else {
                Toast.makeText(AddStudent.this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

