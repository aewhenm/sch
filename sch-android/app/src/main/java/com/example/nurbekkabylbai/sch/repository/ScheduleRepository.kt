package com.example.nurbekkabylbai.sch.repository

import com.example.nurbekkabylbai.sch.ResponseListener
import com.example.nurbekkabylbai.sch.db.entity.Class
import com.example.nurbekkabylbai.sch.util.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
class ScheduleRepository {

    var listener: ResponseListener? = null

    fun setResponseListener(listener: ResponseListener) {
        this.listener = listener
    }


    fun requestSchedule(weekDay: Int) {

        val call = ApiUtils.getScheduleService().requestSchedule(weekDay)
//        val call = ApiUtils.getScheduleService().healthcheck()

        call.enqueue(object: Callback<List<Class>> {
            override fun onFailure(call: Call<List<Class>>?, t: Throwable?) {
                System.out.println("lalka huialka: " + call)
            }

            override fun onResponse(call: Call<List<Class>>?, response: Response<List<Class>>) {
                System.out.println("lalka:" + response.body())
                listener?.onResponseReceived(response.body())
            }
        })
    }
}