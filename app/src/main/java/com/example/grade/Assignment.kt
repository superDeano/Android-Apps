package com.example.grade

import kotlin.random.Random

class Assignment constructor(val assignmentTitle: String, val digitGrade: String) {
    var letter: Boolean = false

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
        !letter
    }

    private var tempLetterGrade = {
        if (digitGrade != "") {
            when (digitGrade.toInt()) {
                in 90..100 -> "A+"
                in 85..89 -> "A"
                in 80..84 -> "A-"
                in 77..79 -> "B+"
                in 73..76 -> "B"
                in 70..72 -> "B-"
                in 67..69 -> "C+"
                in 63..66 -> "C"
                in 60..62 -> "C-"
                in 57..59 -> "D+"
                in 53..56 -> "D"
                in 50..52 -> "D-"
                else -> "F"
            }
        }
    }
    private var letterGrade: String = tempLetterGrade.toString()

    var grade: String = letterGrade
}
