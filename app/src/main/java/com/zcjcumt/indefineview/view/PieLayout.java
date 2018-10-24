package com.zcjcumt.indefineview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcjcumt.indefineview.R;

import java.util.List;

public class PieLayout extends LinearLayout {
	private PieView mPieView;
	private LinearLayout mLinearLayout;

	public PieLayout(Context context) {
		this(context, null);
	}

	public PieLayout(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PieLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		mPieView = new PieView(context);
		LayoutParams params = new LayoutParams(0, (int) dpToPx(100));
		params.weight = 1;
		params.gravity = Gravity.CENTER;
		addView(mPieView, params);


		mLinearLayout = new LinearLayout(context);
		mLinearLayout.setBackgroundColor(Color.GREEN);
		mLinearLayout.setOrientation(VERTICAL);
		LayoutParams params2 = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
		params2.weight = 1;
		params2.gravity = Gravity.CENTER;
		addView(mLinearLayout, params2);
		initView(context);
	}

	private void initView(Context context) {
		List<PieView.PieData> pieDataList = mPieView.getData();
		for (PieView.PieData pieData : pieDataList) {
			View view = LayoutInflater.from(context).inflate(R.layout.item_chart_desc, null);
			ImageView image = (ImageView) view.findViewById(R.id.image);
			TextView name = (TextView) view.findViewById(R.id.name);
			TextView desc = (TextView) view.findViewById(R.id.desc);

			image.setBackgroundColor(pieData.getColor());
			name.setTextColor(pieData.getColor());
			desc.setTextColor(pieData.getColor());
			name.setText(pieData.getName());
			desc.setText("￥" + pieData.getName());

			LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParams.bottomMargin = (int) dpToPx(6);
			mLinearLayout.addView(view, layoutParams);
		}
	}

	private static float dpToPx(float dp) {
		return dp * ((float) Resources.getSystem().getDisplayMetrics().densityDpi / 160.0F);
	}
}
