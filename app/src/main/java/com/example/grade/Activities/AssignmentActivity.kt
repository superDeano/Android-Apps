package com.example.grade.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.CustomCourse
import com.example.grade.DataBaseHelper
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
    lateinit var dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)
        dbHelper = DataBaseHelper(applicationContext)
        getCourseFromIntent()
        initViews()

        courseName.text = course.courseName
        courseID.text = course.courseID
        animateFab()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_assignments, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (R.id.action_deleteCourse == item?.itemId) {
            deleteCourse(); true
        }
        return super.onOptionsItemSelected(item)
    }

    fun getCourseFromIntent() {

        course = intent.extras?.get("COURSE") as CustomCourse
    }

    fun initViews() {
        courseName = findViewById(R.id.customCourseNameInCourseActivity)
        courseID = findViewById(R.id.customCourseIdInCourseActivity)
        viewManager = LinearLayoutManager(this)
        viewAdapter = AssignmentsRVAdapter(null)
        assignmentRV = findViewById(R.id.customAssignmentRV)
        floatingActionButton = findViewById(R.id.floatingAddCustomAssButton)

        floatingActionButton.setBackgroundColor(1)

        assignmentRV.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


    fun deleteCourse() {
        dbHelper.deleteCourseById(course.courseID)
        val goBackToCoursesList = Intent(this, MainActivity::class.java)
        startActivity(goBackToCoursesList)
    }

    fun animateFab() {
        assignmentRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0 && !floatingActionButton.isShown()) floatingActionButton.show()
                else if (dy > 0 && floatingActionButton.isShown()) floatingActionButton.hide()
            }

        })
    }
}
