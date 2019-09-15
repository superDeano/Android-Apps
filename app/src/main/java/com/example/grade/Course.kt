package com.example.grade

import kotlin.random.Random

class Course constructor(var courseTitle: String, var assignments: ArrayList<Assignment>) {
    var gradeInLetterFormat = true

    companion object {
        var courseID: Int = 0

        init {
            courseID++
        }

        fun generateARandomCourse(id: Int): Course {

            var numberOfAssignment = Random.nextInt(0, 5)
            var assignments = ArrayList<Assignment>()

            if (numberOfAssignment != 0) {
                for (i in 0..numberOfAssignment) {
                    assignments.add(Assignment.generateRandomAssignment(i + 1))
                }
            } else {
                assignments.add(Assignment.generateNoAssignment())
            }
            return Course("Course " + id, assignments)
        }

    }

    fun calculateAverageGrade(): String {
        var sum = 0
        if (assignments.size != 1) {
            for (assignment in assignments) {
                sum += assignment.digitGrade.toInt()
            }
            return (sum / assignments.size).toString()
        } else return "N/A"
    }

    private val averageDigitGrade = calculateAverageGrade()

    private var averageLetterGrade = gradeLetter()

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

    var averageGrade = averageDigitGrade

    fun changeGradeFormat() {

        when (gradeInLetterFormat) {
            true -> {
                averageGrade = averageLetterGrade
                changeAssignmentGradeFormat()
            }
            false -> {
                averageGrade = averageDigitGrade
                changeAssignmentGradeFormat()
            }
        }
        gradeInLetterFormat = !gradeInLetterFormat
    }

    private fun changeAssignmentGradeFormat() {
        for (assignment in assignments) {
            assignment.changeGradeFormat()
        }
    }
}

