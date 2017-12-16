package com.chain.javase.test.day02;

import java.lang.reflect.Method;

import org.junit.Test;

public class ScaleConvertUtilsTest {

	// toFormatString测试
	@Test
	public void test1() throws Exception {
		// DWORD
		int a1 = 100;
		int a2 = -100;
		int a3 = Integer.MAX_VALUE;
		int a4 = Integer.MIN_VALUE;
		// BYTE
		byte b1 = 80;
		byte b2 = -80;
		byte b3 = Byte.MAX_VALUE;
		byte b4 = Byte.MIN_VALUE;
		// WORD
		short c1 = 12345;
		short c2 = -12345;
		short c3 = Short.MAX_VALUE;
		short c4 = Short.MIN_VALUE;
		// QWORD
		long d1 = 1234567890;
		long d2 = -1234567890;
		long d3 = Long.MAX_VALUE;
		long d4 = Long.MIN_VALUE;

		// int
		System.out.println(ScaleConvertUtils.toFormatString(a1, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(a1, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(a1, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(a2, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(a2, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(a2, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(a3, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(a3, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(a3, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(a4, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(a4, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(a4, ScaleConvertUtils.HEX));
		System.out.println();

		// byte
		System.out.println(ScaleConvertUtils.toFormatString(b1, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(b1, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(b1, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(b2, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(b2, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(b2, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(b3, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(b3, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(b3, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(b4, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(b4, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(b4, ScaleConvertUtils.HEX));
		System.out.println();

		// short
		System.out.println(ScaleConvertUtils.toFormatString(c1, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(c1, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(c1, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(c2, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(c2, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(c2, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(c3, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(c3, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(c3, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(c4, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(c4, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(c4, ScaleConvertUtils.HEX));
		System.out.println();

		// long
		System.out.println(ScaleConvertUtils.toFormatString(d1, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(d1, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(d1, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(d2, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(d2, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(d2, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(d3, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(d3, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(d3, ScaleConvertUtils.HEX));
		System.out.println();

		System.out.println(ScaleConvertUtils.toFormatString(d4, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(d4, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(d4, ScaleConvertUtils.HEX));
		System.out.println();

		// 0
		System.out.println(ScaleConvertUtils.toFormatString(0, ScaleConvertUtils.BIN));
		System.out.println(ScaleConvertUtils.toFormatString(0, ScaleConvertUtils.OCT));
		System.out.println(ScaleConvertUtils.toFormatString(0, ScaleConvertUtils.HEX));
		System.out.println();

		// 任意进制测试
		System.out.println(ScaleConvertUtils.toFormatString(0, 20));
		System.out.println(ScaleConvertUtils.toFormatString(12, 20));
		System.out.println(ScaleConvertUtils.toFormatString(120, 20));
		System.out.println(ScaleConvertUtils.toFormatString(12, 3));
		System.out.println(ScaleConvertUtils.toFormatString(120, 3));
		System.out.println(ScaleConvertUtils.toFormatString(12, ScaleConvertUtils.DEC));
		System.out.println(ScaleConvertUtils.toFormatString(120, ScaleConvertUtils.DEC));
		System.out.println(ScaleConvertUtils.toFormatString(-12, ScaleConvertUtils.DEC));
		System.out.println(ScaleConvertUtils.toFormatString(-120, ScaleConvertUtils.DEC));
		System.out.println(ScaleConvertUtils.toFormatString(12, 7));
		System.out.println(ScaleConvertUtils.toFormatString(120, 7));
		System.out.println(ScaleConvertUtils.toFormatString(-12, 7));
		System.out.println(ScaleConvertUtils.toFormatString(-120, 7));
		System.out.println();

		// 单独测试
		Method m1 = ReflectionUtils.getDeclaredMethod(ScaleConvertUtils.class, "divMethod", Object.class, int.class);
		Method m2 = ReflectionUtils.getDeclaredMethod(ScaleConvertUtils.class, "formatNumberString", StringBuffer.class,
				int.class);
		// 输出结果需要reverse
		// -12
		StringBuffer sb1 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(-12), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb1,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb2 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(-12), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb2,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb3 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(-12), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb3,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// -120
		StringBuffer sb4 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(-120), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb4,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb5 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(-120), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb5,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb6 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(-120), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb6,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// 120
		StringBuffer sb7 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(120), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb7,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb8 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(120), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb8,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb9 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Integer(120), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb9,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// 12B
		StringBuffer sb10 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Byte((byte) 12), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb10,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb11 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Byte((byte) 12), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb11,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb12 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Byte((byte) 12), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb12,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// -12B
		StringBuffer sb13 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Byte((byte) -12), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb13,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb14 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Byte((byte) -12), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb14,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb15 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Byte((byte) -12), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb15,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// 120S
		StringBuffer sb16 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Short((short) 120), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb16,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb17 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Short((short) 120), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb17,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb18 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Short((short) 120), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb18,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// -120S
		StringBuffer sb19 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Short((short) -120), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb19,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb20 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Short((short) -120), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb20,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb21 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Short((short) -120), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb21,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// 1234567890L
		StringBuffer sb22 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(1234567890L), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb22,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb23 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(1234567890L), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb23,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb24 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(1234567890L), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb24,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();
		// -1234567890L
		StringBuffer sb25 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(-1234567890L), ScaleConvertUtils.BIN);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb25,
				ScaleConvertUtils.BIN)).reverse());
		StringBuffer sb26 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(-1234567890L), ScaleConvertUtils.OCT);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb26,
				ScaleConvertUtils.OCT)).reverse());
		StringBuffer sb27 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(-1234567890L), ScaleConvertUtils.HEX);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb27,
				ScaleConvertUtils.HEX)).reverse());
		System.out.println();

		// 十进制测试
		// 1234567890L
		StringBuffer sb28 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(1234567890L), ScaleConvertUtils.DEC);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb28,
				ScaleConvertUtils.DEC)).reverse());
		System.out.println();
		// -1234567890L
		StringBuffer sb29 = (StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m1,
				new Long(-1234567890L), ScaleConvertUtils.DEC);
		System.out.println(((StringBuffer) ReflectionUtils.invokeMethod(ScaleConvertUtils.getInstance(), m2, sb29,
				ScaleConvertUtils.DEC)).reverse());
		System.out.println();

	}

	// parseString测试
	@Test
	public void test2() throws Exception {
		byte a1 = ScaleConvertUtils.parseString("+123456   7890", ScaleConvertUtils.DEC, Byte.class);
		System.out.println(a1);
		short a2 = ScaleConvertUtils.parseString("0123 4567890", ScaleConvertUtils.DEC, Short.class);
		System.out.println(a2);
		int a3 = ScaleConvertUtils.parseString("-1234567890", ScaleConvertUtils.DEC, Integer.class);
		System.out.println(a3);
		long a4 = ScaleConvertUtils.parseString("1234567890", ScaleConvertUtils.DEC, Long.class);
		System.out.println(a4);
		long a5 = ScaleConvertUtils.parseString("0100 1001 1001 0110 0000 0010 1101 0010", ScaleConvertUtils.BIN,
				Long.class);
		System.out.println(a5);
		long a6 = ScaleConvertUtils.parseString("49 96 02d2", ScaleConvertUtils.HEX, Long.class);
		System.out.println(a6);

		// long a7 = ScaleConvertUtils.parseString("1234567汉89", ScaleConvertUtils.DEC,
		// Long.class);
		// long a8 = ScaleConvertUtils.parseString("", ScaleConvertUtils.DEC,
		// Long.class);
	}

	// 交叉测试(互逆测试)
	@Test
	public void test3() throws Exception {
		int a = 1234567890;
		System.out.println(a);
		System.out.println();

		String s1 = ScaleConvertUtils.toBinaryString(a, true);
		System.out.println(s1);
		String s2 = ScaleConvertUtils.toBinaryString(a);
		System.out.println(s2);

		String s3 = ScaleConvertUtils.toOctalString(a, true);
		System.out.println(s3);
		String s4 = ScaleConvertUtils.toOctalString(a);
		System.out.println(s4);

		String s5 = ScaleConvertUtils.toDecimalString(a, true);
		System.out.println(s5);
		String s6 = ScaleConvertUtils.toDecimalString(a);
		System.out.println(s6);

		String s7 = ScaleConvertUtils.toHexString(a, true);
		System.out.println(s7);
		String s8 = ScaleConvertUtils.toHexString(a);
		System.out.println(s8);

		System.out.println();

		int b = 0;
		b = ScaleConvertUtils.parseBinaryString(s1);
		System.out.println(b);
		b = ScaleConvertUtils.parseBinaryString(s2);
		System.out.println(b);
		b = ScaleConvertUtils.parseOctalString(s3);
		System.out.println(b);
		b = ScaleConvertUtils.parseOctalString(s4);
		System.out.println(b);
		b = ScaleConvertUtils.parseDecimalString(s5);
		System.out.println(b);
		b = ScaleConvertUtils.parseDecimalString(s6);
		System.out.println(b);
		b = ScaleConvertUtils.parseHexString(s7);
		System.out.println(b);
		b = ScaleConvertUtils.parseHexString(s8);
		System.out.println(b);

	}

	// 交叉测试(互逆测试)
	@Test
	public void test4() throws Exception {
		int a = -1234567890;
		System.out.println(a);
		System.out.println();

		String s1 = ScaleConvertUtils.toBinaryString(a, true);
		System.out.println(s1);
		String s2 = ScaleConvertUtils.toBinaryString(a);
		System.out.println(s2);

		String s3 = ScaleConvertUtils.toOctalString(a, true);
		System.out.println(s3);
		String s4 = ScaleConvertUtils.toOctalString(a);
		System.out.println(s4);

		String s5 = ScaleConvertUtils.toDecimalString(a, true);
		System.out.println(s5);
		String s6 = ScaleConvertUtils.toDecimalString(a);
		System.out.println(s6);

		String s7 = ScaleConvertUtils.toHexString(a, true);
		System.out.println(s7);
		String s8 = ScaleConvertUtils.toHexString(a);
		System.out.println(s8);

		System.out.println();

		int b = 0;
		b = ScaleConvertUtils.parseBinaryString(s1);
		System.out.println(b);
		b = ScaleConvertUtils.parseBinaryString(s2);
		System.out.println(b);
		b = ScaleConvertUtils.parseOctalString(s3);
		System.out.println(b);
		b = ScaleConvertUtils.parseOctalString(s4);
		System.out.println(b);
		b = ScaleConvertUtils.parseDecimalString(s5);
		System.out.println(b);
		b = ScaleConvertUtils.parseDecimalString(s6);
		System.out.println(b);
		b = ScaleConvertUtils.parseHexString(s7);
		System.out.println(b);
		b = ScaleConvertUtils.parseHexString(s8);
		System.out.println(b);

	}

	// 交叉测试(互逆测试)
	@Test
	public void test5() throws Exception {
		int a = -1234567890;
		System.out.println(a);
		System.out.println();

		String s1 = ScaleConvertUtils.toString(a, 7);
		System.out.println(s1);
		String s2 = ScaleConvertUtils.toFormatString(a, 7);
		System.out.println(s2);

		String s3 = ScaleConvertUtils.toString(a, 20);
		System.out.println(s3);
		String s4 = ScaleConvertUtils.toFormatString(a, 20);
		System.out.println(s4);

		String s5 = ScaleConvertUtils.toString(a, 14);
		System.out.println(s5);
		String s6 = ScaleConvertUtils.toFormatString(a, 14);
		System.out.println(s6);

		String s7 = ScaleConvertUtils.toString(a, ScaleConvertUtils.MAX);
		System.out.println(s7);
		String s8 = ScaleConvertUtils.toFormatString(a, ScaleConvertUtils.MAX);
		System.out.println(s8);

		System.out.println();

		int b = 0;
		b = ScaleConvertUtils.parseString(s1, 7, Integer.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s2, 7, Integer.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s3, 20, Integer.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s4, 20, Integer.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s5, 14, Integer.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s6, 14, Integer.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s7, ScaleConvertUtils.MAX, Integer.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s8, ScaleConvertUtils.MAX, Integer.class);
		System.out.println(b);
	}

	// 交叉测试(互逆测试)：测试结果不正确
	@Test
	public void test6() throws Exception {
		long a = -9223372036854775807L;
		System.out.println(a);
		System.out.println();

		String s1 = ScaleConvertUtils.toString(a, 7);
		System.out.println(s1);
		String s2 = ScaleConvertUtils.toFormatString(a, 7);
		System.out.println(s2);

		String s3 = ScaleConvertUtils.toString(a, 20);
		System.out.println(s3);
		String s4 = ScaleConvertUtils.toFormatString(a, 32);
		System.out.println(s4);

		String s5 = ScaleConvertUtils.toString(a, 14);
		System.out.println(s5);
		String s6 = ScaleConvertUtils.toFormatString(a, 14);
		System.out.println(s6);

		String s7 = ScaleConvertUtils.toString(a, ScaleConvertUtils.MAX);
		System.out.println(s7);
		String s8 = ScaleConvertUtils.toFormatString(a, ScaleConvertUtils.MAX);
		System.out.println(s8);

		String s9 = ScaleConvertUtils.toString(a, ScaleConvertUtils.BIN);
		System.out.println(s9);
		String s10 = ScaleConvertUtils.toFormatString(a, ScaleConvertUtils.BIN);
		System.out.println(s10);

		String s11 = ScaleConvertUtils.toString(a, ScaleConvertUtils.OCT);
		System.out.println(s11);
		String s12 = ScaleConvertUtils.toFormatString(a, ScaleConvertUtils.OCT);
		System.out.println(s12);

		String s13 = ScaleConvertUtils.toString(a, ScaleConvertUtils.HEX);
		System.out.println(s13);
		String s14 = ScaleConvertUtils.toFormatString(a, ScaleConvertUtils.HEX);
		System.out.println(s14);

		System.out.println();

		long b = 0;
		b = ScaleConvertUtils.parseString(s1, 7, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s2, 7, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s3, 20, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s4, 32, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s5, 14, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s6, 14, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s7, ScaleConvertUtils.MAX, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s8, ScaleConvertUtils.MAX, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s9, ScaleConvertUtils.BIN, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s10, ScaleConvertUtils.BIN, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s11, ScaleConvertUtils.OCT, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s12, ScaleConvertUtils.OCT, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s13, ScaleConvertUtils.HEX, Long.class);
		System.out.println(b);
		b = ScaleConvertUtils.parseString(s14, ScaleConvertUtils.HEX, Long.class);
		System.out.println(b);
	}

	// 浮点数测试(互逆测试)
	@Test
	public void test7() throws Exception {
		double a = 32.75;
		System.out.println(a);
		String s1 = ScaleConvertUtils.toFormatString(a, ScaleConvertUtils.BIN);
		System.out.println(s1);

		float b = 8.125f;
		System.out.println(b);
		String s2 = ScaleConvertUtils.toFormatString(b, ScaleConvertUtils.BIN);
		System.out.println(s2);

		double c = -32.75;
		System.out.println(c);
		String s3 = ScaleConvertUtils.toFormatString(c, ScaleConvertUtils.OCT);
		System.out.println(s3);

		float d = 0.45f;
		System.out.println(d);
		String s4 = ScaleConvertUtils.toFormatString(d, ScaleConvertUtils.HEX);
		System.out.println(s4);

		float e = 32.75f;
		System.out.println(e);
		String s5 = ScaleConvertUtils.toFormatString(e, ScaleConvertUtils.DEC);
		System.out.println(s5);

		double a1 = ScaleConvertUtils.parseBinaryStringToDouble(s1);
		System.out.println(a1);

		float b1 = ScaleConvertUtils.parseString(s2, ScaleConvertUtils.BIN, Float.class);
		System.out.println(b1);

		double c1 = ScaleConvertUtils.parseString(s3, ScaleConvertUtils.OCT, Double.class);
		System.out.println(c1);

		float d1 = ScaleConvertUtils.parseString(s4, ScaleConvertUtils.HEX, Float.class);
		System.out.println(d1);

		float e1 = ScaleConvertUtils.parseDecimalStringToFloat(s4);
		System.out.println(e1);

	}

	// 特殊测试
	@Test
	public void test8() throws Exception {
		int a = 0;
		System.out.println(a);
		System.out.println();

		String s1 = ScaleConvertUtils.toBinaryString(a, true);
		System.out.println(s1);
		String s2 = ScaleConvertUtils.toBinaryString(a);
		System.out.println(s2);

		String s3 = ScaleConvertUtils.toOctalString(a, true);
		System.out.println(s3);
		String s4 = ScaleConvertUtils.toOctalString(a);
		System.out.println(s4);

		String s5 = ScaleConvertUtils.toDecimalString(a, true);
		System.out.println(s5);
		String s6 = ScaleConvertUtils.toDecimalString(a);
		System.out.println(s6);

		String s7 = ScaleConvertUtils.toHexString(a, true);
		System.out.println(s7);
		String s8 = ScaleConvertUtils.toHexString(a);
		System.out.println(s8);

		System.out.println();

		int b = 0;
		b = ScaleConvertUtils.parseBinaryString(s1);
		System.out.println(b);
		b = ScaleConvertUtils.parseBinaryString(s2);
		System.out.println(b);
		b = ScaleConvertUtils.parseOctalString(s3);
		System.out.println(b);
		b = ScaleConvertUtils.parseOctalString(s4);
		System.out.println(b);
		b = ScaleConvertUtils.parseDecimalString(s5);
		System.out.println(b);
		b = ScaleConvertUtils.parseDecimalString(s6);
		System.out.println(b);
		b = ScaleConvertUtils.parseHexString(s7);
		System.out.println(b);
		b = ScaleConvertUtils.parseHexString(s8);
		System.out.println(b);
	}

	// 特殊测试
	@Test
	public void test9() throws Exception {
		int a = 0;
		a = ScaleConvertUtils.parseBinaryString("10010");
		System.out.println(a);
		a = ScaleConvertUtils.parseBinaryString("-10010");
		System.out.println(a);
		a = ScaleConvertUtils.parseBinaryString("1111 1111 1111 1111 1111 1111 1111 0010");
		System.out.println(a);

		a = ScaleConvertUtils.parseBinaryString("-101110");
		System.out.println(a);
		a = ScaleConvertUtils.parseBinaryString("‭‭‭11111111111111111111111111101110‬");
		System.out.println(a);
		a = ScaleConvertUtils.parseHexString("FFFFFFEE");
		System.out.println(a);
		a = ScaleConvertUtils.parseOctalString("‭37777777756");
		System.out.println(a);
		a = ScaleConvertUtils.parseDecimalString("‭-18");
		System.out.println(a);

		a = ScaleConvertUtils.parseHexString("F12E");
		System.out.println(a);
		a = ScaleConvertUtils.parseHexString("-F12E");
		System.out.println(a);
	}
}
