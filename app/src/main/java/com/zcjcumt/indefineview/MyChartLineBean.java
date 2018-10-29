package com.zcjcumt.indefineview;

import com.zcjcumt.myview.bean.BaseChartLineBean;

public class MyChartLineBean extends BaseChartLineBean {
	private String des;

	public MyChartLineBean(int rowData, int columnData) {
		super(rowData, columnData);
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
