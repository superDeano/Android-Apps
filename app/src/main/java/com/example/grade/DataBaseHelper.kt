package com.example.grade

import android.annotation.TargetApi
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.grade.Classes.CustomCourse
import java.lang.Exception
import kotlin.collections.ArrayList

@TargetApi(28)
class DataBaseHelper(val context: Context?) : SQLiteOpenHelper(
    context, Config.DATABASE_NAME.toString(), null,
    Config.DATABASE_VERSION.value.toInt()
) {


    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_COURSE_TABLE =
            "CREATE TABLE " + Config.TABLE_COURSE.value + "(" + Config.COLUMN_COURSE_ID.value + " INTEGER PRIMARY KEY AUOINCREMENT, " + Config.COLUMN_COURSE_TITLE.value + " TEXT NOT NULL, " + Config.COLUMN_COURSE_CODE.value + " TEXT NOT NULL " + ")"

        db?.execSQL(CREATE_COURSE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_COURSE.value)
        onCreate(db)
    }

    fun insertCourse(course: CustomCourse): Long {
        var id: Long = -1

        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Config.COLUMN_COURSE_TITLE.value, (course.courseName))
        contentValues.put(Config.COLUMN_COURSE_CODE.value, (course.courseID))

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_COURSE.value, null, contentValues)
        } catch (e: SQLiteException) {
            Log.v("Inserting Course", e.message)
            Toast.makeText(context, "Operation Failed " + e.message, Toast.LENGTH_LONG).show()

        } finally {
            sqLiteDatabase.close()
        }

        return id
    }


    fun getAllCourses(): ArrayList<CustomCourse>? {
        val sqLiteDatabase = readableDatabase
        val cursor: Cursor
        val courses: ArrayList<CustomCourse>

        try {
            cursor = sqLiteDatabase.query(
                Config.TABLE_COURSE.value, null, null, null, null,
                null, null
            )

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    courses = ArrayList()
                    var id: Int
                    var courseName: String
                    var courseId: String
                    do {
                        id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COURSE_ID.value))
                        courseName =
                            cursor.getString(
                                cursor.getColumnIndex(Config.COLUMN_COURSE_TITLE.value)
                            )
                        courseId =
                            cursor.getString(
                                cursor.getColumnIndex(Config.COLUMN_COURSE_CODE.value)
                            )
                        courses.add(CustomCourse(id.toLong(), courseName, courseId))

                    } while (cursor.moveToNext())

                    return courses
                }
            }

        } catch (e: Exception) {
            Log.v("Getting Courses", e.message)
            Toast.makeText(context, "Can't get Courses", Toast.LENGTH_LONG).show()
        } finally {
//            cursor.close()
            sqLiteDatabase.close()

        }
        return null
    }


}