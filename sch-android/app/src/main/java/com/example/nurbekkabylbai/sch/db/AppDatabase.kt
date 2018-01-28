package com.example.nurbekkabylbai.sch.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.nurbekkabylbai.sch.db.dao.*
import com.example.nurbekkabylbai.sch.db.entity.*

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Database(entities = arrayOf(Class::class, Room::class, Group::class, Course::class, Teacher::class), version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun StubDao(): StubDao
    abstract fun CourseDao(): CourseDao
    abstract fun GroupDao(): GroupDao
    abstract fun RoomDao(): RoomDao
    abstract fun TeacherDao(): TeacherDao
}