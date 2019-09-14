package com.example.grade


import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_grade.*
import java.util.*


class gradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

        /** For the Recycle View */


        /**For the floating button from the toolbar*/
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun createTheCourses() {
        //TODO: Implement this function
    }

    fun allowRecycleViewToExist(recyclerView: RecyclerView){
        val layoutManager = LinearLayoutManager(this)
        layoutManager.isAutoMeasureEnabled = true
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setNestedScrollingEnabled(false)
    }
}
