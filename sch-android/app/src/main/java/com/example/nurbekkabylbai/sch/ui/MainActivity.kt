package com.example.nurbekkabylbai.sch.ui

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.nurbekkabylbai.sch.R
import java.util.*
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener





/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                Toast.makeText(baseContext, "A date selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}