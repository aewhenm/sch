package com.example.nurbekkabylbai.sch.db.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.nurbekkabylbai.sch.db.entity.Group

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(c: List<Group>?)

    @Query("SELECT * FROM group WHERE courseId = :arg0")
    fun groupById(id: String): Group
}