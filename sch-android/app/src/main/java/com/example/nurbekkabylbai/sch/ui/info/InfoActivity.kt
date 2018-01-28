package com.example.nurbekkabylbai.sch.ui.info

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.nurbekkabylbai.sch.db.entity.Class

/**
 * Created by Nurbek Kabylbay on 28.01.2018.
 */
class InfoActivity: AppCompatActivity() {

    private var list: ArrayList<Class>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent

        list = intent.getSerializableExtra("list") as ArrayList<Class>

        if(list != null)
            Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show()

    }
}