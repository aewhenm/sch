package com.example.nurbekkabylbai.sch.util

import android.content.Context

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
object DimensionConverter {

    fun spToPx(sp: Int, context: Context): Float {
        return sp * context.resources.displayMetrics.scaledDensity
    }

    fun dpToPx(dp: Int, context: Context): Float {
        return dp * context.resources.displayMetrics.scaledDensity
    }
}