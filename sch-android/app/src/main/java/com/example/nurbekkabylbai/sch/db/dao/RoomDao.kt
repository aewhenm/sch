package com.example.nurbekkabylbai.sch.db.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.nurbekkabylbai.sch.db.entity.Room

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(c: List<Room>?)

    @Query("SELECT * FROM room WHERE roomId = :arg0")
    fun roomById(id: String): Room

}