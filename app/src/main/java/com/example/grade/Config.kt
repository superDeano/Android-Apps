package com.example.grade

enum class Config(val value: String) {

    //For the Course Table
    DATABASE_NAME("database"),
    TABLE_COURSE("course"),
    COLUMN_COURSE_ID("_id"),
    COLUMN_COURSE_TITLE("title"),
    COLUMN_COURSE_CODE("code"),
    DATABASE_VERSION("1"),


    //For the Assignments Table
    TABLE_ASS("assignments"),
    COLUMN_ASS_ID("ass_id"),
    COLUMN_ASS_COURSE_ID("courseid"),
    COLUMN_ASS_TITLE("title"),
    COLUMN_ASS_GRADE("grade")
}