package com.example.grade.Helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.grade.Classes.Assignment
import com.example.grade.Classes.CustomCourse
import com.example.grade.Classes.Config
import kotlin.collections.ArrayList

/*
* Database helper class
* */
class DataBaseHelper(val context: Context?) : SQLiteOpenHelper(
    context, Config.DATABASE_NAME.toString(), null, Config.DATABASE_VERSION.value.toInt()
) {


    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_COURSE_TABLE = "CREATE TABLE " + Config.TABLE_COURSE.value + "(" + Config.COLUMN_COURSE_ID.value + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Config.COLUMN_COURSE_TITLE.value + " TEXT NOT NULL, " + Config.COLUMN_COURSE_CODE.value + " TEXT NOT NULL " + ")"

        val CREATE_ASS_TABLE = "CREATE TABLE " + Config.TABLE_ASS.value + "(" + Config.COLUMN_ASS_ID.value + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Config.COLUMN_ASS_COURSE_ID.value + " TEXT NOT NULL, " + Config.COLUMN_ASS_TITLE.value + " TEXT NOT NULL, " + Config.COLUMN_ASS_GRADE.value + " TEXT NOT NULL " + ")"

        db?.execSQL(CREATE_COURSE_TABLE)
        db?.execSQL(CREATE_ASS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_COURSE.value)
        db?.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_ASS.value)
        onCreate(db)
    }


    fun insertCourse(course: CustomCourse): Long {
        var id: Long = -1

        try {
            val sqLiteDatabase = writableDatabase


            if (sqLiteDatabase != null) {
                Log.d("ADDing Course", "database is Not fucking null")

                val contentValues = ContentValues()
                contentValues.put(Config.COLUMN_COURSE_TITLE.value, course.courseName)
                contentValues.put(Config.COLUMN_COURSE_CODE.value, course.courseCode)

                try {
                    id = sqLiteDatabase.insertOrThrow(Config.TABLE_COURSE.value, null, contentValues)
                } catch (e: SQLiteException) {
                    Log.d("Inserting Course", e.message)
                    Toast.makeText(context, "Operation Failed " + e.message, Toast.LENGTH_LONG).show()

                } finally {
                    sqLiteDatabase.close()
                }
            }
        } catch (e: SQLiteException) {
            Log.d("AddingCourse", e.message)
        }
        return id
    }


    fun deleteAllCourses(): Boolean {
        var deletedEverything = false
        val dataBase = writableDatabase


        try {
            // Deleting all courses
            dataBase.delete(Config.TABLE_COURSE.value, null, null)
            // Deleting all assignments
            dataBase.delete(Config.TABLE_ASS.value, null, null)

            val countCourses = DatabaseUtils.queryNumEntries(dataBase, Config.TABLE_COURSE.value)
            val countAssignments = DatabaseUtils.queryNumEntries(dataBase, Config.TABLE_ASS.value)

            //Checking if both tables are empty
            if (countCourses.toInt() == 0 && countAssignments.toInt() == 0) {
                deletedEverything = true
            }
        } catch (e: SQLiteException) {
            Log.d("Deleting All Course", e.message)
        } finally {
            dataBase.close()
        }

        return deletedEverything
    }

    fun deleteCourseById(courseID: String): Long {
        var courseIdInDataBase: Long = -1
        val dataBase = writableDatabase


        try {
            courseIdInDataBase = dataBase.delete(Config.TABLE_COURSE.value, Config.COLUMN_COURSE_CODE.value + " = ? ", arrayOf(courseID)).toLong()
        } catch (e: SQLiteException) {
            Log.d("deleting a Course", e.message)
        } finally {
            dataBase.close()
        }
        deleteAllAssignmentsFromCourse(courseIdInDataBase.toString())
        return courseIdInDataBase

    }

    private fun deleteAllAssignmentsFromCourse(courseId: String): Boolean {
        var success = false
        val database = writableDatabase
        try {
            database.delete(Config.TABLE_ASS.value, Config.COLUMN_ASS_COURSE_ID.value + " = ? ", arrayOf(courseId))
        } catch (exception: SQLiteException) {
            Log.d("deleting Ass", exception.message)
        } finally {
            database.close()
        }


        return success
    }

    fun addAssignmentToCourse(assignment: Assignment): Long {
        var id: Long = -1
        val sqLiteDatabase = writableDatabase

        if (sqLiteDatabase != null) {

            val contentValues = ContentValues()
            contentValues.put(Config.COLUMN_ASS_TITLE.value, assignment.assignmentTitle)
            contentValues.put(Config.COLUMN_ASS_GRADE.value, assignment.digitGrade)
            contentValues.put(Config.COLUMN_ASS_COURSE_ID.value, assignment.courseId)

            try {
                id = sqLiteDatabase.insertOrThrow(Config.TABLE_ASS.value, null, contentValues)
            } catch (e: SQLiteException) {
                Log.d("AddingAss", e.message)
            } finally {
                //To close database
                sqLiteDatabase.close()
            }
        }
        return id
    }



    fun getEverything(): ArrayList<CustomCourse> {
        val courses = ArrayList<CustomCourse>()
        val sqLiteDatabase = readableDatabase
        val courseCursor: Cursor

        try {
            // gets cursor to course table
            courseCursor = sqLiteDatabase.query(Config.TABLE_COURSE.value, null, null, null, null, null, null)


            if (courseCursor.moveToFirst()) {

                do {
                    var assignments: ArrayList<Assignment>

                    val courseTitle = courseCursor.getString(courseCursor.getColumnIndex(Config.COLUMN_COURSE_TITLE.value))
                    val courseId = courseCursor.getInt(courseCursor.getColumnIndex(Config.COLUMN_COURSE_ID.value))
                    val courseCode = courseCursor.getString(courseCursor.getColumnIndex(Config.COLUMN_COURSE_CODE.value))

                    //Cursor in assignment table
                    val assCursor = sqLiteDatabase.query(Config.TABLE_ASS.value, null, Config.COLUMN_ASS_COURSE_ID.value + " = ? ", arrayOf(courseId.toString()), null, null, null)

                    if (assCursor.moveToFirst()) {
                        assignments = ArrayList()

                        //Getting all assignments related to the course
                        while (!assCursor.isAfterLast) {
                            val assTitle = assCursor.getString(assCursor.getColumnIndex(Config.COLUMN_ASS_TITLE.value))
                            val assGrade = assCursor.getString(assCursor.getColumnIndex(Config.COLUMN_ASS_GRADE.value))


                            assignments.add(
                                Assignment(assTitle, assGrade, courseId.toString())
                            )
                            assCursor.moveToNext()
                        }

                        courses.add(CustomCourse(courseId.toLong(), courseTitle, courseCode, assignments))
                    }
                    // Assignments Empty for that course
                    else {
                        courses.add(CustomCourse(courseId.toLong(), courseTitle, courseCode, null))
                    }

                } while (courseCursor.moveToNext())


            }


        } catch (exception: SQLiteException) {
            Log.d("Getting Everything", exception.message)
        } finally {
            sqLiteDatabase.close()
        }


        return courses
    }


}