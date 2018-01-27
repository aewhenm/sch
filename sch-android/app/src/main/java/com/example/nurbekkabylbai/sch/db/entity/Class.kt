package com.example.nurbekkabylbai.sch.db.entity

import android.arch.persistence.room.Entity

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Entity
class Class {
    lateinit var classId: String
    var name: String? = null
}