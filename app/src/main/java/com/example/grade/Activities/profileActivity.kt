/*
* Class which holds the logic for the profile activity
* */
package com.example.grade.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.grade.Helpers.CheckingInputHelper
import com.example.grade.Classes.Profile
import com.example.grade.R
import com.example.grade.Helpers.SharedPreferenceHelper
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class profileActivity : AppCompatActivity() {
    // The variables
    private lateinit var sharedPreference: SharedPreferenceHelper
    private lateinit var nameTFLayout: TextInputLayout
    private lateinit var nameTextField: TextInputEditText
    private lateinit var ageTFLayout: TextInputLayout
    private lateinit var ageTextField: TextInputEditText
    private lateinit var idTFLayout: TextInputLayout
    private lateinit var idTextField: TextInputEditText
    private lateinit var userNameTF: TextInputEditText
    private lateinit var userNameTFLayout: TextInputLayout
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

    //Initializing the variables in the activity
    private fun initView() {
        nameTextField = findViewById(R.id.nameTF)
        ageTextField = findViewById(R.id.ageTF)
        idTextField = findViewById(R.id.idTF)
        saveProfileButt = findViewById(R.id.saveProfileButt)
        nameTFLayout = findViewById(R.id.nameTFLayout)
        ageTFLayout = findViewById(R.id.ageTFLayout)
        userNameTF = findViewById(R.id.userNameTF)
        userNameTFLayout = findViewById(R.id.userNameTFLayout)
        idTFLayout = findViewById(R.id.idTFLayout)
        sharedPreference = SharedPreferenceHelper(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Creating the menus
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        //Checking which menu is selected and call the required function
        R.id.profileMenu_delete -> {
            deleteAllInfo(); true
        }
        R.id.profileMenu_edit -> {
            enableEditMode(); true
        }
        else -> super.onOptionsItemSelected(item)
    }

    //Enabling edit mode so user can edit information
    private fun enableEditMode() {
        saveProfileButt.isVisible = true
        nameTextField.isEnabled = true
        ageTextField.isEnabled = true
        idTextField.isEnabled = true
        userNameTF.isEnabled = true
    }

    //Essentially enabling view mode
    private fun disableEditMode() {
        saveProfileButt.isVisible = false
        nameTextField.isEnabled = false
        ageTextField.isEnabled = false
        userNameTF.isEnabled = false
        idTextField.isEnabled = false
    }

    private fun loadProfileInfo() {
        //If there is indeed data saved, we then just show the profile information in view mode
        if (!sharedPreference.noInfoSaved()) {
            disableEditMode()
            val profile = sharedPreference.getProfile()
            nameTextField.setText(profile.name)
            ageTextField.setText(profile.age)
            userNameTF.setText(profile.userName)
            idTextField.setText(profile.id)
        }
        // No profile data, we enable edit mode
        else {
            enableEditMode()
            clearTextFields()
        }
    }

    private fun saveProfileInfo() {

        //Makes sure that all the information enterred is correct
        if (nameFormatIsCorrect() && CheckingInputHelper.checkTextFieldIsNotEmpty(userNameTF, userNameTFLayout) && ageFormatIsCorrect() && idFormatIsCorrect()) {

            //Create a profile object
            val profile = Profile(
                nameTextField.text.toString(), userNameTF.text.toString(), ageTextField.text.toString(), idTextField.text.toString()
            )
            //Saves the profile information
            sharedPreference.saveProfileInfo(profile)
            //Tell the user his profile was saved
            Toast.makeText(
                applicationContext, "Profile Info Saved", Toast.LENGTH_SHORT
            ).show()

            //Then go to view mode
            disableEditMode()
        } else {
            Toast.makeText(
                applicationContext, "One or more fields is incorrect", Toast.LENGTH_SHORT
            )
        }
    }

    //To easily clear all the text fields
    private fun clearTextFields() {
        nameTextField.text?.clear()
        ageTextField.text?.clear()
        userNameTF.text?.clear()
        idTextField.text?.clear()
    }

    //Checks if the name format is correct
    private fun nameFormatIsCorrect(): Boolean {
        return CheckingInputHelper.checkNameFormat(nameTextField, nameTFLayout)
    }

    //Checking the age is correct
    private fun ageFormatIsCorrect(): Boolean {
        if (ageTextField.text.toString() == "") {
            ageTFLayout.error = "Enter Something!"
            return false
        }
        val age = ageTextField.text.toString().toInt()

        //Making sure the age fits in the required range
        when {
            age < 18 -> {
                ageTFLayout.error = "Please enter age bigger than 18"
                return false
            }
            age > 99 -> {
                ageTFLayout.error = "Please enter age lower than 99"
                return false
            }
            else -> {
                CheckingInputHelper.cleanIntergers(ageTextField)
                ageTFLayout.error = ""
            }
        }
        return true
    }

    // Checks that the id format is correct
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

    //Function to easily delete all profile information
    private fun deleteAllInfo() {
        sharedPreference.deleteAllSavedInfo()
        loadProfileInfo()
        Toast.makeText(
            applicationContext, "All Profile Info Deleted, Please enter new information", Toast.LENGTH_LONG
        )
    }
}

