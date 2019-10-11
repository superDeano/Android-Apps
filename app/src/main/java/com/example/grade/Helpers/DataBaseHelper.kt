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
import java.lang.Exception
import kotlin.collections.ArrayList


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

    //TODO: Retire this function
//    fun getAllCourses(): ArrayList<CustomCourse>? {
//        val sqLiteDatabase = readableDatabase
//        val cursor: Cursor
//        val courses: ArrayList<CustomCourse>
//
//        try {
//            cursor = sqLiteDatabase.query(
//                Config.TABLE_COURSE.value, null, null, null, null, null, null
//            )
//
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    courses = ArrayList()
//                    var id: Int
//                    var courseName: String
//                    var courseId: String
//                    do {
//                        id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID.value))
//                        courseName = cursor.getString(
//                            cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE.value)
//                        )
//                        courseId = cursor.getString(
//                            cursor.getColumnIndex(Config.COLUMN_COURSE_CODE.value)
//                        )
//                        courses.add(CustomCourse(id.toLong(), courseName, courseId, null))
//
//                    } while (cursor.moveToNext())
//
//                    return courses
//                }
//            }
//
//        } catch (e: Exception) {
//            Log.d("Getting Courses", e.message)
//            Toast.makeText(context, "Can't get Courses", Toast.LENGTH_LONG).show()
//        } finally {
//            sqLiteDatabase.close()
//
//        }
//        return null
//    }


    fun deleteAllCourses(): Boolean {
        var deletedEverything = false
        val dataBase = writableDatabase


        try {
            dataBase.delete(Config.TABLE_COURSE.value, null, null)
            val count = DatabaseUtils.queryNumEntries(dataBase, Config.TABLE_COURSE.value)
            if (count.toInt() == 0) {
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
        return courseIdInDataBase

    }

    fun deleteAllAssignmentsFromCourse(courseId: String): Boolean {
        //TODO to implement
        var success = false

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

    fun getAllAssignmentsForCourse(courseId: String): ArrayList<Assignment> {

        val sqLiteDatabase = readableDatabase
        val assignments = ArrayList<Assignment>()
        val cursor: Cursor

        try {

            cursor = sqLiteDatabase.query(Config.TABLE_ASS.value, null, Config.COLUMN_ASS_COURSE_ID.value + " = ? ", arrayOf(courseId), null, null, null)

            if (cursor.moveToFirst()) {
                do {

                    assignments.add(Assignment(cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASS_TITLE.value)), cursor.getString(cursor.getColumnIndex(Config.COLUMN_ASS_GRADE.value)), courseId))

                } while (cursor.moveToNext())
            }

        } catch (e: SQLiteException) {
            Log.d("Getting Ass", e.message)
        } finally {
            sqLiteDatabase.close()
        }

        return assignments
    }

    fun getEverything(): ArrayList<CustomCourse> {
        val courses = ArrayList<CustomCourse>()
        val sqLiteDatabase = readableDatabase
        val courseCursor: Cursor

        try {

            courseCursor = sqLiteDatabase.query(Config.TABLE_COURSE.value, null, null, null, null, null, null)

            if (courseCursor.moveToFirst()) {

                do {
                    var assignments: ArrayList<Assignment>

                    val courseTitle = courseCursor.getString(courseCursor.getColumnIndex(Config.COLUMN_COURSE_TITLE.value))
                    val courseId = courseCursor.getInt(courseCursor.getColumnIndex(Config.COLUMN_COURSE_ID.value))
                    val courseCode = courseCursor.getString(courseCursor.getColumnIndex(Config.COLUMN_COURSE_CODE.value))

                    val assCursor = sqLiteDatabase.query(Config.TABLE_ASS.value, null, Config.COLUMN_ASS_COURSE_ID.value + " = ? ", arrayOf(courseId.toString()), null, null, null)

                    if (assCursor.moveToFirst()) {
                        assignments = ArrayList()

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

    fun deleteDb() {
        context?.deleteDatabase(Config.DATABASE_NAME.value)
    }

}