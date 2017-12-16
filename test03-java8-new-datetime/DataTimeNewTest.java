package com.chain.javase.test.day05;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

import org.junit.Test;

/**
 * Java8:java.time.*
 * 新的的时间日期：Instant,LocalDateTime,LocalDate,LocalTime,ZonedDateTime,ZoneId,ZoneOffset,OffsetDateTime,DateTimeFormatter,TemporalAdjuster，Duration，Period等
 * 
 * 每次都返回新的对象，多线程安全的
 * 
 * 基于ISO-8601标准
 * 
 * @author Chain
 *
 */
public class DataTimeNewTest {

	// Instant：时间戳,距离Unix元年的时间，与区域无关，即默认区域为0(Z)
	@Test
	public void test1() {
		Instant instant = Instant.now();
		System.out.println(instant.getEpochSecond());
		System.out.println(instant.getNano());
		System.out.println(instant);

		// 每次返回新的对象
		OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
		System.out.println(offsetDateTime);
	}

	// LocalDateTime,LocalDate,LocalTime：默认时区为系统时区
	@Test
	public void test2() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);

		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);

		LocalDateTime localDateTime2 = LocalDateTime.of(2017, 2, 12, 12, 14, 43);
		System.out.println(localDateTime2);

		LocalDateTime localDateTime3 = localDateTime2.plusYears(2);
		System.out.println(localDateTime3);

		System.out.println(localDateTime3.getYear());
		System.out.println(localDateTime3.getDayOfYear());
	}

	// Duration：计算两个时间的间隔，Period：计算两个日期的间隔
	@Test
	public void test3() throws InterruptedException {
		Instant instant1 = Instant.now();
		Thread.sleep(1000);
		Instant instant2 = Instant.now();
		System.out.println(Duration.between(instant1, instant2).getSeconds());

		LocalTime localTime1 = LocalTime.now();
		LocalTime localTime2 = LocalTime.of(12, 12);
		System.out.println(Duration.between(localTime1, localTime2).getSeconds());

		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2011, 12, 21);
		System.out.println(Period.between(localDate1, localDate2).getYears());
	}

	// TemporalAdjuster : 时间校正器
	@Test
	public void test4() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		LocalDateTime localDateTime2 = localDateTime.plusSeconds(20);
		System.out.println(localDateTime2);

		LocalDateTime localDateTime3 = localDateTime.with(DayOfWeek.SATURDAY);
		System.out.println(localDateTime3);

		// 找出下一个工作日的时间
		TemporalAdjuster adjuster = new TemporalAdjuster() {

			@Override
			public Temporal adjustInto(Temporal temporal) {
				LocalDateTime localDateTime = (LocalDateTime) temporal;
				if (localDateTime.getDayOfWeek() == DayOfWeek.FRIDAY)
					return localDateTime.plusDays(3);
				else if (localDateTime.getDayOfWeek() == DayOfWeek.SATURDAY)
					return localDateTime.plusDays(2);
				else if (localDateTime.getDayOfWeek() == DayOfWeek.SUNDAY)
					return localDateTime.plusDays(1);
				else
					return localDateTime;
			}
		};

		LocalDateTime localDateTime4 = localDateTime.with(adjuster);
		System.out.println(localDateTime4);
	}

	// DateTimeFormatter : 解析和格式化日期或时间
	@Test
	public void test5() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

		DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(dateTimeFormatter.format(localDateTime));
		System.out.println(dateTimeFormatter2.format(localDateTime));

		String time = "2017-07-31T11:46:41.353";
		LocalDateTime localDateTime2 = LocalDateTime.parse(time, dateTimeFormatter);
		System.out.println(localDateTime2);
		String time2 = "2017年07月31日 11:49:17";
		LocalDateTime localDateTime3 = LocalDateTime.parse(time2, dateTimeFormatter2);
		System.out.println(localDateTime3);
	}

	// ZoneId,ZonedDateTime,ZoneOffset：和时区相关
	@Test
	public void test6() {
		System.out.println(Arrays.asList(ZoneId.getAvailableZoneIds()));

		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println(zonedDateTime);

		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zonedDateTime2 = ZonedDateTime.ofStrict(localDateTime, ZoneOffset.ofHours(-8),
				ZoneId.of("Aisa/Shanghai"));

		System.out.println(zonedDateTime2);
	}

}
