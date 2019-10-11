//Class which contains the logic for the main activity
package com.example.grade.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.CustomCourse
import com.example.grade.Helpers.DataBaseHelper
import com.example.grade.Fragments.AddCourseFragment
import com.example.grade.R
import com.example.grade.Helpers.SharedPreferenceHelper
import com.example.grade.rvAdapters.CustomCoursesRVAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var welcome_Text: TextView
    private lateinit var totalAverageTextView: TextView
    private lateinit var fab: FloatingActionButton
    lateinit var dbHelper: DataBaseHelper
    lateinit var sharedPreference: SharedPreferenceHelper
    private lateinit var courses: ArrayList<CustomCourse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DataBaseHelper(applicationContext)
        initView()
        loadProfileUsername()

    }

    override fun onRestart() {
        super.onRestart()
        loadProfileUsername()
    }

    override fun onDestroy() {
        fab.hide()
        super.onDestroy()
    }

    override fun onPause() {
        fab.hide()
        super.onPause()
    }

    override fun onStop() {
        fab.hide()
        super.onStop()
    }

    override fun onResume() {
        fab.show()
        reloadCourses()
        super.onResume()
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
        R.id.action_deleteAllCourses -> {
            deleteAllCourses(); true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        reloadCourses()
        fab.show()
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


    private fun initView() {
        fab = findViewById(R.id.floatingAddCustomCourseButton)
        welcome_Text = findViewById(R.id.welcome_Text)
        totalAverageTextView = findViewById(R.id.averageCourseGradeTextView)
        sharedPreference = SharedPreferenceHelper(applicationContext)
        reloadCourses()
        addListenerOnFloatingButton()
    }

    private fun animateFloatingButton() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0 && !fab.isShown()) fab.show()
                else if (dy > 0 && fab.isShown()) fab.hide()
            }

        })
    }

    // ;)
    private fun loadProfileUsername() {
        if (sharedPreference.getProfile() == null || sharedPreference.getProfile().userName == "" || sharedPreference.getProfile().userName == null) {
            welcome_Text.setText("Average Grade")
        } else {
            var userName = sharedPreference.getProfile().userName
            userName += "'s average grade"
            welcome_Text.setText(userName)
        }
    }

    //To know when the fab is clicked
    private fun addListenerOnFloatingButton() {
        fab.setOnClickListener {
            val addCourseDialog = AddCourseFragment()
            addCourseDialog.setFAB(fab)
            // animate the fab
            fab.hide()
            addCourseDialog.show(supportFragmentManager, "Insert Course")
            Log.v("FAB", "trying to show button")

        }
    }

    //Display the courses again in the recycler view
    fun reloadCourses() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomCoursesRVAdapter(getCoursesFromDb())
        if (calculateAllCoursesAverage() == "N/A") {
            totalAverageTextView.text = calculateAllCoursesAverage()
        } else {
            totalAverageTextView.text = calculateAllCoursesAverage() + "%"
        }
        recyclerView = findViewById<RecyclerView>(R.id.customCoursesList).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    //Calculates the average for all the courses' average grade
    fun calculateAllCoursesAverage(): String {
        var sum = 0
        if (courses.size != 0) {
            for (course in courses) {
                if (course.courseAverage != null) {
                    sum += course.courseAverage!!.toInt()
                }
            }
            sum /= courses.size
            return sum.toString()
        } else return "N/A"
    }

    // Get the list of courses from the database
    private fun getCoursesFromDb(): ArrayList<CustomCourse>? {
        courses = dbHelper.getEverything()
        return courses
    }

    private fun deleteAllCourses() {
        dbHelper.deleteAllCourses()
        reloadCourses()
    }


}
