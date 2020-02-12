package com.findroid.interestingview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 圆环图
 * Create by zcj 2020.2.12
 * 770233070@qq.com
 */

public class RingView extends View {
    protected Paint mPaint;
    protected final int sStartAngle = 270;//开始角度
    protected float sumValue = 100;//数据值的总和
    protected int mWidth, mHeight;
    protected float mCurrentSweepAngle = 0;
    private float sRectFMargin = 10f;
    private static final String TAG = RingView.class.getSimpleName();

    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        customDraw(canvas);
    }

    protected void customDraw(Canvas canvas) {
        int radius = Math.min(mWidth, mHeight);
        RectF rectF = new RectF(sRectFMargin, sRectFMargin, radius - sRectFMargin, radius - sRectFMargin);
        //矩形内绘制弧形
        Log.d(TAG, "mCurrentSweepAngle: " + mCurrentSweepAngle);
        canvas.drawArc(rectF, sStartAngle, mCurrentSweepAngle, false, mPaint);
    }


    protected void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.parseColor("#FF4081"));
    }


    private void addCustom(float value) {
        float percentage = value / sumValue;
        mCurrentSweepAngle = (float) Math.ceil(percentage * 360);
    }

    private void setStrokeWidth(float width) {
        mPaint.setStrokeWidth(width);
    }

    private void setPaintColor(@ColorInt int color) {
        mPaint.setColor(color);
    }


    public static class Builder {
        private RingView ringView;

        public Builder(RingView pieView) {
            this.ringView = pieView;
        }

        public Builder setStrokeWidth(float width) {
            this.ringView.setStrokeWidth(width);
            return this;
        }

        public Builder setPaintColor(@ColorInt int color) {
            this.ringView.setPaintColor(color);
            return this;
        }


        public Builder addCustom(float value) {
            ringView.addCustom(value);
            return this;
        }

        public void build() {
            ringView.invalidate();
        }

    }
}

