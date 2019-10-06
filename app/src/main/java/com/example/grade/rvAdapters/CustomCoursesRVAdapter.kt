package com.example.grade.rvAdapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Activities.AssignmentActivity
import com.example.grade.Classes.CustomCourse
import com.example.grade.R

class CustomCoursesRVAdapter(val customCourses: ArrayList<CustomCourse>?) :
    RecyclerView.Adapter<CustomCoursesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.custom_courses_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        when (customCourses == null) {
            true -> return 0
            false -> return customCourses!!.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (customCourses != null) {
            val customCourse = customCourses!![position]
            holder.customCourseName.text = customCourse.courseName
            holder.customCourseIdTV.text = customCourse.courseID
            holder.customCourseAverageGrade.text //TODO: fix this

            holder.cardview.setOnClickListener {

                val intent = Intent(holder.cardview.context, AssignmentActivity::class.java)
                intent.putExtra("COURSE", customCourses[position])
                startActivity(holder.cardview.context, intent, null)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customCourseName = itemView.findViewById<TextView>(R.id.customCourseName)
        val customCourseIdTV = itemView.findViewById<TextView>(R.id.customCourseId)
        val customCourseAverageGrade = itemView.findViewById<TextView>(R.id.customAverageGrade)
        val cardview = itemView.findViewById<CardView>(R.id.customCourseCardView)

    }
}