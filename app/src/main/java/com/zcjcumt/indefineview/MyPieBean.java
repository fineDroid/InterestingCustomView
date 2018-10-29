package com.zcjcumt.indefineview;

import com.zcjcumt.myview.bean.BasePieBean;

public class MyPieBean extends BasePieBean {
	private String name;
	private String desc;

	public MyPieBean(float value, int color) {
		super(value, color);
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
