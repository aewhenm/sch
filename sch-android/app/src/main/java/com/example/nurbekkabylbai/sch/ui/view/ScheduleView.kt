package com.example.nurbekkabylbai.sch.ui.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
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
    private var mTextMarginTop = 100f
    private var mViewPaddingLeft = 0f
    private var mBackgroundColor = Color.rgb(232, 237, 240)
    private var mSeparatorColor = Color.rgb(196, 206, 210)
    private var mTimeTextColor = Color.rgb(30, 40, 46)
    private var mNormalTimeSize = DimensionConverter.pxToSp(10, context)
    private var mAccentTimeSize = DimensionConverter.pxToSp(12, context)
    private var mOrderBackgroundColor = Color.rgb(0, 74, 211)

    private val accentRect = Rect()
    private val normalRect = Rect()

    private var mHeightDiff = 0
    private var mWidthDiff = 0

    init {

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
        mDashedSeparatorPaint.strokeWidth = DimensionConverter.dpToPx(1, context)
        mDashedSeparatorPaint.pathEffect = DashPathEffect(floatArrayOf(5f, 10f), 0f)

        // Time accented
        mAccentTimeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mAccentTimeTextPaint.color = this.mTimeTextColor
        mAccentTimeTextPaint.textSize = mAccentTimeSize
        mAccentTimeTextPaint.getTextBounds("09:00", 0, "09:00".length, accentRect)

        mTextMarginTop = DimensionConverter.dpToPx(accentRect.height(), context)
        mViewPaddingLeft = DimensionConverter.dpToPx((mTextMarginTop/2).toInt(), context)

        // Time normal
        mNormalTimeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mNormalTimeTextPaint.color = this.mTimeTextColor
        mNormalTimeTextPaint.textSize = mNormalTimeSize
        mNormalTimeTextPaint.getTextBounds("09:00", 0, "09:00".length, normalRect)
        mNormalTimeTextPaint.alpha = 100

        // Difference between normal and accented text
        mHeightDiff = DimensionConverter.dpToPx((accentRect.height() - normalRect.height()) / 2, context).toInt()
        mWidthDiff = DimensionConverter.dpToPx((accentRect.width() - normalRect.width()) / 2, context).toInt()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Background
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), mBackgroundPaint)
    }
}