package com.zcjcumt.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zcjcumt.myview.LineChartType;
import com.zcjcumt.myview.bean.BaseChartLineBean;
import com.zcjcumt.myview.util.CustomUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * 折线图
 * create by zcj
 * 770233070@qq.com
 */

public class LineChartView extends View {

	protected int mViewWidth;
	protected int mViewHeight;
	protected float mLineChartWidth;
	protected float mLineChartHeight;

	protected Paint mCoordinateLinePaint;
	protected Paint mChartLinePaint;
	protected Paint mColumnLineTextPaint;
	protected Paint mRowLineTextPaint;

	//坐标轴绘制起止间距
	protected float mChartStartLeft = CustomUtil.dip2px(getContext(), 20);
	protected float mChartStartTop = CustomUtil.dip2px(getContext(), 20);
	protected float mChartStartRight = CustomUtil.dip2px(getContext(), 20);
	protected float mChartStartBottom = CustomUtil.dip2px(getContext(), 20);

	//坐标线宽度
	protected float mCoordinateLineStrokeWidth = CustomUtil.dip2px(getContext(), 2);
	protected float mLineChartStrokeWidth = CustomUtil.dip2px(getContext(), 2);

	//line色值
	protected int mCoordinateLineColor = Color.RED;
	protected int mChartLineColor = Color.BLACK;

	//坐标轴文字大小
	protected float mRowLineTextSize = CustomUtil.dip2px(getContext(), 10);
	protected float mColumnLineTextSize = CustomUtil.dip2px(getContext(), 10);

	//坐标轴文字起始距离
	protected float mRowLineTextStart = CustomUtil.dip2px(getContext(), 6);
	protected float mColumnLineTextStart = CustomUtil.dip2px(getContext(), 6);

	//横列坐标轴行数
	protected int mRowLineNumbers = 10;
	protected int mColumnLineNumbers = 5;

	//曲线类型
	@LineChartType
	private int mCharType = LineChartType.CURVE;

	private List<BaseChartLineBean> mDatas = new ArrayList<>();

	public LineChartView(Context context) {
		this(context, null);
	}

	public LineChartView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mViewHeight = getMeasuredHeight();
		mViewWidth = getMeasuredWidth();
		mLineChartHeight = mViewHeight - mChartStartBottom - mChartStartTop;
		mLineChartWidth = mViewWidth - mChartStartRight - mChartStartLeft;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//paint
		setPaint();
		drawCoordinateLine(canvas);
		if (!mDatas.isEmpty()) {
			drawChartLine(canvas);
		}
	}

	protected void drawCoordinateLine(Canvas canvas) {
		//columnLine
		canvas.drawLine(mChartStartLeft, mChartStartTop, mChartStartLeft, mViewHeight - mChartStartBottom, mCoordinateLinePaint);
		//rowLine
		canvas.drawLine(mChartStartLeft, mViewHeight - mChartStartBottom - mCoordinateLineStrokeWidth / 2,
				mViewWidth - mChartStartRight, mViewHeight - mChartStartBottom - mCoordinateLineStrokeWidth / 2, mCoordinateLinePaint);
	}

	protected void setPaint() {
		if (mCoordinateLinePaint == null) {
			mCoordinateLinePaint = new Paint();
		}
		initPaint(mCoordinateLinePaint);

		mCoordinateLinePaint.setColor(mCoordinateLineColor);
		mCoordinateLinePaint.setStrokeWidth(mCoordinateLineStrokeWidth);

		if (mChartLinePaint == null) {
			mChartLinePaint = new Paint();
		}
		initPaint(mChartLinePaint);

		mChartLinePaint.setColor(mChartLineColor);
		mChartLinePaint.setStrokeWidth(mLineChartStrokeWidth);

		if (mColumnLineTextPaint == null) {
			mColumnLineTextPaint = new Paint();
		}
		initPaint(mColumnLineTextPaint);
		mColumnLineTextPaint.setColor(mCoordinateLineColor);
		mColumnLineTextPaint.setTextSize(mColumnLineTextSize);
		mColumnLineTextPaint.setTextAlign(Paint.Align.LEFT);

		if (mRowLineTextPaint == null) {
			mRowLineTextPaint = new Paint();
		}
		initPaint(mRowLineTextPaint);
		mRowLineTextPaint.setColor(mCoordinateLineColor);
		mRowLineTextPaint.setTextSize(mRowLineTextSize);
		mRowLineTextPaint.setTextAlign(Paint.Align.CENTER);
	}


	private void initPaint(Paint paint) {
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
	}

	private int calculateRowMax() {
		int max = 0;
		if (mDatas != null && !mDatas.isEmpty()) {
			for (BaseChartLineBean baseChartLineBean : mDatas) {
				max = baseChartLineBean.getRowData() > max ? baseChartLineBean.getRowData() : max;
			}
		}
		return max;
	}

	private int calculateColumnMax() {
		int max = 0;
		if (mDatas != null && !mDatas.isEmpty()) {
			for (BaseChartLineBean baseChartLineBean : mDatas) {
				max = baseChartLineBean.getColumnData() > max ? baseChartLineBean.getColumnData() : max;
			}
		}
		return max;
	}

	protected void drawChartLine(Canvas canvas) {
		final int columnMax = calculateColumnMax();
		final int rowMax = calculateRowMax();

		if (columnMax == 0 || rowMax == 0) {
			return;
		}

		float avH = mLineChartHeight / mColumnLineNumbers;
		float avW = mLineChartWidth / mRowLineNumbers;

		for (int i = 0; i < mColumnLineNumbers; i++) {
			//column coordinate text
			canvas.drawText(String.valueOf(columnMax - i * (columnMax / mColumnLineNumbers)), mColumnLineTextStart, avH * i + mChartStartTop + mColumnLineTextSize, mColumnLineTextPaint);
			//every row line
//			canvas.drawLine(mChartStartLeft, avH * i + mChartStartTop, mLineChartWidth + mChartStartLeft, avH * i + mChartStartTop, mCoordinateLinePaint);
		}


		for (int i = 0; i <= mRowLineNumbers; i++) {
			//row coordinate text
			canvas.drawText(String.valueOf(i * (rowMax / mRowLineNumbers)), avW * i + mChartStartLeft, mViewHeight - mRowLineTextStart, mRowLineTextPaint);
			//every column line
//			canvas.drawLine(avW * i + mChartStartLeft, mChartStartTop, avW * i + mChartStartLeft, mLineChartHeight + mChartStartTop, mCoordinateLinePaint);
		}

		drawChart(canvas, columnMax, rowMax);
	}

	private void drawChart(Canvas canvas, final int columnMax, final int rowMax) {
		Path path = new Path();
		List<Point> pointList = calculatePoints(columnMax, rowMax);
		if (pointList == null || pointList.isEmpty()) {
			return;
		}
		for (int j = 0; j < pointList.size(); j++) {
			Point startP = pointList.get(j);
			if (mCharType == LineChartType.CURVE) {
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
			} else if (mCharType == LineChartType.LINE) {
				if (j == 0) {
					path.moveTo(startP.x, startP.y);
				} else {
					//折线
					path.lineTo(startP.x, startP.y);
				}
			}
		}
		canvas.drawPath(path, mChartLinePaint);
	}

	private List<Point> calculatePoints(final int columnMax, final int rowMax) {
		List<Point> pointList = new ArrayList<>();
		if (mDatas != null && !mDatas.isEmpty()) {
			for (BaseChartLineBean baseChartLineBean : mDatas) {
				float x = mChartStartLeft + baseChartLineBean.getRowData() * (mLineChartWidth / rowMax);
				float y = mChartStartTop + mLineChartHeight - baseChartLineBean.getColumnData() * (mLineChartHeight / columnMax);
				Point point = new Point();
				point.set((int) x, (int) y);
				pointList.add(point);
			}
		}

		return pointList;
	}

	public static class Builder {
		private LineChartView lineChartView;

		public Builder(LineChartView lineChartView) {
			this.lineChartView = lineChartView;
		}

		public Builder setDatas(List<? extends BaseChartLineBean> datas) {
			if (!lineChartView.mDatas.isEmpty()) {
				lineChartView.mDatas.clear();
			}
			lineChartView.mDatas.addAll(datas);
			return this;
		}

		public Builder setColumnLineNumbers(int numbers) {
			if (numbers < 1) {
				throw new IllegalArgumentException("column numbers need more 1");
			}
			lineChartView.mColumnLineNumbers = numbers;
			return this;
		}

		public Builder setRowLineNumbers(int numbers) {
			if (numbers < 1) {
				throw new IllegalArgumentException("row numbers need more 1");
			}
			lineChartView.mRowLineNumbers = numbers;
			return this;
		}

		public Builder setCoordinateLineColor(@ColorInt int color) {
			lineChartView.mCoordinateLineColor = color;
			return this;
		}

		public Builder setChartLineColor(@ColorInt int color) {
			lineChartView.mChartLineColor = color;
			return this;
		}

		public Builder setCoordinateLineStrokeWidth(float strokeWidthPx) {
			lineChartView.mCoordinateLineStrokeWidth = strokeWidthPx;
			return this;
		}

		public Builder setLineChartStrokeWidth(float strokeWidthPx) {
			lineChartView.mLineChartStrokeWidth = strokeWidthPx;
			return this;
		}

		public Builder setRowLineTextSize(float sizePx) {
			lineChartView.mRowLineTextSize = sizePx;
			return this;
		}

		public Builder setColumnLineTextSize(float sizePx) {
			lineChartView.mColumnLineTextSize = sizePx;
			return this;
		}

		public Builder setLineType(@LineChartType int type) {
			lineChartView.mCharType = type;
			return this;
		}

		public void build() {
			lineChartView.invalidate();
		}
	}

}


