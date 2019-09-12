package com.example.grade

import kotlin.random.Random

class Assignment public constructor(val assignmentTitle: String, var grade: Int) {

    companion object {
        var assID: Int = 0

        init {
            assID++
        }

        fun generateRandomAssignment(): Assignment {
            val tempTitle = "Assignment " + Assignment.assID
            val tempGrade = Random.nextInt(1, 100)

            return Assignment(tempTitle, tempGrade)
        }
    }
}