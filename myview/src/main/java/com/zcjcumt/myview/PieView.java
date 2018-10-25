package com.zcjcumt.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zcjcumt 2018.10.12
 */

public class PieView extends View {
	private Paint mPaint;
	private List<PieData> mData;
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
		if (mData == null || mData.isEmpty() || mData.size() == 0) {
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
			for (PieData pieData : mData) {
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
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mAnimation = new PieChartAnimation();
		mAnimation.setDuration(ANIMATION_DURATION);
		setData();
	}

	private void setData() {
		mData = new ArrayList<>();
		PieData pieData1 = new PieData();
		pieData1.setName("苹果公司");
		pieData1.setValue(500f);
		pieData1.setColor(Color.parseColor("#FF9912"));

		PieData pieData2 = new PieData();
		pieData2.setName("阿里公司");
		pieData2.setValue(300f);
		pieData2.setColor(Color.parseColor("#FF6100"));

		PieData pieData3 = new PieData();
		pieData3.setName("腾讯公司");
		pieData3.setValue(100f);
		pieData3.setColor(Color.parseColor("#1E90FF"));

		mData.add(pieData1);
		mData.add(pieData2);
		mData.add(pieData3);

		caculateData();

		startAnimation(mAnimation);
		invalidate();
	}

	public List<PieData> getData() {
		return mData;
	}


	private void caculateData() {
		sumValue = 0;
		/**
		 * 计算数据总和确定颜色
		 */
		for (int i = 0; i < mData.size(); i++) {
			PieData data = mData.get(i);
			sumValue += data.getValue();
		}
		/**
		 * 计算百分比和角度
		 */
		for (int i = 0; i < mData.size(); i++) {
			PieData data = mData.get(i);
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

	public static class PieData {
		/**
		 * 用户传递的数据
		 */
		private float value; //值
		private String name;
		private String desc;
		/**
		 * 计算出来的数据
		 */
		private float percentage; //百分比
		private int color = 0;//颜色值
		private float angle = 0;//角度

		public PieData() {
		}

		public PieData(float value) {
			this.value = value;
		}

		public PieData(float value, int color) {
			this.value = value;
			this.color = color;
		}

		public PieData(float value, String name, String desc, int color) {
			this.value = value;
			this.name = name;
			this.desc = desc;
			this.color = color;
		}

		public float getValue() {
			return value;
		}

		public void setValue(float value) {
			this.value = value;
		}

		public float getPercentage() {
			return percentage;
		}

		public void setPercentage(float percentage) {
			this.percentage = percentage;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public float getAngle() {
			return angle;
		}

		public void setAngle(float angle) {
			this.angle = angle;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	public class PieChartAnimation extends Animation {
		@Override
		protected void applyTransformation(float interpolatedTime, Transformation t) {
			super.applyTransformation(interpolatedTime, t);
			if (interpolatedTime < 1.0f) {
				for (int i = 0; i < mData.size(); i++) {
					PieData data = mData.get(i);
					//通过总和来计算百分比
					float percentage = data.getValue() / sumValue;
					//通过百分比来计算对应的角度
					float angle = percentage * 360;
					//根据插入时间来计算角度
					angle = angle * interpolatedTime;
					data.setAngle(angle);
					Log.d("juju", "interpolatedTime:" + interpolatedTime);
				}
			} else {//默认显示效果
				for (int i = 0; i < mData.size(); i++) {
					//通过总和来计算百分比
					PieData data = mData.get(i);
					float percentage = data.getValue() / sumValue;
					//通过百分比来计算对应的角度
					float angle = percentage * 360;
					data.setPercentage(percentage);
					data.setAngle(angle);
					Log.d("juju", "interpolatedTime2:" + interpolatedTime);
				}
			}
			invalidate();
		}
	}
}

