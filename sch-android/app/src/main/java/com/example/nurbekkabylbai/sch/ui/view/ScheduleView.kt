package com.example.nurbekkabylbai.sch.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import com.example.nurbekkabylbai.sch.R
import com.example.nurbekkabylbai.sch.util.DimensionConverter

/**
 * Created by Nurbek Kabylbay on 27.01.2018.
 */
class ScheduleView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val displayMetrics = DisplayMetrics()

    // Paints of main elements and background
    private lateinit var mBackgroundPaint: Paint
    private lateinit var mAccentTimeTextPaint: Paint
    private lateinit var mNormalTimeTextPaint: Paint
    private lateinit var mNormalSeparatorPaint: Paint
    private lateinit var mDashedSeparatorPaint: Paint

    // Attributes and default values
    private var mViewPaddingTop = toDp(20).toInt()
    private var mViewPaddingLeft = 0
    private val mTimeSeparatorStartX = toDp(50)
    private var mBackgroundColor = Color.rgb(232, 237, 240)
    private var mSeparatorColor = Color.rgb(196, 206, 210)
    private var mTimeTextColor = Color.rgb(30, 40, 46)
    private var mNormalTimeSize = DimensionConverter.spToPx(10, context)
    private var mAccentTimeSize = DimensionConverter.spToPx(12, context)

    private val accentRect = Rect()
    private val normalRect = Rect()

    private var mHeightDiff = 0
    private var mWidthDiff = 0

    private var mScreenWidth = 0
    private var mScreenHeight = 0

    private var m5minHeight = 0


    init {
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(displayMetrics)
        mScreenWidth = displayMetrics.widthPixels
        mScreenHeight = displayMetrics.heightPixels

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ScheduleView, 0, 0)
        try {
            mBackgroundColor = a.getColor(R.styleable.ScheduleView_backgroundColor, mBackgroundColor)
            mTimeTextColor = a.getColor(R.styleable.ScheduleView_timeTextColor, mTimeTextColor)
            mSeparatorColor = a.getColor(R.styleable.ScheduleView_separatorColor, mSeparatorColor)
            mNormalTimeSize = a.getDimensionPixelSize(R.styleable.ScheduleView_normalTimeSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mNormalTimeSize, context.resources.displayMetrics).toInt()).toFloat()
            mAccentTimeSize = a.getDimensionPixelSize(R.styleable.ScheduleView_accentTimeSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mAccentTimeSize, context.resources.displayMetrics).toInt()).toFloat()
        } finally {
            a.recycle()
        }

        initView()
    }

    private fun initView() {
        // Background
        mBackgroundPaint = Paint()
        mBackgroundPaint.color = mBackgroundColor

        // Separator lines
        mNormalSeparatorPaint = Paint()
        mNormalSeparatorPaint.color = mSeparatorColor
        mDashedSeparatorPaint = Paint()
        mDashedSeparatorPaint.color = mSeparatorColor
        mDashedSeparatorPaint.style = Paint.Style.STROKE
        mDashedSeparatorPaint.strokeWidth = toDp(1)
        mDashedSeparatorPaint.pathEffect = DashPathEffect(floatArrayOf(5f, 10f), 0f)

        // Time accented
        mAccentTimeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mAccentTimeTextPaint.color = this.mTimeTextColor
        mAccentTimeTextPaint.textSize = mAccentTimeSize.toFloat()
        mAccentTimeTextPaint.getTextBounds("09:00", 0, "09:00".length, accentRect)

        // Time normal
        mNormalTimeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mNormalTimeTextPaint.color = this.mTimeTextColor
        mNormalTimeTextPaint.textSize = mNormalTimeSize.toFloat()
        mNormalTimeTextPaint.getTextBounds("09:00", 0, "09:00".length, normalRect)
        mNormalTimeTextPaint.alpha = 100

        // Difference between normal and accented text
        mHeightDiff = toDp((accentRect.height() - normalRect.height()) / 2).toInt()
        mWidthDiff = toDp((accentRect.width() - normalRect.width()) / 2).toInt()

        m5minHeight = mViewPaddingTop + accentRect.height()
        mViewPaddingLeft = (mTimeSeparatorStartX.toInt() - accentRect.width())/2
    }

    private fun toDp(value: Int): Float {
        return DimensionConverter.dpToPx(value, context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mScreenHeight = 158 * mViewPaddingTop + 157 * accentRect.height() + mViewPaddingTop
        this.setMeasuredDimension(mScreenWidth, mScreenHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Background
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), mBackgroundPaint)

        drawLinesAndAxes(canvas)

        drawTimes(canvas)
    }

    private fun drawLinesAndAxes(canvas: Canvas) {
        val lineStartY = mViewPaddingTop.toFloat()
        val lineStartX = mTimeSeparatorStartX
        val lineEndX = toDp(mScreenWidth)

        val accentRectHeight = accentRect.height()

        canvas.drawLine(lineStartX, lineStartY, lineStartX, mScreenHeight.toFloat(), mNormalSeparatorPaint)
        canvas.drawLine(lineStartX, lineStartY, lineEndX, mViewPaddingTop.toFloat(), mNormalSeparatorPaint)

        var yy = mViewPaddingTop + m5minHeight.toFloat()
        for (i in 1..157) {
            val path = Path()
            path.moveTo(lineStartX, yy)
            path.lineTo(lineEndX, yy)
            canvas.drawPath(path, mDashedSeparatorPaint)

            yy += mViewPaddingTop + accentRectHeight
        }

    }

    private fun drawTimes(canvas: Canvas) {
        val marginTop = (m5minHeight - accentRect.height()) / 2
        val start = mViewPaddingTop
        val accentedTime = marginTop + accentRect.height()
        val normalTime = marginTop + mHeightDiff + normalRect.height()
        var hourCounter = 0

        var yy = 0
        for (i in 9..21) {
            // 09:00 ACCENTED
            yy = start + hourCounter * m5minHeight + accentedTime
            canvas.drawText(timeFormat(i, 0), mViewPaddingLeft.toFloat(), yy.toFloat(), mAccentTimeTextPaint)

            // 09:15
            yy = start + (hourCounter + 3) * m5minHeight + normalTime
            canvas.drawText(timeFormat(i, 15), (mWidthDiff + mViewPaddingLeft).toFloat(), yy.toFloat(), mNormalTimeTextPaint)

            // 09:30 ACCENTED
            yy = start + (hourCounter + 6) * m5minHeight + accentedTime
            canvas.drawText(timeFormat(i, 30), mViewPaddingLeft.toFloat(), yy.toFloat(), mAccentTimeTextPaint)

            // 09:45
            yy = start + (hourCounter + 9) * m5minHeight + normalTime
            canvas.drawText(timeFormat(i, 45), (mWidthDiff + mViewPaddingLeft).toFloat(), yy.toFloat(), mNormalTimeTextPaint)

            hourCounter += 12
        }
    }

    private fun timeFormat(hour: Int, minute: Int): String {
        return String.format("%2d:%02d", hour, minute)
    }

}