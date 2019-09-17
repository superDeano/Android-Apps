//Class which contains the logic for the main activity
package com.example.grade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting the reference for the show my grades button
        val showMyGradesButt = findViewById<Button>(R.id.seeGradesButt)

        //Listener to know when the button is pressed onto
        showMyGradesButt.setOnClickListener {
            showAllGrades()

        }
    }

    fun showAllGrades() {
        // Intent to move to the next activity
        val moveToGradeActivity = Intent(this, gradeActivity::class.java)
        startActivity(moveToGradeActivity)
    }
}
