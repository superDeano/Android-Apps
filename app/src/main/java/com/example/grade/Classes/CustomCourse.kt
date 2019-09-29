package com.example.grade.Classes

import android.annotation.TargetApi
import java.util.stream.Stream

class CustomCourse constructor(
    var courseName: String,
    var courseID: String,
    var assignments: ArrayList<Assignment>?
) {
    var courseAverage: String?

    init {
        courseAverage = calculateAverageGrade()
    }

    fun calculateAverageGrade(): String? {
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