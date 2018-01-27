package com.example.nurbekkabylbai.sch.db.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.nurbekkabylbai.sch.db.entity.Course

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(c: List<Course>?)

    @Query("SELECT * FROM course WHERE courseId = :arg0")
    fun courseById(id: String): Course
}