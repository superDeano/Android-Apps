package com.example.grade

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.coursesrv.view.*

class CoursesRVAdapter(val courses: ArrayList<Course>) :
    RecyclerView.Adapter<CoursesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.coursesrv, p0, false)
        return ViewHolder(view)
    }

    public fun notifyData(){

    }

    private var viewPool = RecyclerView.RecycledViewPool()

    //The number of courses to display by the Recycler View
    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val course = courses[position]

        holder.courseTitle.text = course.courseTitle

            holder.averageGradeForCourse.text = course.averageGrade
        System.out.println("Assignment Grade "+ course.averageGrade)

        val childLayoutManager = LinearLayoutManager(
            holder.assignmentsRV.context,
            RecyclerView.VERTICAL, false
        )

        holder.assignmentsRV.apply {
            layoutManager = childLayoutManager
            adapter = AssignmentsRVAdapter(course.assignments)
            setRecycledViewPool(viewPool)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitle = itemView.findViewById<TextView>(R.id.titleCourseTextView)
        val assignmentsRV: RecyclerView = itemView.assignmentsPerCourseRecyclerView
        val averageGradeForCourse = itemView.findViewById<TextView>(R.id.averageCourseGradeTextView)

    }
}