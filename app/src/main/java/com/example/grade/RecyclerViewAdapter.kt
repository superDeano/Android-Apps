package com.example.grade

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val coursesList: ArrayList<Course>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.recyclerviewadapter, p0, false)
        return ViewHolder(view);
    }

    //The number of courses to display by the Recycler View
    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.courseTitle.text = coursesList[position].courseTitle
        holder.averageGradeForCourse.text = coursesList[position].returnAverageGrade().toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitle = itemView.findViewById<TextView>(R.id.titleCourseTextView)
        val assignmentName = itemView.findViewById<TextView>(R.id.assignmentNameTextView)
        val assignmentGrade = itemView.findViewById<TextView>(R.id.assignmentGradeTextView)
        val averageGradeForCourse = itemView.findViewById<TextView>(R.id.averageCourseGradeTextView)

    }
}