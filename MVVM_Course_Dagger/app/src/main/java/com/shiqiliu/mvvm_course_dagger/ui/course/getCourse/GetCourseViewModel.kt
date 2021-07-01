package com.shiqiliu.mvvm_course_dagger.ui.course.getCourse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiqiliu.mvvm_course_dagger.model.data.CourseResponse
import com.shiqiliu.mvvm_course_dagger.model.remote.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class GetCourseViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    val courseResponse = MutableLiveData<CourseResponse>()

    fun getCourse() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var response = mainRepository.getCourses()
                if(response.isSuccessful){
                    courseResponse.postValue(response.body())
                }
                else{
                    var failedResponse = CourseResponse(null,"Failed to load data, error: ${response.errorBody()}",1)
                    courseResponse.postValue(failedResponse)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                var errorResponse = CourseResponse(courses = null, "Error here: $e", 1)
                courseResponse.postValue(errorResponse)
            }
        }
    }


}