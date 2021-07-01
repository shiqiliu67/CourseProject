package com.shiqiliu.mvvm_course_dagger.model.remote

import com.shiqiliu.mvvm_course_dagger.model.data.CourseBody
import com.shiqiliu.mvvm_course_dagger.model.data.CourseResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("getCourses")
    suspend fun getCourses():Response<CourseResponse>

    @Headers("Content-type: application/json")
    @POST("addCourse")
    suspend fun addCourse(
    @Body courseBody: CourseBody
    ):Response<CourseResponse>

}