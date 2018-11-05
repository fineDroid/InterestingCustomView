package com.zcjcumt.indefineview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.findroid.interestingview.util.CustomUtil;
import com.findroid.interestingview.view.FlowLayout;



public class FlowActivity extends Activity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow_page);
		FlowLayout flowLayout = findViewById(R.id.myFlowLayout);
		View childView1 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView2 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView3 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView4 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView5 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView6 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView7 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView8 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);
		View childView9 = LayoutInflater.from(this).inflate(R.layout.item_flow_layout, null);

		TextView tv1 = (TextView) childView1.findViewById(R.id.item_flow_tv);
		tv1.setText("aa");
		TextView tv2 = (TextView) childView2.findViewById(R.id.item_flow_tv);
		tv2.setText("bbb\n adbsah");
		TextView tv3 = (TextView) childView3.findViewById(R.id.item_flow_tv);
		tv3.setText("ccccfsdfdsc\n sndjhfsdfsaj\n ddsfdsfds\ndfss\nddffsd\nfdgdfg");
		TextView tv4 = (TextView) childView4.findViewById(R.id.item_flow_tv);
		tv4.setText("jhsdfjhvsdfj\njkhjh");
		TextView tv5 = (TextView) childView5.findViewById(R.id.item_flow_tv);
		tv5.setText("eeeeeeeeeee");
		TextView tv6 = (TextView) childView6.findViewById(R.id.item_flow_tv);
		tv6.setText("fff\nsajkdjk");
		TextView tv7 = (TextView) childView7.findViewById(R.id.item_flow_tv);
		tv7.setText("gg\ndsfhjkhshfj\nbhgh");
		TextView tv8 = (TextView) childView8.findViewById(R.id.item_flow_tv);
		tv8.setText("hhhhhhhhhhh\nhsajdjgdhjg");
		TextView tv9 = (TextView) childView9.findViewById(R.id.item_flow_tv);
		tv9.setText("hhhhhhhhdsfn\njkdshfjhhh\nhsajdjgdhjg");


		ViewGroup.MarginLayoutParams layoutParams1 = new ViewGroup.MarginLayoutParams
				(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		layoutParams1.leftMargin = CustomUtil.dip2px(this, 5);
		layoutParams1.rightMargin = CustomUtil.dip2px(this, 5);
		layoutParams1.topMargin = CustomUtil.dip2px(this, 5);
		layoutParams1.bottomMargin = CustomUtil.dip2px(this, 5);

		flowLayout.addView(childView1, layoutParams1);
		flowLayout.addView(childView2, layoutParams1);
		flowLayout.addView(childView3, layoutParams1);
		flowLayout.addView(childView4, layoutParams1);
		flowLayout.addView(childView5, layoutParams1);
		flowLayout.addView(childView6, layoutParams1);
		flowLayout.addView(childView7, layoutParams1);
		flowLayout.addView(childView8, layoutParams1);
		flowLayout.addView(childView9, layoutParams1);
	}
}
