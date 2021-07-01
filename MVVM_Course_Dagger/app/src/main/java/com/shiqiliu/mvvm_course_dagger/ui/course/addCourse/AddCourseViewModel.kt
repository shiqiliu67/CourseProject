package com.shiqiliu.mvvm_course_dagger.ui.course.addCourse


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.shiqiliu.mvvm_course_dagger.model.data.Course
import com.shiqiliu.mvvm_course_dagger.model.data.CourseBody
import com.shiqiliu.mvvm_course_dagger.model.data.CourseResponse
import com.shiqiliu.mvvm_course_dagger.model.remote.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddCourseViewModel @Inject constructor(private val mainRepository: MainRepository) :ViewModel(){
    val courseResponse = MutableLiveData<CourseResponse>()
    val titleResponse = MutableLiveData<String>()
    val descriptionResponse = MutableLiveData<String>()
    val feeResponse = MutableLiveData<String>()

    fun addCourse(courseBody: CourseBody){
        val jsonObject = JsonObject()
        jsonObject.addProperty("course_title",courseBody.course_title)
        jsonObject.addProperty("course_desc",courseBody.course_desc)
        jsonObject.addProperty("course_fees",courseBody.course_fees)

        if(courseBody.course_title.isEmpty()){
            titleResponse.postValue("Please enter a title")
        }
        if(courseBody.course_desc.isEmpty()){
            descriptionResponse.postValue("Please enter a description")
        }
        if(courseBody.course_fees.isEmpty()){
            feeResponse.postValue("Please enter course fee")
        }

        viewModelScope.launch(Dispatchers.IO){
            val response = mainRepository.addCourse(courseBody)

            try {
                if (response.isSuccessful) {
                    courseResponse.postValue(response.body())
                }
                else {
                    val failedResponse = CourseResponse( message = "Failed to load data: ${response.errorBody()}", status = 1, courses = null)
                    courseResponse.postValue(failedResponse)
                }

            }
            catch (e:Exception){
                e.printStackTrace()
                val errorResponse = CourseResponse(status=1,message="Error exception: $e",courses = null)
                courseResponse.postValue(errorResponse)
            }
        }
    }
}

//                if (response.isSuccessful){
//                    courseResponse.postValue(response.body())
//                }
//                else{
//                    val failedResponse = CourseResponse(message = "Failed to load data: ${response.errorBody()}",status = 1,courses = null)
//                    courseResponse.postValue(failedResponse)
//                }
