//Class which contains the logic for the main activity
package com.example.grade.Activities

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.Assignment
import com.example.grade.Classes.CustomCourse
import com.example.grade.R
import com.example.grade.rvAdapters.CustomCoursesRVAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomCoursesRVAdapter(generateFewCourses())

        recyclerView = findViewById<RecyclerView>(R.id.customCoursesList).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_profile, menu)
        menuInflater.inflate(R.menu.main_menu_random_grades, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menuSeeProfile) {
            showProfile()
        } else if (item?.itemId == R.id.menuSeeRandomGrades) {
            showRandomGrades()
        }
        return super.onOptionsItemSelected(item)
    }


    fun showRandomGrades() {
        // Intent to move to the next activity
        val moveToGradeActivity = Intent(this, gradeActivity::class.java)
        startActivity(moveToGradeActivity)
    }

    fun showProfile() {
        //Intent to show profile activity
        val showProfileActivity = Intent(this, profileActivity::class.java)
        startActivity(showProfileActivity)
    }

    fun generateFewCourses(): ArrayList<CustomCourse> {
        val customCourses = ArrayList<CustomCourse>()

        for (i in 0..10) {

            customCourses.add(
                CustomCourse(
                    "Course Name $i",
                    "course ID $i",
                    generateFewAssignments()
                )
            )
        }
        return customCourses
    }

    fun generateFewAssignments(): ArrayList<Assignment> {
        val assignments = ArrayList<Assignment>()
        for (i in 1..5) {
            assignments.add(Assignment.generateRandomAssignment(i))
        }
        return assignments
    }
}
