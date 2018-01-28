package com.example.nurbekkabylbai.sch.remote

import com.example.nurbekkabylbai.sch.db.entity.Class
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
interface ScheduleService {

    @GET("api/schedule")
    fun requestSchedule(@Query("weekDay") weekDay: Int): Call<List<Class>>

    @GET("api/healthcheck")
    fun healthcheck(): Call<String>
}