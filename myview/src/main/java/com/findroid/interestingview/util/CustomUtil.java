package com.findroid.interestingview.util;

import android.content.Context;

/**
 * create by zcj
 * 770233070@qq.com
 */
public class CustomUtil {

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
}
