package com.example.nurbekkabylbai.sch.repository

import com.example.nurbekkabylbai.sch.SchApp
import com.example.nurbekkabylbai.sch.db.entity.Course
import com.example.nurbekkabylbai.sch.db.entity.Group
import com.example.nurbekkabylbai.sch.db.entity.Room
import com.example.nurbekkabylbai.sch.db.entity.Teacher
import com.example.nurbekkabylbai.sch.util.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
class InfoRepository {

    fun requestTeachers() {
        val call = ApiUtils.getTecherService().requestTeachers()

        call.enqueue(object: Callback<List<Teacher>> {
            override fun onFailure(call: Call<List<Teacher>>?, t: Throwable?) {
                SchApp.database.TeacherDao()

            }

            override fun onResponse(call: Call<List<Teacher>>?, response: Response<List<Teacher>>) {
                SchApp.database.TeacherDao().insert(response.body())
            }
        })
    }

    fun requestCourses() {

        val call = ApiUtils.getCourseService().requestCourses()
        call.enqueue(object: Callback<List<Course>> {
            override fun onFailure(call: Call<List<Course>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<List<Course>>?, response: Response<List<Course>>) {
                SchApp.database.CourseDao().insert(response.body())
            }
        })
    }

    fun requestRooms() {
        val call = ApiUtils.getRoomService().requestRooms()

        call.enqueue(object: Callback<List<Room>> {
            override fun onFailure(call: Call<List<Room>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Room>>?, response: Response<List<Room>>) {
                SchApp.database.RoomDao().insert(response.body())
            }
        })
    }

    fun requestGroups() {
        val call = ApiUtils.getGroupService().requestGroups()

        call.enqueue(object: Callback<List<Group>> {
            override fun onFailure(call: Call<List<Group>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Group>>?, response: Response<List<Group>>) {
                SchApp.database.GroupDao().insert(response.body())
            }
        })
    }
}