package com.example.grade.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.grade.R
import com.example.grade.SharedPreferenceHelper
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class profileActivity : AppCompatActivity() {
    private lateinit var sharedPreference: SharedPreferenceHelper
    private lateinit var nameTFLayout: TextInputLayout
    private lateinit var nameTextField: TextInputEditText
    private lateinit var ageTFLayout: TextInputLayout
    private lateinit var ageTextField: TextInputEditText
    private lateinit var idTFLayout: TextInputLayout
    private lateinit var idTextField: TextInputEditText
    private lateinit var saveProfileButt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)
        initView()

        saveProfileButt.setOnClickListener {
            saveProfileInfo()
        }

        loadProfileInfo()
    }


    private fun initView() {
        nameTextField = findViewById(R.id.nameTF)
        ageTextField = findViewById(R.id.ageTF)
        idTextField = findViewById(R.id.idTF)
        saveProfileButt = findViewById(R.id.saveProfileButt)
        nameTFLayout = findViewById(R.id.nameTFLayout)
        ageTFLayout = findViewById(R.id.ageTFLayout)
        idTFLayout = findViewById(R.id.idTFLayout)
        sharedPreference = SharedPreferenceHelper(this)
    }

    private fun setErrorTextFields() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_profile, menu)
        menuInflater.inflate(R.menu.menu_delete_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.profileMenu_delete) {
            deleteAllInfo()
        } else if (item?.itemId == R.id.profileMenu_edit) {
            enableEditMode()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun enableEditMode() {
        saveProfileButt.isVisible = true
        nameTextField.isEnabled = true
        ageTextField.isEnabled = true
        idTextField.isEnabled = true
    }

    private fun disableEditMode() {
        saveProfileButt.isVisible = false
        nameTextField.isEnabled = false
        ageTextField.isEnabled = false
        idTextField.isEnabled = false
    }

    private fun loadProfileInfo() {
        if (!sharedPreference.noInfoSaved()) {
            disableEditMode()
            nameTextField.setText(sharedPreference.getProfileName())
            ageTextField.setText(sharedPreference.getAge())
            idTextField.setText(sharedPreference.getProfileID())
        } else {
            enableEditMode()
            clearTextFields()
        }
    }

    private fun saveProfileInfo() {

        if (nameFormatIsCorrect() && ageFormatIsCorrect() && idFormatIsCorrect()) {
            sharedPreference.saveProfileName(nameTextField.text.toString())
            sharedPreference.saveAge(ageTextField.text.toString())
            sharedPreference.saveProfileID(idTextField.text.toString())
            Toast.makeText(
                applicationContext,
                "Profile Info Saved", Toast.LENGTH_SHORT
            ).show()

            disableEditMode()
        } else {
            Toast.makeText(
                applicationContext,
                "One or more fields is incorrect",
                Toast.LENGTH_SHORT
            )
        }
    }

    private fun clearTextFields() {
        nameTextField.text?.clear()
        ageTextField.text?.clear()
        idTextField.text?.clear()
    }

    private fun nameFormatIsCorrect(): Boolean {
        if (nameTextField.text.toString() == "") {
            nameTFLayout.error = "Enter Something!"
            return false
        }
        val name = nameTextField.text.toString().toCharArray()
        for (letter in name) {
            if (!Character.isLetter(letter) && letter != ' ') {
                nameTFLayout.error = "Please enter a better name"
                return false
            } else {
                nameTFLayout.error = ""
            }
        }
        return true
    }

    private fun ageFormatIsCorrect(): Boolean {
        if (ageTextField.text.toString() == "") {
            ageTFLayout.error = "Enter Something!"
            return false
        }

        val age = ageTextField.text.toString().toInt()
        if (age < 0) {
            ageTFLayout.error = "Please enter age bigger than 0"
            return false
        } else if (age > 99) {
            ageTFLayout.error = "Please enter age lower than 99"
            return false
        } else {
            ageTFLayout.error = ""
        }
        return true
    }

    private fun idFormatIsCorrect(): Boolean {
        if (idTextField.text.toString() == "") {
            idTFLayout.error = "Enter Something!"
            return false
        }
        if (idTextField.text.toString().length > 6) {
            idTFLayout.error = "ID is too big"
            return false
        } else if (idTextField.text.toString().length < 6) {
            idTFLayout.error = "ID is too short"
            return false
        } else {
            idTFLayout.error = ""
            return true
        }
    }

    private fun deleteAllInfo() {
        sharedPreference.deleteAllSavedInfo()
        loadProfileInfo()
        Toast.makeText(
            applicationContext,
            "All Profile Info Deleted, Please enter new information",
            Toast.LENGTH_LONG
        )
    }
}

