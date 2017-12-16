package com.chain.javase.test.day05;

import java.sql.Timestamp;
import java.text.Format;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

/**
 * Java新旧时间日期的转换
 * 
 * @author Chain
 *
 */
public class DateTimeNewOldTest {

	// Date-Instant
	@Test
	public void test1() {
		// 距离Unix元年的时间
		Instant instant = Instant.now();
		// System.out.println(instant.toEpochMilli());

		// 系统时间
		java.util.Date date = Date.from(instant);
		// System.out.println(date.getTime());

		System.out.println(instant);
		System.out.println(date);

		// -------------------------------

		Date date2 = new Date();
		Instant instant2 = Instant.ofEpochMilli(date2.getTime());
		System.out.println(date2);
		System.out.println(instant2);
	}

	// Calendar-ZonedDateTime
	@Test
	public void test2() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		Instant instant = Instant.now();

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(instant.toEpochMilli()));
		calendar.setTimeZone(TimeZone.getTimeZone(zonedDateTime.getZone()));

		System.out.println(zonedDateTime);
		System.out.println(calendar);

		// System.out.println(calendar.getTime());
		// System.out.println(calendar.getTimeZone().getID());

		// ----------------------------------

		Calendar calendar2 = new GregorianCalendar();
		Instant instant2 = calendar2.toInstant();
		ZonedDateTime zonedDateTime2 = ZonedDateTime.ofInstant(instant2, ZoneId.of("Asia/Shanghai"));
		System.out.println(calendar2);
		System.out.println(zonedDateTime2);
	}

	// Date-localDateTime
	@Test
	public void test3() {
		// 转为本地时间
		Date date = new Date();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),
				ZoneId.of("Asia/Shanghai"));
		LocalDateTime localDateTime2 = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Shanghai"));

		System.out.println(date);
		System.out.println(localDateTime);
		System.out.println(localDateTime2);

		// --------------------------------------

		LocalDateTime localDateTime3 = LocalDateTime.now();
		Instant instant = localDateTime3.toInstant(ZoneOffset.ofHours(8));
		Date date2 = Date.from(instant);

		System.out.println(localDateTime3);
		System.out.println(date2);
	}

	// java.sql.Date-LocalDate
	@Test
	public void test4() {
		LocalDate localDate = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
		System.out.println(localDate);
		System.out.println(sqlDate);

		// --------------------------------------

		java.sql.Date sqlDate2 = new java.sql.Date(new Date().getTime());
		LocalDate localDate2 = sqlDate2.toLocalDate();

		System.out.println(sqlDate2);
		System.out.println(localDate2);
	}

	// java.sql.Time-LocalTime
	@Test
	public void test5() {
		LocalTime localTime = LocalTime.now();
		java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
		System.out.println(localTime);
		System.out.println(sqlTime);

		// ------------------------------------

		java.sql.Time sqlTime2 = new java.sql.Time(new Date().getTime());
		LocalTime localTime2 = sqlTime2.toLocalTime();

		System.out.println(sqlTime2);
		System.out.println(localTime2);
	}

	// java.sql.Timestamp-LocalDateTime/Instant
	@Test
	public void test6() {
		LocalDateTime localDateTime = LocalDateTime.now();
		java.sql.Timestamp sqlTimestamp1 = java.sql.Timestamp.valueOf(localDateTime);

		Instant instant = Instant.now();
		java.sql.Timestamp sqlTimestamp2 = java.sql.Timestamp.from(instant);

		System.out.println(localDateTime);
		System.out.println(sqlTimestamp1);
		System.out.println(sqlTimestamp2);

		// ------------------------------------------------

		java.sql.Timestamp sqlTimestamp3 = new Timestamp(new Date().getTime());
		LocalDateTime localDateTime2 = sqlTimestamp3.toLocalDateTime();

		System.out.println(sqlTimestamp3);
		System.out.println(localDateTime2);
	}

	// TimeZone-ZoneId
	@Test
	public void test7() {
		ZoneId zoneId1 = ZoneId.systemDefault();
		TimeZone timeZone1 = TimeZone.getTimeZone(zoneId1);
		System.out.println(zoneId1);
		System.out.println(timeZone1);

		// -----------------------------------------------------

		TimeZone timeZone2 = TimeZone.getDefault();
		ZoneId zoneId2 = timeZone2.toZoneId();
		System.out.println(timeZone2);
		System.out.println(zoneId2);
	}

	// Format-DateTimeFormatter
	@Test
	public void test8() {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		Format f1 = dtf.toFormat();

		// 没有从Format到DateTimeFormatter的方法
	}

}
