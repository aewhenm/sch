package com.example.nurbekkabylbai.sch

import android.app.Application
import android.arch.persistence.room.Room
import com.example.nurbekkabylbai.sch.db.AppDatabase

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
class SchApp: Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "SchDatabase")
                .allowMainThreadQueries() // TODO: remove after debug
                .fallbackToDestructiveMigration()
                .build()
    }
}