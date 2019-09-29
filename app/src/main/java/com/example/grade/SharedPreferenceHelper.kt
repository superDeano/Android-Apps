package com.example.grade

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.grade.Classes.Profile

/**
 * In MVC, this is the controller class
 * */

class SharedPreferenceHelper(context: Context) {
    private var sharedPreferences: SharedPreferences

    //What to do when an object is created
    init {
        //Getting the reference to ProfileReference from SharedPreferences
        sharedPreferences = context.getSharedPreferences("ProfilePreference", MODE_PRIVATE)
    }

    //Takes in a profile and saves the information in SharedPreferences
    fun saveProfileInfo(profile: Profile) {
        saveProfileName(profile.name)
        saveProfileID(profile.id)
        saveProfileAge(profile.age)

    }

    //Returns a profile with the information from SharedPreferences
    fun getProfile(): Profile {
        val profile = Profile(getProfileName(), getAge(), getProfileID())
        return profile
    }

    //Internal function to save the name
    private fun saveProfileName(name: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("profileName", name)
        editor.commit()
    }

    //Internal function to get the name
    private fun getProfileName(): String? {
        return sharedPreferences.getString("profileName", null)
    }

    //Internal function to save the age
    private fun saveProfileAge(age: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("profileAge", age)
        editor.commit()
    }

    //Internal function to get the age
    private fun getAge(): String? {
        return sharedPreferences.getString("profileAge", null)
    }

    //Internal function to save the ID
    private fun saveProfileID(id: String?) {
        val editor = sharedPreferences.edit()
        editor.putString("profileId", id)
        editor.commit()
    }

    //Internal function to get the ID
    private fun getProfileID(): String? {
        return sharedPreferences.getString("profileId", null)
    }

    //To check if there is any information saved in SharedPreferences
    fun noInfoSaved(): Boolean {
        if (getAge() == null && getProfileName() == null && getProfileID() == null) {
            return true
        }
        return false

    }

    //Deleting all the information from SharedPreferences
    fun deleteAllSavedInfo() {
        sharedPreferences.edit()
            .clear()
            .commit()
    }
}