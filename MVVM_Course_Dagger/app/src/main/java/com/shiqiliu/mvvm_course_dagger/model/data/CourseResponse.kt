package com.shiqiliu.mvvm_course_dagger.model.data

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("courses")
    var courses: ArrayList<Course>?,
    @SerializedName("message")
    var message: String,
    @SerializedName("status")
    var status: Int
)

data class Course(
    @SerializedName("course_desc")
    var course_desc: String,
    @SerializedName("course_fees")
    var course_fees: String,
    @SerializedName("course_id")
    var course_id: String,
    @SerializedName("course_title")
    var course_title: String
)

data class CourseBody(
    @SerializedName("course_title")
    var course_title: String,
    @SerializedName("course_desc")
    var course_desc: String,
    @SerializedName("course_fees")
    var course_fees: String
)