package com.example.grade

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.assignmentsrv.view.*

class AssignmentsRVAdapter(val assignments: ArrayList<Assignment>) :
    RecyclerView.Adapter<AssignmentsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.assignmentsrv, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return assignments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.assignmentTitle.text = assignments[position].assignmentTitle

        holder.assignmentGrade.text = assignments[position].grade
        System.out.println("Assignment Grade "+ assignments[position].grade)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val assignmentTitle = itemView.assignmentNameTextView
        val assignmentGrade = itemView.assignmentGradeTextView
    }
}