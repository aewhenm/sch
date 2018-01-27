package com.example.nurbekkabylbai.sch.repository

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
                System.out.println("lalka huialka: " + call)
            }

            override fun onResponse(call: Call<List<Teacher>>?, response: Response<List<Teacher>>) {
                System.out.println("lalka:" + response.body())
            }
        })
    }

    fun requestCourses() {

        val call = ApiUtils.getCourseService().requestCourses()
        call.enqueue(object: Callback<List<Course>> {
            override fun onFailure(call: Call<List<Course>>?, t: Throwable?) {
                System.out.println("lalka huialka: " + call)
            }

            override fun onResponse(call: Call<List<Course>>?, response: Response<List<Course>>) {
                System.out.println("lalka:" + response.body())
            }
        })
    }

    fun requestRooms() {
        val call = ApiUtils.getRoomService().requestRooms()

        call.enqueue(object: Callback<List<Room>> {
            override fun onFailure(call: Call<List<Room>>?, t: Throwable?) {
                System.out.println("lalka huialka: " + call)
            }

            override fun onResponse(call: Call<List<Room>>?, response: Response<List<Room>>) {
                System.out.println("lalka:" + response.body())
            }
        })
    }

    fun requestGroups() {
        val call = ApiUtils.getGroupService().requestGroups()

        call.enqueue(object: Callback<List<Group>> {
            override fun onFailure(call: Call<List<Group>>?, t: Throwable?) {
                System.out.println("lalka huialka: " + call)
            }

            override fun onResponse(call: Call<List<Group>>?, response: Response<List<Group>>) {
                System.out.println("lalka:" + response.body())
            }
        })
    }

}