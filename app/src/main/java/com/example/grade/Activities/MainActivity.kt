//Class which contains the logic for the main activity
package com.example.grade.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.grade.R
import com.example.grade.SharedPreferenceHelper

class MainActivity : AppCompatActivity() {

//    TODO: Clean
//    private lateinit var showProfileButt: Button
//    private lateinit var showMyGradesButt: Button
    private lateinit var sharedPreference: SharedPreferenceHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreference = SharedPreferenceHelper(this)


        //Getting the reference for the show my grades button
//        showMyGradesButt = findViewById(R.id.seeGradesButt)
//        showProfileButt = findViewById(R.id.goToProfileButt)

        //Listener to know when the button is pressed onto
//        showMyGradesButt.setOnClickListener {
//            showRandomGrades()
//
//        }
//        showProfileButt.setOnClickListener {
//            showProfile()
//        }


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

//    override fun onStart() {
//        super.onStart()

////Checks if there is any information stored in SharedPreference
//        if (sharedPreference.noInfoSaved()) {
//            //If there is none, it loads the profile activity immediately
//            showProfile()
//            //Telling the user there is no information
//            Toast.makeText(applicationContext, "No Profile Information Found!", Toast.LENGTH_LONG)
//                .show()
//
//        } else if (sharedPreference.getProfile().name != null) {
//            //There is information, thus displays the name in the button
//            showProfileButt.text = (sharedPreference.getProfile().name)
//        }
//    }

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
}
