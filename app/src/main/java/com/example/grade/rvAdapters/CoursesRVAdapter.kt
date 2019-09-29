//Class which is responsible to link the view and data for each course


package com.example.grade.rvAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.Course
import com.example.grade.R
import kotlinx.android.synthetic.main.coursesrv.view.*

class CoursesRVAdapter(val courses: ArrayList<Course>) : RecyclerView.Adapter<CoursesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.coursesrv, p0, false)
        return ViewHolder(view)
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

        //The other nested adapter to display the list of assignments per course
        val childLayoutManager = LinearLayoutManager(
            holder.assignmentsRV.context,
            RecyclerView.VERTICAL, false
        )

        // Assignment RecyclerView adapter being set up
        holder.assignmentsRV.apply {
            layoutManager = childLayoutManager
            adapter = AssignmentsRVAdapter(course.assignments)
            setRecycledViewPool(viewPool)
        }

    }

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitle = itemView.findViewById<TextView>(R.id.titleCourseTextView)
        val assignmentsRV: RecyclerView = itemView.assignmentsPerCourseRecyclerView
        val averageGradeForCourse = itemView.findViewById<TextView>(R.id.averageCourseGradeTextView)

    }
}