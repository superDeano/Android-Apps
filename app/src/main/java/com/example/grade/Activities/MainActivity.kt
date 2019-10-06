//Class which contains the logic for the main activity
package com.example.grade.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.Assignment
import com.example.grade.Classes.CustomCourse
import com.example.grade.Fragments.AddCourseFragment
import com.example.grade.R
import com.example.grade.rvAdapters.CustomCoursesRVAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var fab: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_seeProfile -> {
            showProfile(); true
        }
        R.id.menuSeeRandomGrades -> {
            showRandomGrades(); true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        animateFloatingButton()
    }

    private fun showRandomGrades() {
        // Intent to move to the next activity
        val moveToGradeActivity = Intent(this, GradeActivity::class.java)
        startActivity(moveToGradeActivity)
    }

    private fun showProfile() {
        //Intent to show profile activity
        val showProfileActivity = Intent(this, profileActivity::class.java)
        startActivity(showProfileActivity)
    }

    private fun generateFewCourses(): ArrayList<CustomCourse> {
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

    private fun generateFewAssignments(): ArrayList<Assignment> {
        val assignments = ArrayList<Assignment>()
        for (i in 1..5) {
            assignments.add(Assignment.generateRandomAssignment(i))
        }
        return assignments
    }

    private fun initView() {
        fab = findViewById(R.id.floatingAddCustomCourseButton)
        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomCoursesRVAdapter(generateFewCourses())

        recyclerView = findViewById<RecyclerView>(R.id.customCoursesList).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        addListenerOnFloatingButton()
    }

    private fun animateFloatingButton() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0 && !fab.isShown())
                    fab.show()
                else if (dy > 0 && fab.isShown())
                    fab.hide()
            }

        })
    }

    private fun addListenerOnFloatingButton() {
        fab.setOnClickListener {
            val addCourseDialog = AddCourseFragment()

            addCourseDialog.show(supportFragmentManager, "Insert Course")

            fab.hide()
        }
    }
}
