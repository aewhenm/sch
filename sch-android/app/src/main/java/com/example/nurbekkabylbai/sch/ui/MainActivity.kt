package com.example.nurbekkabylbai.sch.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.nurbekkabylbai.sch.R
import com.example.nurbekkabylbai.sch.ResponseListener
import com.example.nurbekkabylbai.sch.db.entity.Class
import com.example.nurbekkabylbai.sch.ui.info.InfoActivity
import com.example.nurbekkabylbai.sch.ui.view.EventClickListener
import java.util.*
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import kotlin.collections.ArrayList

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
class MainActivity: AppCompatActivity(), ResponseListener, EventClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.setListener(this)
        viewModel.requestEntities()

        schedule.setListener(this)

        setupCalendar()
    }

    override fun onEventClicked(list: List<Class>) {
        Toast.makeText(baseContext, "A class clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, InfoActivity::class.java)
        intent.putExtra("list", list as Serializable)
        startActivity(intent)
    }

    override fun onResponseReceived(list: List<Class>?) {
        if(list == null)
            return

        val arrayList = ArrayList<Class>()
        for(c in list)
            arrayList.add(c)

        schedule.updateAndInvalidate(arrayList)
    }

    private fun setupCalendar() {
        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val horizontalCalendar = HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(7)   // Number of Dates cells shown on screen (default to 5).
                .configure()    // starts configuration.
                .textColor(Color.WHITE, Color.WHITE)    // default to (Color.LTGRAY, Color.WHITE).
                .selectorColor(Color.YELLOW)               // set selection indicator bar's color (default to colorAccent).
                .end()          // ends configuration.
                .build()

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                // TODO: make a request
                val weekDay = date.get(Calendar.DAY_OF_WEEK)
                viewModel.requestClasses(weekDay)
                schedule.updateAndInvalidate(ArrayList()) // TODO: remove then
                Toast.makeText(baseContext, "A date selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}