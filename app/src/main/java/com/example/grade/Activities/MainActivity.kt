//Class which contains the logic for the main activity
package com.example.grade.Activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.grade.R
import com.example.grade.SharedPreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var showProfileButt: Button
    private lateinit var showMyGradesButt: Button
    private lateinit var sharedPreference: SharedPreferenceHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreference = SharedPreferenceHelper(this)


        //Getting the reference for the show my grades button
        showMyGradesButt = findViewById(R.id.seeGradesButt)
        showProfileButt = findViewById(R.id.goToProfileButt)

        //Listener to know when the button is pressed onto
        showMyGradesButt.setOnClickListener {
            showAllGrades()

        }
        showProfileButt.setOnClickListener {
            showProfile()
        }


    }

    override fun onStart() {
        super.onStart()


        if (sharedPreference.noInfoSaved()) {
            showProfile()
            Toast.makeText(applicationContext, "No Profile Information Found!", Toast.LENGTH_LONG).show()
        } else if (sharedPreference.getProfileName() != null) {
            showProfileButt.text = (sharedPreference.getProfileName())
        }
    }

    fun showAllGrades() {
        // Intent to move to the next activity
        val moveToGradeActivity = Intent(this, gradeActivity::class.java)
        startActivity(moveToGradeActivity)
    }

    fun showProfile() {
        val showProfileActivity = Intent(this, profileActivity::class.java)
        startActivity(showProfileActivity)
    }
}
