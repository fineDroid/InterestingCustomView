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
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int paddingLeft = getPaddingLeft();
		int paddingRight = getPaddingRight();
		int paddingBottom = getPaddingBottom();
		int paddingTop = getPaddingTop();

		int count = getChildCount();
		int maxWidth = 0;
		int totalHeight = 0;
		int lineWidth = 0;
		int lineHeight = 0;
		int extraWidth = widthSize - paddingLeft - paddingRight;

		measureChildren(widthMeasureSpec, heightMeasureSpec);
		for (int i = 0; i < count; i++) {
			View view = getChildAt(i);
			int leftMargin = 0;
			int rightMargin = 0;
			int topMargin = 0;
			int bottomMargin = 0;
			LayoutParams mlp = view.getLayoutParams();
			if (mlp instanceof MarginLayoutParams) {
				leftMargin = ((MarginLayoutParams) mlp).leftMargin;
				rightMargin = ((MarginLayoutParams) mlp).rightMargin;
				topMargin = ((MarginLayoutParams) mlp).topMargin;
				bottomMargin = ((MarginLayoutParams) mlp).bottomMargin;
			}
			if (view != null && view.getVisibility() != GONE) {
				if (lineWidth + view.getMeasuredWidth() + leftMargin + rightMargin > extraWidth) {
					totalHeight += lineHeight;
					lineWidth = view.getMeasuredWidth() + leftMargin + rightMargin;
					lineHeight = view.getMeasuredHeight() + topMargin + bottomMargin;
					maxWidth = widthSize;
				} else {
					lineWidth += (view.getMeasuredWidth() + leftMargin + rightMargin);
				}
				lineHeight = Math.max(lineHeight, view.getMeasuredHeight() + topMargin + bottomMargin);
			}
		}
		totalHeight += lineHeight;
		maxWidth = Math.max(lineWidth, maxWidth);

		totalHeight = resolveSize(totalHeight + paddingBottom + paddingTop, heightMeasureSpec);
		lineWidth = resolveSize(maxWidth + paddingLeft + paddingRight, widthMeasureSpec);
		setMeasuredDimension(lineWidth, totalHeight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int flowWidth = getWidth();
		int childLeft = 0;
		int childTop = 0;

		int parentPaddingLeft = getPaddingLeft();
		int parentPaddingRight = getPaddingRight();
		int parentPaddingTop = getPaddingTop();

		int maxHeight = 0;
		int lastTopMargin = 0;
		int lastBottomMargin = 0;
		for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
			View childView = getChildAt(i);
			if (childView.getVisibility() == GONE) {
				continue;
			}
			int childWidth = childView.getMeasuredWidth();
			int childHeight = childView.getMeasuredHeight();

			LayoutParams mlp = childView.getLayoutParams();
			int leftMargin = 0;
			int rightMargin = 0;
			int topMargin = 0;
			int bottomMargin = 0;

			if (mlp instanceof MarginLayoutParams) {
				leftMargin = ((MarginLayoutParams) mlp).leftMargin;
				rightMargin = ((MarginLayoutParams) mlp).rightMargin;
				topMargin = ((MarginLayoutParams) mlp).topMargin;
				bottomMargin = ((MarginLayoutParams) mlp).bottomMargin;
			}

			if (childLeft + childWidth + leftMargin + rightMargin > flowWidth - parentPaddingLeft - parentPaddingRight) {
				//+last view's margin
				childTop += (maxHeight + lastTopMargin + lastBottomMargin);
				maxHeight = childHeight;
				childLeft = 0;
			} else {
				lastTopMargin = topMargin;
				lastBottomMargin = bottomMargin;
				maxHeight = Math.max(maxHeight, childHeight);
			}
			//current view's margin
			int left = childLeft + leftMargin + parentPaddingLeft;
			int top = childTop + topMargin + parentPaddingTop;
			int right = left + childWidth;
			int bottom = top + childHeight;

			childView.layout(left, top, right, bottom);
			//+last view's margin
			childLeft += (leftMargin + childWidth + rightMargin);
		}
	}
}
