package com.zcjcumt.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.zcjcumt.myview.bean.BasePieBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼图
 * Create by zcjcumt 2018.10.12
 */

public class PieView extends View {
	private Paint mPaint;
	private List<BasePieBean> mData = new ArrayList<>();
	private PieChartAnimation mAnimation;
	private static final long ANIMATION_DURATION = 1000;
	private final int sStartAngle = 270;
	private float sumValue = 0;//数据值的总和
	private int mWidth, mHeight;

	public PieView(Context context) {
		this(context, null);
	}

	public PieView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int radius = Math.min(mWidth, mHeight) / 2;
		RectF rectF = new RectF(0, 0, radius * 2, radius * 2);
		if (mData.isEmpty()) {
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setColor(Color.BLACK);
			//绘制矩形
			canvas.drawRect(rectF, mPaint);

			mPaint.setStyle(Paint.Style.FILL);
			mPaint.setColor(Color.RED);
			//矩形内绘制弧形
			canvas.drawArc(rectF, 0, 360, false, mPaint);
			mPaint.setColor(Color.GREEN);

			//绘制圆
			canvas.drawCircle(radius, radius, radius / 2, mPaint);
		} else {
			float currentStartAngle = sStartAngle;
			for (BasePieBean pieData : mData) {
				mPaint.setStyle(Paint.Style.FILL);
				mPaint.setColor(pieData.getColor());
				//矩形内绘制弧形
				canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
				currentStartAngle += pieData.getAngle();
			}
			mPaint.setColor(Color.WHITE);
			//绘制圆
			canvas.drawCircle(radius, radius, radius / 2, mPaint);
		}

	}

	private void init() {
		if (mPaint == null) {
			mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);

		if (mAnimation == null) {
			mAnimation = new PieChartAnimation();
		}
		mAnimation.setDuration(ANIMATION_DURATION);
	}

	private void setDatas(List<? extends BasePieBean> data) {
		if (!mData.isEmpty()) {
			mData.clear();
		}
		mData.addAll(data);

		caculateData();
		startAnimation(mAnimation);
		invalidate();
	}

	private void caculateData() {
		if (mData.isEmpty()) {
			return;
		}
		sumValue = 0;
		/**
		 * 计算数据总和确定颜色
		 */
		for (int i = 0; i < mData.size(); i++) {
			BasePieBean data = mData.get(i);
			sumValue += data.getValue();
		}
		/**
		 * 计算百分比和角度
		 */
		for (int i = 0; i < mData.size(); i++) {
			BasePieBean data = mData.get(i);
			//通过总和来计算百分比
			float percentage = data.getValue() / sumValue;
			//通过百分比来计算对应的角度
			float angle;

			angle = (float) Math.ceil(percentage * 360);
			//设置用户数据
			data.setPercentage(percentage);
			data.setAngle(angle);
		}
	}

	public class PieChartAnimation extends Animation {
		@Override
		protected void applyTransformation(float interpolatedTime, Transformation t) {
			super.applyTransformation(interpolatedTime, t);
			if (interpolatedTime < 1.0f) {
				for (int i = 0; i < mData.size(); i++) {
					BasePieBean data = mData.get(i);
					//通过总和来计算百分比
					float percentage = data.getValue() / sumValue;
					//通过百分比来计算对应的角度
					float angle = percentage * 360;
					//根据插入时间来计算角度
					angle = angle * interpolatedTime;
					data.setAngle(angle);
				}
			} else {//默认显示效果
				for (int i = 0; i < mData.size(); i++) {
					//通过总和来计算百分比
					BasePieBean data = mData.get(i);
					float percentage = data.getValue() / sumValue;
					//通过百分比来计算对应的角度
					float angle = percentage * 360;
					data.setPercentage(percentage);
					data.setAngle(angle);
				}
			}
			invalidate();
		}
	}

	public static class Builder {
		private PieView pieView;

		public Builder(PieView pieView) {
			this.pieView = pieView;
		}

		public Builder setDatas(List<? extends BasePieBean> datas) {
			pieView.setDatas(datas);
			return this;
		}

		public void build() {
			pieView.invalidate();
		}

	}
}

