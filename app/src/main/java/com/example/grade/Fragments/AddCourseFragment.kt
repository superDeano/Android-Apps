package com.example.grade.Fragments
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.grade.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class AddCourseFragment : DialogFragment() {
    private lateinit var addCourseNameTF: TextInputEditText
    private lateinit var addCourseNameLayout: TextInputLayout
    private lateinit var addCourseIdTF: TextInputEditText
    private lateinit var addCourseIdLayout: TextInputLayout
    private lateinit var addCourseButton: Button
    private lateinit var cancelButton: Button

    companion object {
        fun newInstance(): AddCourseFragment {
            val fragment = AddCourseFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.add_course_fragment, container, false)
        initView(view)
        return view
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

        }

        cancelButton.setOnClickListener {
            cancelDialog()
        }
    }

    private fun cancelDialog() {
//        val dialog = getDialog()
//        dialog?.dismiss()
//        activity?.fragmentManager?.popBackStack()
        dialog?.dismiss()
        Log.v("Cancel button", "Pressed")

    }


    private fun addCourse() {
//        TODO : Implement this
    }
}
