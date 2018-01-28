package com.example.nurbekkabylbai.sch.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Entity(tableName = "mGroup")
class Group {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    lateinit var groupId: String


    @SerializedName("size")
    @Expose
    var size: Int = 0
}