//Class which contains the logic for the main activity
package com.example.grade.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.grade.R


class MainActivity : AppCompatActivity() {

//    TODO: Clean


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
