package com.shiqiliu.mvvm_course_dagger.model.remote.repository

import com.shiqiliu.mvvm_course_dagger.model.data.CourseBody
import com.shiqiliu.mvvm_course_dagger.model.remote.ApiService

class MainRepository(private val apiService: ApiService) {
    suspend fun getCourses() = apiService.getCourses()
    suspend fun addCourse(courseBody : CourseBody) = apiService.addCourse(courseBody)
}