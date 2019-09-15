package com.example.grade

import kotlin.random.Random

class Course constructor(var courseTitle: String, var assignments: ArrayList<Assignment>) {
    var gradeInLetterFormat = false

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

    private val tempAverageLetterGrade = {

        if (calculateAverageGrade() != "N/A") {
            when (calculateAverageGrade().toInt()) {
                in 90..100 -> "A+"
                in 85..89 -> "A"
                in 80..84 -> "A-"
                in 77..79 -> "B+"
                in 73..76 -> "B"
                in 70..72 -> "B-"
                in 67..69 -> "C+"
                in 60..62 -> "C-"
                in 53..56 -> "D"
                in 50..52 -> "D-"
                else -> "F"
            }
        }
    }
    var averageLetterGrade: String = tempAverageLetterGrade.toString()

    var averageGrade = averageLetterGrade

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
        !gradeInLetterFormat
    }

    private fun changeAssignmentGradeFormat() {
        for (assignment in assignments) {
            assignment.changeGradeFormat()
        }
    }
}

