package com.zcjcumt.indefineview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zcjcumt.myview.view.LineChartView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyBean<Integer, Integer> myBean1 = new MyBean<>();
		myBean1.setColumnData(10);
		myBean1.setRowData(100);
		myBean1.setDes("111");

		MyBean<Integer, Integer> myBean2 = new MyBean<>();
		myBean2.setColumnData(20);
		myBean2.setRowData(200);
		myBean2.setDes("222");


		MyBean<Integer, Integer> myBean3 = new MyBean<>();
		myBean3.setColumnData(10);
		myBean3.setRowData(400);
		myBean3.setDes("333");

		MyBean<Integer, Integer> myBean4 = new MyBean<>();
		myBean4.setColumnData(30);
		myBean4.setRowData(500);
		myBean4.setDes("444");

		MyBean<Integer, Integer> myBean5 = new MyBean<>();
		myBean5.setColumnData(10);
		myBean5.setRowData(600);
		myBean5.setDes("555");

		MyBean<Integer, Integer> myBean6 = new MyBean<>();
		myBean5.setColumnData(20);
		myBean5.setRowData(700);
		myBean5.setDes("666");

		List<MyBean<Integer, Integer>> dataList = new ArrayList<>();
		dataList.add(myBean1);
		dataList.add(myBean2);
		dataList.add(myBean3);
		dataList.add(myBean4);
		dataList.add(myBean5);

		LineChartView lineChartView = findViewById(R.id.myLineChart);
		LineChartView.Builder builder = new LineChartView.Builder(lineChartView);
		builder.setDatas(dataList).build();
	}

}
