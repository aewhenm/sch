package com.example.nurbekkabylbai.sch.ui.info

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.nurbekkabylbai.sch.R
import com.example.nurbekkabylbai.sch.SchApp
import com.example.nurbekkabylbai.sch.db.entity.Class
import com.example.nurbekkabylbai.sch.db.entity.Event
import kotlinx.android.synthetic.main.activity_info.*

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
class InfoActivity: AppCompatActivity() {

    private var list: ArrayList<Class>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)



        val intent = intent
        list = intent.getSerializableExtra("list") as ArrayList<Class>

        if(list != null) {

            val eventList = ArrayList<Event>()

            for(c in (list as ArrayList<Class>)) {
                val teacher = SchApp.database.TeacherDao().teacherById(c.teacherId!!)
                val group = SchApp.database.GroupDao().groupById(c.groupId!!)
                val room = SchApp.database.RoomDao().roomById(c.roomId)

                eventList.add(Event(teacher, group, room))

            }

            val adapter = InfoAdapter(eventList, this)
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}