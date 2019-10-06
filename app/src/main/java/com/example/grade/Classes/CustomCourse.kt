package com.example.grade.Classes

import java.io.Serializable


class CustomCourse constructor(val id: Long, var courseName: String, var courseID: String) :
    Serializable {
//    var courseAverage: String?

    init {
//        courseAverage = calculateAverageGrade()
    }

//    fun calculateAverageGrade(): String? {
//        if (assignments == null) {
//            return null
//        } else {
//            var grade = 0
//            for (assignment in assignments!!) {
//                grade += assignment.digitGrade.toInt()
//            }
//
//            return (grade / assignments!!.size).toString()
//        }
//    }
}