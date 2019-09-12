package com.example.grade

import android.app.ExpandableListActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.alespero.expandablecardview.ExpandableCardView
import kotlinx.android.synthetic.main.activity_grade.*

class gradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)

//        val card : ExpandableCardView = findViewById(R.id.profile)
//        card.setOnExpandedListener { view, isExpanded ->
//            Toast.makeText(applicationContext, if(isExpanded) "Expanded!" else "Collapsed!", Toast.LENGTH_SHORT).show()
//        }

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
}
