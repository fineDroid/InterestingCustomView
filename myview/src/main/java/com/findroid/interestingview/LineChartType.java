package com.findroid.interestingview;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by zcj
 * 770233070@qq.com
 */
@IntDef({LineChartType.CURVE, LineChartType.LINE})
@Retention(RetentionPolicy.SOURCE)
public @interface LineChartType {
	//贝塞尔曲线图
	int CURVE = 1;

	//折线图
	int LINE = 2;
}
