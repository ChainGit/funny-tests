package com.chain.javase.test.day05;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeOldThreadLocal {

	private static final ThreadLocal<DateFormat> tl = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	public void parse(String time) throws Exception {
		DateFormat df = tl.get();
		Date date = df.parse(time);
		String s = df.format(date);
		System.out.println(s);
	}
}
