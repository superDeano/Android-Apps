//Class containing all the logic for the grade activity


package com.example.grade.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grade.Classes.Course
import com.example.grade.rvAdapters.CoursesRVAdapter
import com.example.grade.R
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

        //Setting up the recycler view for the courses
        recyclerView = findViewById<RecyclerView>(R.id.ListCourses).apply {
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

            if (item?.itemId == R.id.changeGradeFormat_settings) {
                changeGradesFormat()
            }
        return super.onOptionsItemSelected(item)
    }

    private fun generateCourses(): ArrayList<Course> {

        val randomNumberOfCourses = Random.nextInt(1, 5)
        val courses = ArrayList<Course>()

        for (i in 0..randomNumberOfCourses) {
            courses.add(Course.generateARandomCourse(i + 1))
        }

        return courses
    }


    private fun changeGradesFormat() {
        //Changing the data the recycler view is receiving to change the format
        for (course in courses) {
            course.changeGradeFormat()
        }
        //Notify the RecyclerView to refresh the grades being displayed
        viewAdapter.notifyDataSetChanged()
    }
}
