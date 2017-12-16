package com.chain.javase.test.day05;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeOldProblem implements Runnable {

	private DateFormat df;

	{
		df = new SimpleDateFormat("yyyy-MM-dd");
	}

	private String time;

	public DateTimeOldProblem(String time) {
		this.time = time;
	}

	@Override
	public void run() {
		try {
			Date s = df.parse(time);
			String f = df.format(s);
			System.out.println(f);
		} catch (Exception e) {
			System.out.println("转换错误");
			// e.printStackTrace();
		}
	}

}
