package com.shiqiliu.mvvm_course_dagger.ui.course.addCourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.shiqiliu.mvvm_course_dagger.R
import com.shiqiliu.mvvm_course_dagger.databinding.ActivityAddCourseBinding
import com.shiqiliu.mvvm_course_dagger.model.data.CourseBody
import com.shiqiliu.mvvm_course_dagger.model.remote.ApiClient
import com.shiqiliu.mvvm_course_dagger.model.remote.repository.MainRepository
import com.shiqiliu.mvvm_course_dagger.ui.course.CourseActivity


class AddCourseActivity : AppCompatActivity() {
    lateinit var addCourseBinding: ActivityAddCourseBinding
    lateinit var addCourseViewModel: AddCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addCourseBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_course)
        //this activity should use view-binding
        setUpViewModel()
        setUpObserver()
        setUpEvent()
    }

    private fun setUpViewModel() {
        val apiService = ApiClient.getApiService()
        val mainRepository = MainRepository(apiService)
        val factory = AddCourseViewModelFactory(mainRepository)
        addCourseViewModel = ViewModelProvider(this, factory).get(AddCourseViewModel::class.java)
    }

    private fun setUpObserver() {
        addCourseViewModel.titleResponse.observe(this){
            addCourseBinding.editTextTitle.error = it
        }
        addCourseViewModel.feeResponse.observe(this){
            addCourseBinding.editTextFee.error = it
        }
        addCourseViewModel.descriptionResponse.observe(this){
            addCourseBinding.editTextDesc.error = it
        }
        addCourseViewModel.courseResponse.observe(this){
            response ->
            when(response.status){
                0->{
                   Toast.makeText(baseContext,response.message,Toast.LENGTH_SHORT).show()
                    startActivity(Intent(baseContext,CourseActivity::class.java))
                }
                1->{
                    Toast.makeText(baseContext,response.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpEvent() {
       addCourseBinding.buttonSubmit.setOnClickListener {
           val title = addCourseBinding.editTextTitle.text.toString()
           val description = addCourseBinding.editTextDesc.text.toString()
           val fee = addCourseBinding.editTextFee.text.toString()
           val courseBody = CourseBody(course_desc = description,course_fees = fee,course_title = title)
           //var courseBody2 = addCourseBinding.addCourseInfo -> should use 2-way data binding
           addCourseViewModel.addCourse(courseBody)
       }
    }
}