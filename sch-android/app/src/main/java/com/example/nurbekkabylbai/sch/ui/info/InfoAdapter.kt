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

class ServiceAdapter(var eventList: ArrayList<Event>, internal var context: Context) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_info, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]

        holder.teacher.text = event.teacher.name
        holder.group.text = event.group.groupId + ":" + event.group.size
        holder.room.text = event.room.roomId + ":" + event.room.seatsNumber
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var teacher: TextView = itemView.findViewById<View>(R.id.teacher) as TextView
        internal var room: TextView = itemView.findViewById<View>(R.id.room) as TextView
        internal var group: TextView = itemView.findViewById<View>(R.id.group) as TextView
    }
}