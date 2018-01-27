package com.example.nurbekkabylbai.sch.db.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.nurbekkabylbai.sch.db.entity.Teacher

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
interface TeacherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(c: List<Teacher>?)

    @Query("SELECT * FROM teacher WHERE teacherId = :arg0")
    fun teacherById(id: String): Teacher

}