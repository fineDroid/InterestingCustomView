package com.findroid.interestingview.bean;

public abstract class BasePieBean {
	private float value;
	private int color;

	private float percentage; //百分比
	private float angle = 0;//角度

	public BasePieBean(float value, int color) {
		this.value = value;
		this.color = color;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}


	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
}
