package com.example.grade.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.Assignment
import com.example.grade.Classes.CustomCourse
import com.example.grade.Helpers.DataBaseHelper
import com.example.grade.Fragments.AddAssFragment
import com.example.grade.R
import com.example.grade.rvAdapters.AssignmentsRVAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
/**
 * This is the class responsible for the logic behind the Assignment Activity
 *
 * */
class AssignmentActivity : AppCompatActivity() {

    private lateinit var courseName: TextView
    private lateinit var courseID: TextView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var course: CustomCourse
    private lateinit var assignmentRV: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var assignments: ArrayList<Assignment>
    lateinit var dbHelper: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)

        dbHelper = DataBaseHelper(applicationContext)

        getCourseFromIntent()
        initViews()
        loadCourseInfoAsTitle()
        animateFloatingActionButton()

    }

    // creating menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_assignments, menu)
        return super.onCreateOptionsMenu(menu)

    }

    // when menu is clicked on
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (R.id.action_deleteCourse == item?.itemId) {
            deleteCourse(); true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        floatingActionButton.hide()
        super.onDestroy()
    }

    override fun onPause() {
        floatingActionButton.hide()
        super.onPause()
    }

    override fun onStop() {
        floatingActionButton.hide()
        super.onStop()
    }


    fun getCourseFromIntent() {
        course = intent.extras?.get("COURSE") as CustomCourse
    }

    fun initViews() {
        //Showing the title of the course in the action toolbar
        this.title = "Assignments"
        courseName = findViewById(R.id.customCourseNameInCourseActivity)
        courseID = findViewById(R.id.customCourseIdInCourseActivity)
        floatingActionButton = findViewById(R.id.floatingAddCustomAssButton)
        assignments = ArrayList()
        loadAssignments()
        addListenerOnFab()
    }

    //Display the title and code of the course
    private fun loadCourseInfoAsTitle() {
        courseName.text = course.courseName
        courseID.text = course.courseCode
    }

    // Used to show all the assignments a course has
    private fun loadAssignments() {

        if (course.assignments != null) {
            assignments = course.assignments!!
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = AssignmentsRVAdapter(assignments)
        assignmentRV = findViewById(R.id.customAssignmentRV)
        assignmentRV.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    //Called to delete a course
    fun deleteCourse() {

        dbHelper.deleteCourseById(course.courseCode)

        val goBackToCoursesList = Intent(this, MainActivity::class.java)
        startActivity(goBackToCoursesList)

        //Telling the users that the course has been deleted
        Toast.makeText(this,"This course and its assignments have been deleted",Toast.LENGTH_SHORT).show()

    }


    /*
     * Used to hide and show the floating action button when scrolling
     *  */
    fun animateFloatingActionButton() {
        assignmentRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0 && !floatingActionButton.isShown()) floatingActionButton.show()
                else if (dy > 0 && floatingActionButton.isShown()) floatingActionButton.hide()
            }

        })
    }


    //To know when the floating action is pressed upon
    private fun addListenerOnFab() {
        floatingActionButton.setOnClickListener {
            val addAssDialog = AddAssFragment()
            addAssDialog.setFAB(floatingActionButton)
            //Hide the button before the dialog appears
            floatingActionButton.hide()
            addAssDialog.show(supportFragmentManager, "Insert Assignment")
            addAssDialog.setCourseId(course.id.toString())

        }
    }


}
