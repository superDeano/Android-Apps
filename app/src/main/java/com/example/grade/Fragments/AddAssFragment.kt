package com.example.grade.Fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.grade.DataBaseHelper
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialogView = inflater.inflate(R.layout.add_ass_fragment, container, false)
        initView(dialogView)
        addListenersOnButtons()
        return dialogView
    }

    fun setFAB(fab: FloatingActionButton) {
        this.fab = fab
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

    private fun addListenersOnButtons() {
        addAssButt.setOnClickListener {
            //            addAssignment()
        }
        cancelButt.setOnClickListener {
            cancelDialog()
        }
    }
}