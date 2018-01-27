package com.example.nurbekkabylbai.sch.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.nurbekkabylbai.sch.db.entity.Course
import com.example.nurbekkabylbai.sch.db.entity.Group
import com.example.nurbekkabylbai.sch.db.entity.Room
import com.example.nurbekkabylbai.sch.db.entity.Teacher

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Database(entities = arrayOf(Class::class, Room::class, Group::class, Course::class, Teacher::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

}