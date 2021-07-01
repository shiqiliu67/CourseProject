package com.shiqiliu.mvvm_course_dagger.ui.course.addCourse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shiqiliu.mvvm_course_dagger.model.remote.repository.MainRepository
import javax.inject.Inject

class AddCourseViewModelFactory @Inject constructor (private val mainRepository: MainRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddCourseViewModel(mainRepository) as T
    }
}