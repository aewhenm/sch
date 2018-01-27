package com.example.nurbekkabylbai.sch

import com.example.nurbekkabylbai.sch.db.entity.Class

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
interface ResponseListener {
    fun onResponseReceived(list: List<Class>?)
}