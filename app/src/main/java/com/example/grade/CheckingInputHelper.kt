package com.example.grade

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CheckingInputHelper {
    companion object {

        fun checkNameFormat(
            nameTextField: TextInputEditText, nameLayout: TextInputLayout
        ): Boolean {
            if (nameTextField.text.toString() == "") {
                nameLayout.error = "Enter Something!"
                return false
            } else {
                nameLayout.error = ""
            }
            val name = nameTextField.text.toString().toCharArray()
            for (letter in name) {
                if (!Character.isLetter(letter) && letter != ' ') {
                    nameLayout.error = "Please enter a better name"
                    return false
                } else {
                    nameLayout.error = ""
                }
            }
            return true
        }

    }


}