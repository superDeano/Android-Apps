package com.example.grade.Helpers

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

        fun checkTextFieldIsNotEmpty(textfield: TextInputEditText, textLayout: TextInputLayout): Boolean {
            if (textfield.text.toString() == "") {
                textLayout.error = "Enter Something!"
                return false
            } else {
                textLayout.error = ""
                return true
            }
        }

        fun checkPositiveIntegersOnly(textfield: TextInputEditText, textLayout: TextInputLayout): Boolean {
            if (textfield.text.toString() == "") {
                textLayout.error = "Enter Something!"
                return false
            } else {
                val value = textfield.text.toString().toInt()
                when {
                    value < 0 -> {
                        textLayout.error = "Please enter a number greater than 0"
                        return false
                    }
                    value > 100 -> {
                        textLayout.error = "Please enter a number smaller than 100"
                        return false
                    }
                    else -> {
                        return true
                    }
                }

            }

        }


    }


}