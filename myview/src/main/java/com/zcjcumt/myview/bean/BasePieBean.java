package com.zcjcumt.myview.bean;

public abstract class BasePieBean {
	/**
	 * 用户传递的数据
	 */
	private float value; //值
	private String name;
	private String desc;
	/**
	 * 计算出来的数据
	 */
	private float percentage; //百分比
	private int color = 0;//颜色值
	private float angle = 0;//角度

	public BasePieBean() {
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
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
