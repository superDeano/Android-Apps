package com.example.grade.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.grade.Classes.CustomCourse
import com.example.grade.R

class AssignmentActivity : AppCompatActivity() {

    private lateinit var courseName: TextView
    private lateinit var course: CustomCourse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)

        courseName = findViewById(R.id.customCourseNameInCourseActivity)

        getCourseFromIntent()
        courseName.text = course.courseName
    }

    fun getCourseFromIntent() {
        course = intent.extras.get("COURSE") as CustomCourse
    }
}
