package com.example.grade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class gradeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var courses: ArrayList<Course>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        viewManager = LinearLayoutManager(this)
        courses = generateCourses()
        viewAdapter = CoursesRVAdapter(courses)

        recyclerView = findViewById<RecyclerView>(R.id.ListCourses).apply {
            // use this setting to improve performance if you know that changes
            // in content do not changeFormatGrade the layout size of the RecyclerView
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
    }

    /**The three little dots at the top right hand corner in the action bar*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_grade, menu)
        return true
    }

    //When the little menu button is selected
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        changeGradesFormat()
        return super.onOptionsItemSelected(item)
    }

    fun generateCourses(): ArrayList<Course> {

        var randomNumberOfCourse = Random.nextInt(1, 5)
        var courses = ArrayList<Course>()

        for (i in 0..randomNumberOfCourse) {
            courses.add(Course.generateARandomCourse(i + 1))
        }

        return courses
    }


    fun changeGradesFormat() {

        for (course in courses) {
            course.changeGradeFormat()
        }
        //Notify the RecyclerView to refresh the grades being displayed
        viewAdapter.notifyDataSetChanged()
    }
}
