package com.zcjcumt.indefineview;

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


import com.zcjcumt.myview.R;
import com.zcjcumt.myview.view.PieView;

import java.util.ArrayList;
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

		List<MyPieBean> datas = initDatas();

		mPieView = new PieView(context);
		PieView.Builder builder = new PieView.Builder(mPieView);
		builder.setDatas(datas).build();

		LayoutParams params = new LayoutParams(0, (int) dpToPx(100));
		params.weight = 1;
		params.gravity = Gravity.CENTER;
		addView(mPieView, params);


		mLinearLayout = new LinearLayout(context);
		mLinearLayout.setOrientation(VERTICAL);
		LayoutParams params2 = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
		params2.weight = 1;
		params2.gravity = Gravity.CENTER;
		addView(mLinearLayout, params2);
		initView(context);
	}

	private void initView(Context context) {
		List<MyPieBean> pieDataList = initDatas();
		for (MyPieBean pieData : pieDataList) {
			View view = LayoutInflater.from(context).inflate(R.layout.item_chart_desc, null);
			ImageView image = (ImageView) view.findViewById(R.id.image);
			TextView name = (TextView) view.findViewById(R.id.name);
			TextView desc = (TextView) view.findViewById(R.id.desc);

			image.setBackgroundColor(pieData.getColor());
			name.setTextColor(pieData.getColor());
			desc.setTextColor(pieData.getColor());
			name.setText(pieData.getName());
			desc.setText("￥" + pieData.getDesc());

			LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParams.bottomMargin = (int) dpToPx(6);
			layoutParams.leftMargin = (int) dpToPx(20);
			mLinearLayout.addView(view, layoutParams);
		}
	}

	private static float dpToPx(float dp) {
		return dp * ((float) Resources.getSystem().getDisplayMetrics().densityDpi / 160.0F);
	}

	private List<MyPieBean> initDatas() {

		List<MyPieBean> datas = new ArrayList<>();
		MyPieBean pieData1 = new MyPieBean();
		pieData1.setName("APPLE Company");
		pieData1.setValue(500f);
		pieData1.setDesc("500");
		pieData1.setColor(Color.parseColor("#FF9912"));

		MyPieBean pieData2 = new MyPieBean();
		pieData2.setName("Alibaba Company");
		pieData2.setValue(300f);
		pieData2.setDesc("300");
		pieData2.setColor(Color.parseColor("#FF6100"));

		MyPieBean pieData3 = new MyPieBean();
		pieData3.setName("Tencent Company");
		pieData3.setValue(100f);
		pieData3.setDesc("100");
		pieData3.setColor(Color.parseColor("#1E90FF"));

		datas.add(pieData1);
		datas.add(pieData2);
		datas.add(pieData3);
		return datas;
	}
}
