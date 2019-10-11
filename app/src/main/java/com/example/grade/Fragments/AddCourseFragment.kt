package com.example.grade.Fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.grade.Activities.MainActivity
import com.example.grade.Helpers.CheckingInputHelper
import com.example.grade.Classes.CustomCourse
import com.example.grade.Helpers.DataBaseHelper

import com.example.grade.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AddCourseFragment : DialogFragment() {
    private lateinit var addCourseNameTF: TextInputEditText
    private lateinit var addCourseNameLayout: TextInputLayout
    private lateinit var addCourseIdTF: TextInputEditText
    private lateinit var addCourseIdLayout: TextInputLayout
    private lateinit var addCourseButton: Button
    private lateinit var cancelButton: Button
    var dbHelper: DataBaseHelper? = null
    lateinit var fab: FloatingActionButton

    companion object {
        fun newInstance(): AddCourseFragment {
            val fragment = AddCourseFragment()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_course_fragment, container, false)
        initView(view)
        return view
    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        dbHelper = DataBaseHelper(activity)
    }

    private fun initView(view: View) {
        //Initializing the views
        addCourseNameTF = view.findViewById(R.id.addingCourseNameTF)
        addCourseNameLayout = view.findViewById(R.id.addingCourseNameLayout)
        addCourseIdTF = view.findViewById(R.id.addingCourseIdTF)
        addCourseIdLayout = view.findViewById(R.id.addingCourseIdLayout)
        addCourseButton = view.findViewById(R.id.addCourseButt)
        cancelButton = view.findViewById(R.id.cancelAddButt)
        addListenersOnButtons()
    }

    private fun addListenersOnButtons() {

        addCourseButton.setOnClickListener {
            addCourse()
        }

        cancelButton.setOnClickListener {
            cancelDialog()
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        Log.v("Cancel Dialog", "onCancel Function called")
        fab.show()
        super.onCancel(dialog)

    }

    fun setFAB(fab: FloatingActionButton) {
        this.fab = fab
    }

    private fun cancelDialog() {
        dialog?.cancel()
        Log.v("Cancel button", "Pressed")

    }


    private fun addCourse() {
        if (formatInfoEnterredIsCorrect()) {
            dbHelper!!.insertCourse(CustomCourse(-1, addCourseNameTF.text.toString(), addCourseIdTF.text.toString(), null))
            //TODO: Close dialog then reload activity
            val act = activity as MainActivity
            act.reloadCourses()
            cancelDialog()
        }
    }

    private fun formatInfoEnterredIsCorrect(): Boolean {
        return (CheckingInputHelper.checkNameFormat(
            addCourseNameTF, addCourseNameLayout
        ) && CheckingInputHelper.checkNameFormat(addCourseIdTF, addCourseIdLayout))
    }
}
