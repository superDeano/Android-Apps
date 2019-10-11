//Data class for each assignment
package com.example.grade.Classes

import java.io.Serializable
import kotlin.random.Random

class Assignment constructor(val assignmentTitle: String, val digitGrade: String, val courseId: String?) : Serializable {

    private var letterGrade = gradeLetter()
    var grade: String = digitGrade

    /**The companion object is Kotlin's way of enabling static functions*/
    companion object {

        //To generate a random assignment
        fun generateRandomAssignment(id: Int): Assignment {
            //for readability purposes
            val grade = Random.nextInt(1, 100).toString()
            return Assignment("Assignment $id", grade, null)
        }

        //When no assignments for a course
        fun generateNoAssignment(): Assignment {
            return Assignment("No Assignments", "", null)
        }

    }

    /*Allows for the Recycler To Display different grade Formats*/
    fun changeGradeFormat() {
        when (grade) {
            letterGrade -> grade = digitGrade
            digitGrade -> grade = letterGrade
        }
    }


    /**To get the equivalent letter grade*/
    private fun gradeLetter(): String {

        var tempAverageLetterGrade: String

        if (digitGrade != "") {
            val grade = digitGrade.toInt()

            if (grade in 90..100) {
                tempAverageLetterGrade = "A+"
            } else if (grade in 85..89) {
                tempAverageLetterGrade = "A"
            } else if (grade in 80..84) {
                tempAverageLetterGrade = "A-"
            } else if (grade in 77..79) {
                tempAverageLetterGrade = "B+"
            } else if (grade in 73..76) {
                tempAverageLetterGrade = "B"
            } else if (grade in 70..72) {
                tempAverageLetterGrade = "B-"
            } else if (grade in 67..69) {
                tempAverageLetterGrade = "C+"
            } else if (grade in 63..66) {
                tempAverageLetterGrade = "C"
            } else if (grade in 60..62) {
                tempAverageLetterGrade = "C-"
            } else if (grade in 53..56) {
                tempAverageLetterGrade = "D"
            } else if (grade in 50..52) {
                tempAverageLetterGrade = "D-"
            } else {
                tempAverageLetterGrade = "F"
            }

        } else {
            tempAverageLetterGrade = ""
        }
        return tempAverageLetterGrade
    }


}
