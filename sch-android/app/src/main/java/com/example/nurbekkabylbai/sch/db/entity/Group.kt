package com.example.nurbekkabylbai.sch.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Entity(tableName = "mGroup")
class Group {

    @PrimaryKey
    lateinit var groupId: String

    var size: Int = 0
}