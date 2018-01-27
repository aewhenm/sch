package com.example.nurbekkabylbai.sch.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
@Database(entities = arrayOf(), version = 1)
abstract class AppDatabase: RoomDatabase() {
}