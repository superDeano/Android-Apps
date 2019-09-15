package com.example.grade

import kotlin.random.Random

class Assignment constructor(val assignmentTitle: String, val digitGrade: String) {
    var letter: Boolean = true

    companion object {
        var assID: Int = 0

        init {
            assID++
        }


        fun generateRandomAssignment(id: Int): Assignment {
            val tempTitle = "Assignment " + id
            val tempGrade = Random.nextInt(1, 100).toString()

            return Assignment(tempTitle, tempGrade)
        }

        fun generateNoAssignment(): Assignment {
            return Assignment("No Assignments", "")
        }

    }


    fun changeGradeFormat() {
        when (letter) {
            true -> grade = letterGrade
            false -> grade = digitGrade
        }
        letter = !letter
    }

    private var letterGrade = gradeLetter()

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


    var grade: String = digitGrade
}
