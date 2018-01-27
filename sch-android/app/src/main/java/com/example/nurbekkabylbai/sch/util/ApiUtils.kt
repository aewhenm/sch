package com.example.nurbekkabylbai.sch.util

import com.example.nurbekkabylbai.sch.remote.RetrofitClient
import com.example.nurbekkabylbai.sch.remote.ScheduleService

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
object ApiUtils {

    private var scheduleService: ScheduleService? = null

    fun getScheduleService() : ScheduleService {
        return scheduleService ?: RetrofitClient.getClient().create(ScheduleService::class.java)
    }

}