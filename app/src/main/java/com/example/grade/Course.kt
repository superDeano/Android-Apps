//Data model for a course
package com.example.grade

import kotlin.random.Random

class Course constructor(var courseTitle: String, var assignments: ArrayList<Assignment>) {
    private val averageDigitGrade = calculateAverageGrade()
    private var averageLetterGrade = gradeLetter()
    var averageGrade = averageDigitGrade

    /**Allows for the use of Static function as in Java*/
    companion object {

        //Function used to generate a random course
        fun generateARandomCourse(id: Int): Course {

            var numberOfAssignment = Random.nextInt(0, 4)
            var assignments = ArrayList<Assignment>()

            //Course is supposed to have assignments
            if (numberOfAssignment != 0) {
                for (i in 0..numberOfAssignment) {
                    assignments.add(Assignment.generateRandomAssignment(i + 1))
                }
                // if course is not supposed to have assignment
            } else {
                assignments.add(Assignment.generateNoAssignment())
            }
            return Course("Course $id", assignments)
        }

    }

    /*Easily calculate the average from all the assignment grades*/
    fun calculateAverageGrade(): String {
        var sum = 0
        if (assignments.size != 1) {
            for (assignment in assignments) {
                sum += assignment.digitGrade.toInt()
            }
            return (sum / assignments.size).toString()
        } else return "N/A"
    }


    /**To get the equivalent grade in letter format*/
    private fun gradeLetter(): String {

        var tempAverageLetterGrade: String

        if (calculateAverageGrade() != "N/A") {
            val grade = averageDigitGrade.toInt()

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
            tempAverageLetterGrade = averageDigitGrade
        }
        return tempAverageLetterGrade
    }


    /*Used to change the data used by the Recycler View, allows it to know data has been changed and displays the different grade format*/
    fun changeGradeFormat() {

        when (averageGrade) {
            averageDigitGrade -> {
                averageGrade = averageLetterGrade
                changeAssignmentGradeFormat()
            }
            averageLetterGrade -> {
                averageGrade = averageDigitGrade
                changeAssignmentGradeFormat()
            }
        }
    }

    //Changes the grade format for the assignments too
    private fun changeAssignmentGradeFormat() {
        for (assignment in assignments) {
            assignment.changeGradeFormat()
        }
    }


}

