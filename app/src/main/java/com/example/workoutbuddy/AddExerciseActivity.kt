package com.example.workoutbuddy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddExerciseActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etSets: EditText
    private lateinit var etReps: EditText
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

        etName = findViewById(R.id.etName)
        etSets = findViewById(R.id.etSets)
        etReps = findViewById(R.id.etReps)
        fabAdd = findViewById(R.id.fabAdd)

        dbHelper = DBHelper(this)

        fabAdd.setOnClickListener {
            addExerciseToDatabase()
        }
        Log.d("AddExerciseActivity", "etName: $etName, etSets: $etSets, etReps: $etReps")

    }

    private fun addExerciseToDatabase() {
        // Get the input data
        val name = etName.text.toString().trim()
        val sets = etSets.text.toString().toIntOrNull() ?: 0
        val reps = etReps.text.toString().toIntOrNull() ?: 0

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter exercise name", Toast.LENGTH_SHORT).show()
            return
        }
        if (sets <= 0 || reps <= 0) {
            Toast.makeText(this, "Sets and Reps must be positive numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val success = dbHelper.insertExercise(name, sets, reps)
        if (success) {
            Toast.makeText(this, "Exercise added successfully", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Failed to add exercise", Toast.LENGTH_SHORT).show()
        }
    }
}
