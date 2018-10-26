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

		MyChartLineBean myBean1 = new MyChartLineBean();
		myBean1.setColumnData(10);
		myBean1.setRowData(100);
		myBean1.setDes("111");

		MyChartLineBean myBean2 = new MyChartLineBean();
		myBean2.setColumnData(20);
		myBean2.setRowData(200);
		myBean2.setDes("222");


		MyChartLineBean myBean3 = new MyChartLineBean();
		myBean3.setColumnData(10);
		myBean3.setRowData(400);
		myBean3.setDes("333");

		MyChartLineBean myBean4 = new MyChartLineBean();
		myBean4.setColumnData(30);
		myBean4.setRowData(500);
		myBean4.setDes("444");

		MyChartLineBean myBean5 = new MyChartLineBean();
		myBean5.setColumnData(20);
		myBean5.setRowData(600);
		myBean5.setDes("555");

		MyChartLineBean myBean6 = new MyChartLineBean();
		myBean6.setColumnData(30);
		myBean6.setRowData(700);
		myBean6.setDes("666");

		List<MyChartLineBean> dataList = new ArrayList<>();
		dataList.add(myBean1);
		dataList.add(myBean2);
		dataList.add(myBean3);
		dataList.add(myBean4);
		dataList.add(myBean5);
		dataList.add(myBean6);

		LineChartView lineChartView = findViewById(R.id.myLineChart);
		LineChartView.Builder builder = new LineChartView.Builder(lineChartView);
		builder.setDatas(dataList).build();
	}

}
