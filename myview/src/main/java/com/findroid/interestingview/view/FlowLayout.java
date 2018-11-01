package com.findroid.interestingview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
/**
 * 自适应布局
 * create by zcj
 * 770233070@qq.com
 */
public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context) {
		this(context, null);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int parentPaddingLeft = getPaddingLeft();
		int parentPaddingRight = getPaddingRight();
		int parentPaddingTop = getPaddingTop();
		int parentPaddingBottom = getPaddingBottom();

		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		int resultWidth = 0;
		int resultHeight = 0;

		int lineWidth = 0;
		int lineHeight = 0;

		for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
			View childView = getChildAt(i);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);

			int childWidth = childView.getMeasuredWidth();
			int childHeight = childView.getMeasuredHeight();

			MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
			int realChildWidth = childWidth + mlp.leftMargin + mlp.rightMargin;
			int realChildHeight = childHeight + mlp.topMargin + mlp.bottomMargin;

			if (lineWidth + realChildWidth > sizeWidth - parentPaddingLeft - parentPaddingRight) {
				resultWidth = Math.max(lineWidth, realChildWidth);
				resultHeight += realChildHeight;

				lineWidth = realChildWidth;
				lineHeight = realChildHeight;

			} else {
				lineWidth += realChildWidth;
				lineHeight = Math.max(lineHeight, realChildHeight);
			}

			if (i == childCount - 1) {
				resultWidth = Math.max(lineWidth, resultWidth);
				resultHeight += lineHeight;
			}

			setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : resultWidth + parentPaddingLeft + parentPaddingRight,
					modeHeight == MeasureSpec.EXACTLY ? sizeHeight : resultHeight + parentPaddingTop + parentPaddingBottom);
		}

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int flowWidth = getWidth();
		int childLeft = 0;
		int childTop = 0;

		int parentPaddingLeft = getPaddingLeft();
		int parentPaddingRight = getPaddingRight();
		int parentPaddingTop = getPaddingTop();
		int parentPaddingBottom = getPaddingBottom();

		for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
			View childView = getChildAt(i);
			if (childView.getVisibility() == GONE) {
				continue;
			}
			int childWidth = childView.getMeasuredWidth();
			int childHeight = childView.getMeasuredHeight();

			MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();

			if (childLeft + childWidth + mlp.leftMargin + mlp.rightMargin > flowWidth - parentPaddingLeft - parentPaddingRight) {
				childTop += (childHeight + mlp.topMargin + mlp.bottomMargin);
				childLeft = 0;
			}
			int left = childLeft + mlp.leftMargin + parentPaddingLeft;
			int top = childTop + mlp.topMargin + parentPaddingTop;
			int right = left + childWidth + parentPaddingRight;
			int bottom = top + childHeight + parentPaddingBottom;

			childView.layout(left, top, right, bottom);

			childLeft += (mlp.leftMargin + childWidth + mlp.rightMargin);
		}
	}

	@Override
	protected LayoutParams generateLayoutParams(LayoutParams p) {
		return new MarginLayoutParams(p);
	}
}
