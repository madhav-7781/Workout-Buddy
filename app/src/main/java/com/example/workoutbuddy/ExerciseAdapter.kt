package com.example.workoutbuddy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(
    private var exercises: ArrayList<ExerciseModel>,
    private val dbHelper: DBHelper
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val repsSets: TextView = itemView.findViewById(R.id.tvRepsSets)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val ex = exercises[position]
        holder.name.text = ex.name
        holder.repsSets.text = "Sets: ${ex.sets}, Reps: ${ex.reps}"
        holder.btnDelete.setOnClickListener {
            dbHelper.deleteExercise(ex.id)
            updateData(dbHelper.getAllExercises())
        }
    }

    override fun getItemCount() = exercises.size

    fun updateData(newList: ArrayList<ExerciseModel>) {
        exercises = newList
        notifyDataSetChanged()
    }
}
