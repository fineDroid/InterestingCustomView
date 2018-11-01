package com.findroid.interestingview.bean;

public abstract class BaseChartLineBean {

	public BaseChartLineBean(int rowData, int columnData) {
		this.rowData = rowData;
		this.columnData = columnData;
	}

	private int rowData;
	private int columnData;

	public int getRowData() {
		return rowData;
	}

	public void setRowData(int rowData) {
		this.rowData = rowData;
	}

	public int getColumnData() {
		return columnData;
	}

	public void setColumnData(int columnData) {
		this.columnData = columnData;
	}
}
