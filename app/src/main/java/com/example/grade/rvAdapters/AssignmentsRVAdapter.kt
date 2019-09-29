//Adapter for each assignment per course
package com.example.grade.rvAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.Assignment
import com.example.grade.R
import kotlinx.android.synthetic.main.assignmentsrv.view.*

class AssignmentsRVAdapter(val assignments: ArrayList<Assignment>) :
    RecyclerView.Adapter<AssignmentsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.assignmentsrv, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return assignments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.assignmentTitle.text = assignments[position].assignmentTitle
        holder.assignmentGrade.text = assignments[position].grade

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assignmentTitle = itemView.assignmentNameTextView
        val assignmentGrade = itemView.assignmentGradeTextView
    }
}