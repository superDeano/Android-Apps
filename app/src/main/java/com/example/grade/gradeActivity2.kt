package com.example.grade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class gradeActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade2)

        /**Back button in the action bar to go to main activity*/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setting up the recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.ListCourses)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //Creating array of data for the recyclerView to populate
        var dataList = ArrayList<Course>()
        dataList = generateCourses()

        val recyclerViewAdapter = CoursesRVAdapter(dataList)
        recyclerView.adapter = recyclerViewAdapter
    }

    /**The three little dots at the top right hand corner in the action bar*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun generateCourses(): ArrayList<Course> {
        var randomNumberOfCourse = Random.nextInt(1, 5)
        var courses = ArrayList<Course>()

        for (i in 0..randomNumberOfCourse) {
            courses.add(Course.generateARandomCourse())
        }

        return courses
    }

}


