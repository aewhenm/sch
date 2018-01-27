package com.example.nurbekkabylbai.sch.remote

import com.example.nurbekkabylbai.sch.db.entity.Group
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
interface GroupService {

    @GET("api/group")
    fun requestGroups(): Call<List<Group>>

}