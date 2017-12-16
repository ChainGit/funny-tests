package com.chain.javase.test.day05;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.junit.Test;

/**
 * Java 旧的的时间日期：Date,TimeZone,Locale,Calendar,DateFormat
 * 
 * @author Chain
 *
 */
public class DataTimeOldTest {

	// Date表示时间（距离纪元时Epoch Time的毫秒数），原先Date中表示日期的处理功能被废除
	@Test
	public void test1() {
		Date date = new Date();
		System.out.println(date.getTime());
		System.out.println(date);

		Date date2 = new Date(2017 - 1900, 2, 12, 12, 34, 12);
		System.out.println(date2);
	}

	// Date和SQL的结合
	@Test
	public void test2() {
		// java.sql.Date,java.sql.Time,java.sql.Timestamp均是继承于java.util.Date
		java.util.Date date = new Date();

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println(sqlDate.getTime());
		System.out.println(sqlDate);

		java.sql.Time sqlTime = new java.sql.Time(date.getTime());
		System.out.println(sqlTime.getTime());
		System.out.println(sqlTime);

		java.sql.Timestamp sqlDateTime = new java.sql.Timestamp(date.getTime());
		System.out.println(sqlDateTime);
	}

	// TimeZone：时间区，格林尼治时间为0，中国处在东八区，GMT+8:00
	@Test
	public void test3() {
		TimeZone timeZone = new SimpleTimeZone(8, "Asia/Shanghai");
		System.out.println(timeZone.getID());

		System.out.println(TimeZone.getDefault().getID());
	}

	// Locale： 表示国家和语言，如zh_CN,zh_TW,en_US,en_UK
	@Test
	public void test4() {
		Locale locale = new Locale("zh", "CN");
		System.out.println(locale);

		System.out.println(locale.getLanguage());
		System.out.println(locale.getCountry());

		System.out.println(Locale.CHINA);
		System.out.println(Locale.US);

		System.out.println(Locale.ENGLISH);
		System.out.println(Locale.CHINESE);

		System.out.println(Locale.getDefault());
	}

	// Calender：表示对日期的处理
	@Test
	public void test5() {
		// 阳历
		Calendar calendar = new GregorianCalendar();

		Date date = calendar.getTime();
		System.out.println(date.getTime());
		System.out.println(date);

		TimeZone timeZone = calendar.getTimeZone();
		System.out.println(timeZone.getID());

		Locale[] locales = Calendar.getAvailableLocales();
		System.out.println(Arrays.asList(locales));

	}

	// DateFormat：将时间和字符串形式的转换
	@Test
	public void test6() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		System.out.println(df.format(date));

		TimeZone timeZone = df.getTimeZone();
		System.out.println(timeZone.getID());

		Locale[] locales = DateFormat.getAvailableLocales();
		System.out.println(Arrays.asList(locales));
	}

	// 旧API的多线程安全问题
	@Test
	public void test7() throws InterruptedException {
		Runnable r = new DateTimeOldProblem("2017-02-12");
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();

		Thread.sleep(2000);
	}

	/*
	 * 旧API的多线程安全问题解决： 1、每次使用新的实例 2、加锁 3、使用ThreadLocal 4、使用Java8新API 5、使用Joda-Time
	 * 
	 */
	@Test
	public void test8() throws InterruptedException {
		Runnable r = new DateTimeOldSolve("2017-02-12");
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();

		Thread.sleep(2000);
	}

}
