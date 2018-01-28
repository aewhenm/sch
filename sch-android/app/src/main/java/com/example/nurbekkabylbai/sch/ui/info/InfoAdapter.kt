package com.example.nurbekkabylbai.sch.ui.info

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nurbekkabylbai.sch.R
import com.example.nurbekkabylbai.sch.db.entity.Event

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */

class InfoAdapter(var eventList: ArrayList<Event>, internal var context: Context) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_info, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]

        holder.course.text = "Предмет: " + event.course.name
        holder.teacher.text = "\nПреподователь: " + event.teacher.name
        holder.group.text = "\nГруппа: " + event.group.groupId + ". Количество студентов " + event.group.size
        holder.room.text = "\nКабинет: " + event.room.roomId + ". Вместимость " + event.room.seatsNumber +
                ". Оборудован: " + if(event.room.isEquiped) "ДА" else "НЕТ"
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var teacher: TextView = itemView.findViewById<View>(R.id.teacher) as TextView
        internal var room: TextView = itemView.findViewById<View>(R.id.room) as TextView
        internal var group: TextView = itemView.findViewById<View>(R.id.group) as TextView
        internal var course: TextView = itemView.findViewById<View>(R.id.course) as TextView
    }
}