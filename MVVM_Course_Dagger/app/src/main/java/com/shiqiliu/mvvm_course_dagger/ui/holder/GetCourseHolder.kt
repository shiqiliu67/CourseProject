package com.shiqiliu.mvvm_course_dagger.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.mvvm_course_dagger.databinding.RowCourseAdapterBinding
import com.shiqiliu.mvvm_course_dagger.model.data.Course

class GetCourseHolder(var binding: RowCourseAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(course: Course){
        binding.courseInfo = course//data binding data to display data
    }
}