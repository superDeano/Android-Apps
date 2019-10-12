package com.example.grade.Classes

import java.io.Serializable
/**
 * Data model for a Custom Course which can be created by the user
 * */

class CustomCourse constructor(val id: Long, var courseName: String, var courseCode: String, var assignments: ArrayList<Assignment>?) : Serializable {
     var courseAverage: String?


    init {
        courseAverage = calculateAverageGrade()
    }

    private fun calculateAverageGrade(): String? {
        if (assignments == null) {
            return null
        } else {
            var grade = 0
            for (assignment in assignments!!) {
                grade += assignment.digitGrade.toInt()
            }

            return (grade / assignments!!.size).toString()
        }
    }
}
