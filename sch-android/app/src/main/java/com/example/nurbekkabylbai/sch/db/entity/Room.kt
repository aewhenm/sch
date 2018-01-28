package com.example.nurbekkabylbai.sch.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Entity
class Room {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    lateinit var roomId: String

    @SerializedName("number")
    @Expose
    var number: Int = 0

    @SerializedName("seatsNumber")
    @Expose
    var seatsNumber: String? = null

    @SerializedName("isEquiped")
    @Expose
    var isEquiped: Boolean = false
}