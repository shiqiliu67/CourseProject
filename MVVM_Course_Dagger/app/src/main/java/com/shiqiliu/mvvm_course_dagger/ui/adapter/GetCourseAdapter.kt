package com.shiqiliu.mvvm_course_dagger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.mvvm_course_dagger.databinding.RowCourseAdapterBinding
import com.shiqiliu.mvvm_course_dagger.model.data.Course
import com.shiqiliu.mvvm_course_dagger.ui.holder.GetCourseHolder

class GetCourseAdapter : RecyclerView.Adapter<GetCourseHolder>() {
    private var mList:ArrayList<Course> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetCourseHolder {
        var view = RowCourseAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GetCourseHolder(view)
    }

    override fun onBindViewHolder(holder: GetCourseHolder, position: Int) {
        var course = mList[position]
        return holder.bind(course)
    }

    override fun getItemCount() = mList.size

    fun setData(courseList : ArrayList<Course>){
        mList = courseList
        notifyDataSetChanged()
    }

}