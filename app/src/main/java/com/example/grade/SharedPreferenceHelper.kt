package com.example.grade

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharedPreferenceHelper(context: Context) {
    private var sharedPreferences: SharedPreferences


    init {
        sharedPreferences = context.getSharedPreferences("ProfilePreference", MODE_PRIVATE)
    }

    fun saveProfileName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("profileName", name)
        editor.commit()
    }

    fun getProfileName(): String? {
        return sharedPreferences.getString("profileName", null)
    }

    fun saveAge(age: String) {
        val editor = sharedPreferences.edit()
        editor.putString("profileAge", age)
        editor.commit()
    }

    fun getAge(): String? {
        return sharedPreferences.getString("profileAge", null)
    }

    fun saveProfileID(id: String) {
        val editor = sharedPreferences.edit()
        editor.putString("profileId", id)
        editor.commit()
    }

    fun getProfileID(): String? {
        return sharedPreferences.getString("profileId", null)
    }

    fun noInfoSaved(): Boolean {
        if (getAge() == null && getProfileName() == null && getProfileID() == null) {
            return true
        }
        return false

    }

    fun deleteAllSavedInfo() {
        sharedPreferences.edit()
            .clear()
            .commit()
    }
}