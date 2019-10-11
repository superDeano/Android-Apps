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
    private lateinit var fab: FloatingActionButton
    lateinit var dbHelper: DataBaseHelper
    lateinit var sharedPreference: SharedPreferenceHelper

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
        R.id.action_dropDataBase -> {
            dbHelper.deleteDb()
           reloadCourses()
            true
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

    private fun loadProfileUsername() {
        if (sharedPreference.getProfile() == null || sharedPreference.getProfile().userName == "" || sharedPreference.getProfile().userName == null) {
            welcome_Text.setText("Average Grade")
        } else {
            var userName = sharedPreference.getProfile().userName
            userName += "'s average grade"
            welcome_Text.setText(userName)
        }
    }

    private fun addListenerOnFloatingButton() {
        fab.setOnClickListener {
            val addCourseDialog = AddCourseFragment()
            addCourseDialog.setFAB(fab)
            fab.hide()
            addCourseDialog.show(supportFragmentManager, "Insert Course")
//            reloadCourses()
            Log.v("FAB", "trying to show button")

        }
    }

    fun reloadCourses() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomCoursesRVAdapter(getCoursesFromDb())

        recyclerView = findViewById<RecyclerView>(R.id.customCoursesList).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    //TODO : Implement this
    fun calculateAllCoursesAverage() {

    }


    private fun getCoursesFromDb(): ArrayList<CustomCourse>? {
//        return dbHelper.getAllCourses()
        return dbHelper.getEverything()
    }

    private fun deleteAllCourses() {
        dbHelper.deleteAllCourses()
        reloadCourses()
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
}
