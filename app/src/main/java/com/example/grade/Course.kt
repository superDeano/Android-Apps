package com.example.grade

import kotlin.random.Random

class Course constructor(var courseTitle: String, var assignments: Array<Assignment>) {

    companion object {
        var courseID: Int = 0

        init {
            courseID++
        }

        fun generateRandomCourse(): Course {

            var numberOfAssignment = Random.nextInt(1, 5)
            var assignments: Array<Assignment> = arrayOf()

            for (i in 0..numberOfAssignment) {
                assignments[i] = Assignment.generateRandomAssignment()
            }

            return Course("Course " + courseID, assignments)
        }
    }
}