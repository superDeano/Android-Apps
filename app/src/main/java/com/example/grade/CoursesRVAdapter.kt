package com.example.grade

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoursesRVAdapter(val coursesList: ArrayList<Course>) : RecyclerView.Adapter<CoursesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.coursesrv, p0, false)
        return ViewHolder(view);
    }

    //The number of courses to display by the Recycler View
    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var list = holder.assignmentsList





        holder.courseTitle.text = coursesList[position].courseTitle
        holder.averageGradeForCourse.text = coursesList[position].returnAverageGrade().toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitle = itemView.findViewById<TextView>(R.id.titleCourseTextView)
        val assignmentsList = itemView.findViewById<RecyclerView>(R.id.assignmentsPerCourseRecyclerView)
        val averageGradeForCourse = itemView.findViewById<TextView>(R.id.averageCourseGradeTextView)

    }
}