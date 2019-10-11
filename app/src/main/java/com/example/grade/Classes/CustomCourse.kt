package com.example.grade.Classes

import java.io.Serializable


class CustomCourse constructor(val id: Long, var courseName: String, var courseCode: String, var assignments: ArrayList<Assignment>?) : Serializable {
    var courseAverage: String?


    init {
        courseAverage = calculateAverageGrade()
    }

    fun calculateAverageGrade(): String? {
        if (assignments == null) {
            return "0"
        } else {
            var grade = 0
            for (assignment in assignments!!) {
                grade += assignment.digitGrade.toInt()
            }

            return (grade / assignments!!.size).toString()
        }
    }
}
