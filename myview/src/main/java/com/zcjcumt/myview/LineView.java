package com.zcjcumt.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表
 * create by zcjcumt
 * 770233070@qq.com
 */

public class LineView extends View {
	private int mViewWidth;
	private int mViewHeight;
	private Paint mLinePaint;
	private Paint mBlackLinePaint;
	private Paint mMoneyTextPain;
	private Paint mTimeTextPain;

	public LineView(Context context) {
		this(context, null);
	}

	public LineView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mViewHeight = getMeasuredHeight();
		mViewWidth = getMeasuredWidth();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//竖线
		canvas.drawLine(dip2px(10), dip2px(10), dip2px(10), mViewHeight, mLinePaint);
		//横线
		canvas.drawLine(dip2px(10), mViewHeight - dip2px(3), mViewWidth, mViewHeight - dip2px(3), mLinePaint);

		int avH = mViewHeight / 10;
		int avW = mViewWidth / 10;


		for (int i = 0; i <= 10; i++) {
			canvas.drawText(String.valueOf(10 - i), 0, avH * i, mMoneyTextPain);
			if (i != 0) {
				canvas.drawText(String.valueOf(i), avW * i, mViewHeight - dip2px(10), mTimeTextPain);
			}
		}

		Path path = new Path();
		List<Point> pointList = new ArrayList<>();

		Point point1 = new Point();
		point1.set(dip2px(10), mViewHeight);

		Point point2 = new Point();
		point2.set(200, 200);

		Point point3 = new Point();
		point3.set(300, 400);

		Point point4 = new Point();
		point4.set(400, 100);

		Point point5 = new Point();
		point5.set(500, mViewHeight);

		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		pointList.add(point5);

		for (int j = 0; j < pointList.size(); j++) {
			Point startP = pointList.get(j);
			Point endP;
			if (j != pointList.size() - 1) {
				endP = pointList.get(j + 1);
				int wt = (startP.x + endP.x) / 2;

				Point p3 = new Point();
				Point p4 = new Point();

				p3.x = wt;
				p3.y = startP.y;

				p4.x = wt;
				p4.y = endP.y;

				if (j == 0) {
					path.moveTo(startP.x, startP.y);
				}

				//贝塞尔曲线
				path.cubicTo(p3.x, p3.y, p4.x, p4.y, endP.x, endP.y);
			}

		}

		canvas.drawPath(path, mBlackLinePaint);


	}

	private int dip2px(float dipValue) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	private void init() {
		if (mLinePaint == null) {
			mLinePaint = new Paint();
			initPaint(mLinePaint);
		}

		mLinePaint.setColor(Color.RED);
		mLinePaint.setStrokeWidth(dip2px(3));

		if (mBlackLinePaint == null) {
			mBlackLinePaint = new Paint();
			initPaint(mBlackLinePaint);
		}

		mBlackLinePaint.setColor(Color.BLACK);
		mBlackLinePaint.setStrokeWidth(dip2px(3));

		if (mMoneyTextPain == null) {
			mMoneyTextPain = new Paint();
			initPaint(mMoneyTextPain);
		}
		mMoneyTextPain.setColor(Color.BLUE);
		mMoneyTextPain.setTextSize(dip2px(10));
		mMoneyTextPain.setTextAlign(Paint.Align.LEFT);

		if (mTimeTextPain == null) {
			mTimeTextPain = new Paint();
			initPaint(mTimeTextPain);
		}
		mTimeTextPain.setColor(Color.GREEN);
		mTimeTextPain.setTextSize(dip2px(10));
		mTimeTextPain.setTextAlign(Paint.Align.CENTER);
	}

	/**
	 * 初始化画笔默认属性
	 */
	private void initPaint(Paint paint) {
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
	}
}
