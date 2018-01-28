package com.example.nurbekkabylbai.sch.ui.view

import android.content.Context
import android.graphics.*
import android.support.v4.view.GestureDetectorCompat
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.example.nurbekkabylbai.sch.R
import com.example.nurbekkabylbai.sch.db.entity.Class
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
    private lateinit var mEventPaint: Paint


    // Attributes and default values
    private var mViewPaddingTop = toDp(20).toInt()
    private var mViewPaddingLeft = 0
    private val mTimeSeparatorStartX = toDp(50)
    private var mBackgroundColor = Color.rgb(232, 237, 240)
    private var mTimeTextColor = Color.rgb(30, 40, 46)
    private var mNormalTimeSize = DimensionConverter.spToPx(10, context)
    private var mAccentTimeSize = DimensionConverter.spToPx(12, context)

    private var mOkColor = Color.GREEN
    private var mBadColor = Color.RED

    private val mAccentRect = Rect()
    private val mNormalRect = Rect()

    private var mHeightDiff = 0
    private var mWidthDiff = 0

    private var mScreenWidth = 0
    private var mScreenHeight = 0

    private var m5minHeight = 0


    private val mTimeSlots = ArrayList<ArrayList<Class>>()
    private val mEventList = ArrayList<EventLayout>()

    // Event click handling
    private var mEventClickListener: EventClickListener? = null
    private var mGestureDetector: GestureDetectorCompat

    private val mGestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            if (mEventClickListener != null && !mEventList.isEmpty()) {
                for (event in mEventList) {
                    if (e.x > event .rect.left && e.x < event .rect.right && e.y > event .rect.top && e.y < event .rect.bottom) {
                        mEventClickListener?.onEventClicked(event.list)
                        return super.onSingleTapConfirmed(e)
                    }
                }
            }
            return super.onSingleTapConfirmed(e)
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }
    }

    init {
        mGestureDetector = GestureDetectorCompat(context, mGestureListener)
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(displayMetrics)
        mScreenWidth = displayMetrics.widthPixels
        mScreenHeight = displayMetrics.heightPixels

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ScheduleView, 0, 0)
        try {
            mBackgroundColor = a.getColor(R.styleable.ScheduleView_backgroundColor, mBackgroundColor)
            mTimeTextColor = a.getColor(R.styleable.ScheduleView_timeTextColor, mTimeTextColor)
            mNormalTimeSize = a.getDimensionPixelSize(R.styleable.ScheduleView_normalTimeSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mNormalTimeSize, context.resources.displayMetrics).toInt()).toFloat()
            mAccentTimeSize = a.getDimensionPixelSize(R.styleable.ScheduleView_accentTimeSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mAccentTimeSize, context.resources.displayMetrics).toInt()).toFloat()
            mOkColor = a.getColor(R.styleable.ScheduleView_okColor, mOkColor)
            mBadColor = a.getColor(R.styleable.ScheduleView_badColor, mBadColor)
        } finally {
            a.recycle()
        }

        initView()
    }

    fun setListener(listener: EventClickListener) {
        this.mEventClickListener = listener
    }

    private fun initView() {
        // Background
        mBackgroundPaint = Paint()
        mBackgroundPaint.color = mBackgroundColor

        // Separator lines
        mNormalSeparatorPaint = Paint()
        mNormalSeparatorPaint.color = Color.BLACK
        mDashedSeparatorPaint = Paint()
        mDashedSeparatorPaint.color = Color.BLACK
        mDashedSeparatorPaint.style = Paint.Style.STROKE
        mDashedSeparatorPaint.strokeWidth = toDp(1)
        mDashedSeparatorPaint.pathEffect = DashPathEffect(floatArrayOf(5f, 10f), 0f)

        // Time accented
        mAccentTimeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mAccentTimeTextPaint.color = this.mTimeTextColor
        mAccentTimeTextPaint.textSize = mAccentTimeSize.toFloat()
        mAccentTimeTextPaint.getTextBounds("09:00", 0, "09:00".length, mAccentRect)

        // Time normal
        mNormalTimeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mNormalTimeTextPaint.color = this.mTimeTextColor
        mNormalTimeTextPaint.textSize = mNormalTimeSize.toFloat()
        mNormalTimeTextPaint.getTextBounds("09:00", 0, "09:00".length, mNormalRect)
        mNormalTimeTextPaint.alpha = 100

        mEventPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mEventPaint.color = Color.GREEN
        mEventPaint.alpha = 1


        // Difference between normal and accented text
        mHeightDiff = toDp((mAccentRect.height() - mNormalRect.height()) / 2).toInt()
        mWidthDiff = toDp((mAccentRect.width() - mNormalRect.width()) / 2).toInt()

        m5minHeight = 2*(mViewPaddingTop + mAccentRect.height())
        mViewPaddingLeft = (mTimeSeparatorStartX.toInt() - mAccentRect.width())/2
    }

    fun updateAndInvalidate(list: ArrayList<Class>) {
        invalidateTimeSlots(list)
        this@ScheduleView.invalidate()
    }

    private fun toDp(value: Int): Float {
        return DimensionConverter.dpToPx(value, context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mScreenHeight = 40 * mViewPaddingTop + mViewPaddingTop/2
        this.setMeasuredDimension(mScreenWidth, mScreenHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Background
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), mBackgroundPaint)

        drawLinesAndAxes(canvas)

        drawTimes(canvas)

        drawEvents(canvas)
    }

    private fun drawLinesAndAxes(canvas: Canvas) {
        val lineStartY = mViewPaddingTop.toFloat()
        val lineStartX = mTimeSeparatorStartX
        val lineEndX = toDp(mScreenWidth)

        canvas.drawLine(lineStartX, lineStartY, lineStartX, mScreenHeight.toFloat(), mNormalSeparatorPaint)
        canvas.drawLine(lineStartX, lineStartY, lineEndX, mViewPaddingTop.toFloat(), mNormalSeparatorPaint)

        var yy = mViewPaddingTop + m5minHeight.toFloat()
        for (i in 1..157) {
            val path = Path()
            path.moveTo(lineStartX, yy)
            path.lineTo(lineEndX, yy)
            canvas.drawPath(path, mDashedSeparatorPaint)

            yy += m5minHeight.toFloat()
        }
    }

    private fun drawTimes(canvas: Canvas) {
        var yy = mViewPaddingTop + mAccentRect.height()/2
        for (i in 9..21) {
            // 09:00 ACCENTED
            canvas.drawText(timeFormat(i, 0), mViewPaddingLeft.toFloat(), yy.toFloat(), mAccentTimeTextPaint)
            yy += m5minHeight
        }
    }

    private fun timeFormat(hour: Int, minute: Int): String {
        return String.format("%2d:%02d", hour, minute)
    }

    private fun invalidateTimeSlots(list: ArrayList<Class>) {
        mTimeSlots.clear()
        for(i in 0 until 12)
            mTimeSlots.add(ArrayList())

        for(c in list) {
            mTimeSlots[c.startHour.toInt()].add(c)
        }
    }

    private fun drawEvents(canvas: Canvas) {
        for(i in 0 until mTimeSlots.size) {
            if(mTimeSlots[i].isEmpty())
                continue

            val mEventRect = Rect()
            mEventRect.left = mTimeSeparatorStartX.toInt()
            mEventRect.right = mScreenWidth
            mEventRect.top = getEventTop(i)
            mEventRect.bottom = getEventBottom(i)

            mEventList.add(EventLayout(mEventRect, mTimeSlots[i]))

            mEventPaint.color = if(mTimeSlots[i].size > 1) mBadColor else mOkColor
            mEventPaint.alpha = 170

            canvas.drawRect(mEventRect, mEventPaint)
        }
    }

    private fun getEventTop(index: Int): Int {
        return mViewPaddingTop + m5minHeight*index + 1
    }

    private fun getEventBottom(index: Int): Int {
        return mViewPaddingTop + m5minHeight*(index+1) - 1
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return mGestureDetector.onTouchEvent(event)
    }
}