package com.example.grade.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.CustomCourse
import com.example.grade.R
import com.example.grade.rvAdapters.AssignmentsRVAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AssignmentActivity : AppCompatActivity() {

    private lateinit var courseName: TextView
    private lateinit var courseID: TextView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var course: CustomCourse
    private lateinit var assignmentRV: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)

        getCourseFromIntent()
        initViews()

        courseName.text = course.courseName
        courseID.text = course.courseID

        assignmentRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0 && !floatingActionButton.isShown())
                    floatingActionButton.show()
                else if (dy > 0 && floatingActionButton.isShown())
                    floatingActionButton.hide()
            }

        })
    }


    fun getCourseFromIntent() {
        course = intent.extras.get("COURSE") as CustomCourse
    }

    fun initViews() {
        courseName = findViewById(R.id.customCourseNameInCourseActivity)
        courseID = findViewById(R.id.customCourseIdInCourseActivity)
        viewManager = LinearLayoutManager(this)
        viewAdapter = AssignmentsRVAdapter(course.assignments)
        assignmentRV = findViewById(R.id.customAssignmentRV)
        floatingActionButton = findViewById(R.id.floatingAddCustomAssButton)
        assignmentRV.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
