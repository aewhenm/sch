package com.example.nurbekkabylbai.sch.remote

import retrofit2.http.GET

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
interface ScheduleService {

    @GET()
    fun requestShedule(weekDay: Int)
}