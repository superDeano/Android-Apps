package com.example.grade

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AssignmentsRVAdapter(val assignmentsList: ArrayList<Assignment>) :
    RecyclerView.Adapter<AssignmentsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AssignmentsRVAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val v = LayoutInflater.from(parent.context).inflate(R.layout.assignmentsrv, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return assignmentsList.size
    }

    override fun onBindViewHolder(holder: AssignmentsRVAdapter.ViewHolder, position: Int) {

    }
}