package com.shiqiliu.mvvm_course_dagger.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shiqiliu.mvvm_course_dagger.R
import com.shiqiliu.mvvm_course_dagger.databinding.ActivityMainBinding
import com.shiqiliu.mvvm_course_dagger.model.remote.ApiClient
import com.shiqiliu.mvvm_course_dagger.model.remote.repository.MainRepository
import com.shiqiliu.mvvm_course_dagger.ui.adapter.GetCourseAdapter
import com.shiqiliu.mvvm_course_dagger.ui.course.addCourse.AddCourseActivity
import com.shiqiliu.mvvm_course_dagger.ui.course.getCourse.GetCourseViewModel
import com.shiqiliu.mvvm_course_dagger.ui.course.getCourse.GetViewModelFactory
import com.shiqiliu.mvvm_course_dagger.ui.holder.GetCourseHolder

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding : ActivityMainBinding
    lateinit var getCourseAdapter: GetCourseAdapter
    lateinit var getCourseViewModel: GetCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        init()
        setUpViewModel()
        setUpObserver()
    }

    private fun init() {
        //init adapter
        getCourseAdapter = GetCourseAdapter()
        mainBinding.recyclerView.adapter = getCourseAdapter
        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        //button - add
        mainBinding.buttonAdd.setOnClickListener {
            startActivity(Intent(this, AddCourseActivity::class.java))
        }
        //button - get course
        mainBinding.buttonGet.setOnClickListener {
            mainBinding.recyclerView.visibility = VISIBLE
        }
    }

    private fun setUpViewModel() {
        val apiService = ApiClient.getApiService()
        val mainRepository = MainRepository(apiService)
        val factory = GetViewModelFactory(mainRepository)
        getCourseViewModel = ViewModelProvider(this, factory).get(GetCourseViewModel::class.java)
    }

    private fun setUpObserver() {
        getCourseViewModel.courseResponse.observe(this) { response ->
            when (response.status) {
                0 -> {
                    if(response.courses!!.size>0){
                        var mList = response.courses
                        getCourseAdapter.setData(mList!!)
                    }
                    else{
                        Toast.makeText(baseContext,"No course here",Toast.LENGTH_SHORT).show()
                    }
                }
                1 ->{
                    Toast.makeText(baseContext,response.message,Toast.LENGTH_SHORT).show()
                }
            }
        }
        getCourseViewModel.getCourse()
    }
}