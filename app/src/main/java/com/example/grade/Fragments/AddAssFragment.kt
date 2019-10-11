package com.example.grade.Fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.grade.Activities.AssignmentActivity
import com.example.grade.Classes.Assignment
import com.example.grade.Helpers.CheckingInputHelper
import com.example.grade.Helpers.DataBaseHelper
import com.example.grade.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddAssFragment : DialogFragment() {
    private var dbHelper: DataBaseHelper? = null
    lateinit var fab: FloatingActionButton
    private lateinit var addAssignmentNameTF: TextInputEditText
    private lateinit var addAssignmentNameLayout: TextInputLayout
    private lateinit var addAssGradeTF: TextInputEditText
    private lateinit var addAssGradeLayout: TextInputLayout
    private lateinit var cancelButt: Button
    private lateinit var addAssButt: Button
    private lateinit var courseId: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialogView = inflater.inflate(R.layout.add_ass_fragment, container, false)
        initView(dialogView)
        addListenersOnButtons()
        return dialogView
    }

    fun setFAB(fab: FloatingActionButton) {
        this.fab = fab
    }

    fun setCourseId(id: String) {
        this.courseId = id
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHelper = DataBaseHelper(activity)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        fab.show()
    }

    private fun cancelDialog() {
        dialog?.cancel()
    }

    private fun initView(view: View) {
        addAssignmentNameTF = view.findViewById(R.id.assNameTFFrag)
        addAssignmentNameLayout = view.findViewById(R.id.assNameTFLayoutFrag)
        addAssGradeTF = view.findViewById(R.id.assGradeTFFrag)
        addAssGradeLayout = view.findViewById(R.id.assGradeTFLayoutFrag)
        cancelButt = view.findViewById(R.id.cancelAssButt)
        addAssButt = view.findViewById(R.id.addAssButt)

    }

    private fun addAssignment() {
        if (CheckingInputHelper.checkNameFormat(addAssignmentNameTF, addAssignmentNameLayout) && CheckingInputHelper.checkPositiveIntegersOnly(addAssGradeTF, addAssGradeLayout)) {
            val assName = addAssignmentNameTF.text.toString()
            val assGrade = addAssGradeTF.text.toString()
            val assignment = Assignment(assName, assGrade, courseId)
            dbHelper!!.addAssignmentToCourse(assignment)
            cancelDialog()
            reloadingAssignmentList(assignment)
        }
    }

    private fun addListenersOnButtons() {
        addAssButt.setOnClickListener {
            addAssignment()
        }
        cancelButt.setOnClickListener {
            cancelDialog()
        }
    }


    private fun reloadingAssignmentList(assignment: Assignment) {
        val assignmentActivity = activity as AssignmentActivity
        var assignments = assignmentActivity.assignments

        if (assignments == null) {
            assignments = ArrayList()
           assignments.add(assignment)
        }else{
            assignments.add(assignment)
        }
        assignmentActivity.viewAdapter.notifyItemInserted(assignmentActivity.viewAdapter.itemCount + 1)
    }
}