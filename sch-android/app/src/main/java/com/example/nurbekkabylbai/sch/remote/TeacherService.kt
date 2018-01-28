package com.example.nurbekkabylbai.sch.remote

import com.example.nurbekkabylbai.sch.db.entity.Teacher
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
interface TeacherService {

    @GET("api/teacher")
    fun requestTeachers(): Call<List<Teacher>>

}