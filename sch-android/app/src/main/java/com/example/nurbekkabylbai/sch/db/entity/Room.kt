package com.example.nurbekkabylbai.sch.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Entity
class Room {

    @PrimaryKey
    lateinit var roomId: String

    var number: Int = 0

    var seatsNumber: String? = null

    var roomType: String? = null
}