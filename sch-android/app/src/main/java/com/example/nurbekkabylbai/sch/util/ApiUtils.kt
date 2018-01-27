package com.example.nurbekkabylbai.sch.util

import com.example.nurbekkabylbai.sch.remote.*

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
object ApiUtils {

    private var scheduleService: ScheduleService? = null
    private var teacherService: TeacherService? = null
    private var groupService: GroupService? = null
    private var roomService: RoomService? = null
    private var courseService: CourseService? = null

    fun getScheduleService() : ScheduleService {
        return scheduleService ?: RetrofitClient.getClient().create(ScheduleService::class.java)
    }

    fun getTecherService() : TeacherService {
        return teacherService ?: RetrofitClient.getClient().create(TeacherService::class.java)
    }

    fun getGroupService() : GroupService {
        return groupService ?: RetrofitClient.getClient().create(GroupService::class.java)
    }

    fun getRoomService() : RoomService {
        return roomService ?: RetrofitClient.getClient().create(RoomService::class.java)
    }

    fun getCourseService() : CourseService {
        return courseService ?: RetrofitClient.getClient().create(CourseService::class.java)
    }
}