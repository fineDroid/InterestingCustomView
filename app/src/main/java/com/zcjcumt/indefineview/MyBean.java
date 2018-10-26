package com.zcjcumt.indefineview;

import com.zcjcumt.myview.bean.BaseBean;

public class MyBean<T, K> extends BaseBean {
	private String des;
	private String esc;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getEsc() {
		return esc;
	}

	public void setEsc(String esc) {
		this.esc = esc;
	}
}
