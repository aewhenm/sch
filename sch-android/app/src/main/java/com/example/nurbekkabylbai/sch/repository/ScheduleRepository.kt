package com.example.nurbekkabylbai.sch.repository

import com.example.nurbekkabylbai.sch.db.entity.Class
import com.example.nurbekkabylbai.sch.util.ApiUtils
import kz.mycrm.android.util.AppExecutors
import java.lang.reflect.InvocationTargetException

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
class ScheduleRepository(private var appExecutors: AppExecutors) {

    fun requestSchedule(weekDay: Int): List<Class>? {

        ApiUtils.getScheduleService().requestSchedule(weekDay).execute()

        return null
    }

}