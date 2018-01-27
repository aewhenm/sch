package com.example.nurbekkabylbai.sch.ui

import android.arch.lifecycle.ViewModel
import com.example.nurbekkabylbai.sch.db.entity.Class
import com.example.nurbekkabylbai.sch.repository.ScheduleRepository
import kz.mycrm.android.util.AppExecutors

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
class MainViewModel : ViewModel() {

    private val scheduleRepository = ScheduleRepository(AppExecutors)

    fun getClasses(weekDay: Int): List<Class>? {
        return scheduleRepository.requestSchedule(weekDay)
    }

}