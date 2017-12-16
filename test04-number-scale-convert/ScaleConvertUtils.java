package com.chain.javase.test.day02;

import java.math.BigInteger;

/**
 * 进制转换工具类<br/>
 * 
 * 支持：<br/>
 * 
 * 1、十进制byte,short,int,long,float,double转为二进制、八进制、十六进制字符串。<br/>
 * 
 * 2*、十进制byte,short,int,long,float,double转为任意进制(最小二进制，且不大于三十六进制)。<br/>
 * 
 * 2、二进制、八进制、十六进制字符串转十进制byte,short,int,long,float,double。<br/>
 * 
 * 3*、任意进制(最小二进制，且不大于三十六进制)字符串转十进制byte,short,int,long,float,double。<br/>
 * 
 * <i><b>*：带星号的为实验性功能，结果不一定准确。</b></i><br/>
 * 
 * 注意：<br/>
 * 
 * 1)、浮点数存在精度误差。<br/>
 * 
 * 2)、进制数过大可能造成转换成十进制的结果会有溢出。<br/>
 * 
 * 
 * @author Chain Qian
 * @version 20170722
 *
 */
public class ScaleConvertUtils {

	private static Class<ScaleConvertUtils> clz = ScaleConvertUtils.class;

	private ScaleConvertUtils() {

	}

	private static char[] data = null;

	public static final int BIN = 2;
	public static final int OCT = 8;
	public static final int DEC = 10;
	public static final int HEX = 16;

	public static final int MIN = BIN;
	public static final int MAX = 36;

	static {
		// 生成映射表
		data = new char[MAX];
		for (int i = 0; i < DEC; i++)
			data[i] = (char) (i + 48);
		for (int i = 0; i < 26; i++)
			data[i + 10] = (char) (i + 65);
	}

	/**
	 * 根据传入的scale，尝试将字符串转为指定类型（&lt;T extends Number&gt;）<br/>
	 * 
	 * <i>实验性功能，结果不一定准确</i><br/>
	 * 
	 * <b>注意：字符串的符号位非负数用 "+"/"0"/缺省 表示，负数只可以用 "-" 表示，如果想表示负数，需要在字符串头加上
	 * "-"，否则结果不一定正确，输入的负数如果是十进制，则输入的字符串为原来的十进制数，如果是其他进制则为其补码形式</b><br/>
	 * 
	 * 比如可以这样使用：<br/>
	 * 
	 * parseString("1234567890", ScaleConvertUtils.DEC, Integer.class)<br/>
	 * parseString("123", ScaleConvertUtils.DEC, Byte.class)<br/>
	 * parseString("+1234", ScaleConvertUtils.DEC, Short.class)<br/>
	 * parseString("-1234", ScaleConvertUtils.DEC, Integer.class)<br/>
	 * 
	 * 输入的是十进制数18<br/>
	 * parseString("10010", ScaleConvertUtils.BIN, Integer.class)<br/>
	 * 输入的是十进制数-14，相当于‭1111 ‭1111 1111 ‭1111 1111 ‭1111 ‭1111 0010‬<br/>
	 * parseString("-10010", ScaleConvertUtils.BIN, Integer.class)<br/>
	 * 输入的是十进制数18<br/>
	 * parseString("010010", ScaleConvertUtils.BIN, Integer.class)<br/>
	 * 输入的是十进制数18<br>
	 * parseString("+10010", ScaleConvertUtils.BIN, Integer.class)<br/>
	 * 
	 * 输入的是十进制数‭‭61742‬<br/>
	 * parseString("F12E", ScaleConvertUtils.HEX, Integer.class)<br/>
	 * 输入的是十进制数‭‭‭-3794‬，相当于FFFF F12E‬<br/>
	 * parseString("-F12E", ScaleConvertUtils.HEX, Integer.class)<br/>
	 * 
	 * @param src
	 * @param scale
	 * @param dst
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseString(String src, int scale, Class<T> dst) throws Exception {
		src = formatSrcString(src);
		checkSrcString(src);
		// System.out.println("format: " + src);
		ScaleConvertUtils utils = getInstance();
		if (dst == Byte.class || dst == Short.class || dst == Integer.class || dst == Long.class) {
			long result = utils.parseStringToLong(src, scale);
			// 下面的操作可能会导致转换结果不准确
			if (dst == Byte.class)
				return (T) new Byte((byte) result);
			else if (dst == Short.class)
				return (T) new Short((short) result);
			else if (dst == Integer.class)
				return (T) new Integer((int) result);
			else if (dst == Long.class)
				return (T) new Long(result);
		} else if (dst == Float.class || dst == Double.class) {
			// TODO 浮点数尚未支持
			if (dst == Float.class) {
				int lval = parseString(src, scale, Integer.class);
				float fval = Float.intBitsToFloat(lval);
				return (T) new Float(fval);
			} else {
				long lval = parseString(src, scale, Long.class);
				double dval = Double.longBitsToDouble(lval);
				return (T) new Double(dval);
			}
		}
		return null;
	}

	/**
	 * 根据scale将字符串转为十进制的long型整数<br/>
	 * 
	 * 会产生溢出，结果可能不正确
	 * 
	 * @param src
	 * @param scale
	 * @return
	 */
	private long parseStringToLong(String src, final int scale) {
		int len = src.length();
		if (len == 0)
			return 0;
		if (src.charAt(0) == '-') {
			// TODO 负数支持的尚不完善，仅支持长度刚好等于BYTE，WORD，DWORD，QWORD的字符串
			if (scale == DEC)
				return new Long(src);
			else {
				// 先将整数部分转为二进制字符串
				long unsignedLong = parseStringToLong(src.substring(1), scale);
				String unsignedBinaryString = toBinaryString(unsignedLong);
				// 将二进制字符串填1成QWORD的长度
				StringBuffer sb = new StringBuffer(unsignedBinaryString);
				sb.reverse();
				int length = sb.length();
				int qword = 64;
				for (int i = 0; i < qword - length; i++)
					sb.append(1);
				sb.reverse();
				// 对填满后的字符串转成long
				return parseStringToLong(sb.toString(), BIN);
			}
		}
		char[] chs = src.toCharArray();
		BigInteger result = BigInteger.valueOf(0);
		BigInteger pow = BigInteger.valueOf(1);
		BigInteger bscale = BigInteger.valueOf(scale);
		for (int i = len - 1; i > -1; i--) {
			int t = 0;
			if (chs[i] > 47 && chs[i] < 58)
				t = chs[i] - 48;
			else if (chs[i] > 64 && chs[i] < 91)
				t = chs[i] - 65 + 10;
			result = result.add(pow.multiply(BigInteger.valueOf(t)));
			pow = pow.multiply(bscale);
		}
		return result.longValue();
	}

	/**
	 * 格式化字符串，使其转为(-)[0-9A-Z]
	 * 
	 * @param src
	 * @return
	 */
	private static String formatSrcString(String src) {
		src = src.toUpperCase();
		String reg = "[^0-9A-Za-z\\-]";
		src = src.replaceAll(reg, "");
		reg = "^0+";
		src = src.replaceAll(reg, "");
		reg = "^[\\-]+";
		if (src.matches(reg)) {
			src = src.replaceAll(reg, "");
			src = "-" + src;
		}
		return src;
	}

	/**
	 * 检查字符串是否合法，即^[\\-][0-9A-Z]+，且不能为空字符串
	 * 
	 * @param src
	 */
	private static void checkSrcString(String src) {
		// 正则特殊符号：^ $ . * +  - ? = ! : | \ / ( ) [ ] { }
		// 注意负号、点号都需要使用双个斜杠转换，单引号单个斜杠转换
		String reg = "^[\\-]{0,1}[0-9A-Z]*";
		if (!src.matches(reg))
			throw new RuntimeException("format of this string is not correct");
	}

	/**
	 * 格式化字符串（添加空格，参考Windows计算器格式），返回的StringBuffer也是传入的StringBuffer<br/>
	 * 
	 * @param sb
	 * @param scale
	 * @return
	 */
	private static StringBuffer formatNumberString(StringBuffer sb, int scale) {
		// 八进制是三个为一段，其余默认为四个一段
		int gap = 4;
		if (scale == OCT)
			gap = 3;
		int next = gap;
		int i = 1;
		for (; i < sb.length(); i++) {
			if (i == next) {
				sb.insert(i, " ");
				i++;
				next += gap + 1;
			}
		}
		// 二进制再在头部添加零，保证每一段都是四个，但是如果原十进制数是0，那么也输出0
		if (scale == BIN) {
			if (!(sb.length() == 1 && (sb.charAt(0) == '0' || sb.charAt(0) == 'N')))
				for (; i < next; i++)
					sb.append(0);
		}
		return sb;
	}

	/**
	 * 返回格式化的字符串（加上空格，参考Windows计算器格式）<br/>
	 * 
	 * @param obj
	 * @param scale
	 * @return
	 */
	public static String toFormatString(Object obj, int scale) {
		StringBuffer sb = toUnFormatString(obj, scale);
		if (obj instanceof Byte || obj instanceof Short || obj instanceof Integer || obj instanceof Long) {
			formatNumberString(sb, scale);
		} else if (obj instanceof Float || obj instanceof Double) {
			if (scale == BIN) {
				if (obj instanceof Float)
					formatFloatNumberString(sb, 0);
				else if (obj instanceof Double)
					formatFloatNumberString(sb, 1);
			} else {
				formatNumberString(sb, scale);
			}
		}
		return sb.reverse().toString();
	}

	/**
	 * 格式化二进制表示的Float和Double
	 * 
	 * @param sb
	 * @param i
	 */
	private static void formatFloatNumberString(StringBuffer sb, int type) {
		// 0 float 1+8+23
		// 1 double 1+11+52
		if (type == 0) {
			if (sb.length() == 31)
				sb.append(0);
			sb.insert(23, " ");
			sb.insert(23 + 8 + 1, " ");
		} else if (type == 1) {
			if (sb.length() == 63)
				sb.append(0);
			sb.insert(52, " ");
			sb.insert(52 + 11 + 1, " ");
		}
	}

	/**
	 * 返回转成其他进制的字符串（不带空格）
	 * 
	 * @param obj
	 * @param scale
	 * @return
	 */
	public static String toString(Object obj, int scale) {
		return toUnFormatString(obj, scale).reverse().toString();
	}

	/**
	 * 返回转成其他进制的字符串（不带空格），内部方法
	 * 
	 * @param obj
	 * @param scale
	 * @return
	 */
	private static StringBuffer toUnFormatString(Object obj, int scale) {
		checkScale(scale);
		ScaleConvertUtils utils = getInstance();
		if (isFloatPointNumber(obj)) {
			return utils.floatPointNumberMethod(obj, scale);
		} else {
			if (isPowerOfTwo(scale))
				return utils.shiftMethod(obj, scale);
			else
				return utils.divMethod(obj, scale);
		}
	}

	private StringBuffer floatPointNumberMethod(Object obj, int scale) {
		// TODO 浮点数暂不支持
		StringBuffer sb = new StringBuffer();
		if (scale == DEC)
			sb.append(obj).reverse();
		else if (obj instanceof Float) {
			float value = (Float) obj;
			int ibits = Float.floatToIntBits(value);
			try {
				String sval = toBinaryString(ibits);
				int ival = parseBinaryString(sval);
				String s = toString(ival, scale);
				sb.append(s).reverse();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Double) {
			double value = (Double) obj;
			long lbits = Double.doubleToLongBits(value);
			try {
				String sval = toBinaryString(lbits);
				long ival = parseBinaryStringToLong(sval);
				String s = toString(ival, scale);
				sb.append(s).reverse();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb;
	}

	/**
	 * 整数通过<b>连除法</b>转成其他进制的字符串形式<br/>
	 * 
	 * 实验性功能，结果不一定准确<br/>
	 * 
	 * @param obj
	 * @param scale
	 * @return
	 */
	private StringBuffer divMethod(Object obj, int scale) {
		StringBuffer sb = new StringBuffer();
		if (scale == DEC) {
			sb.append(obj).reverse();
		} else if (obj instanceof Byte) {
			byte value = (Byte) obj;
			if (value < 0) {
				// 转为无符号数，因为byte会自动转型为int，所以还需要强转
				short value2 = (short) (value & 0x7f | 0x80);
				return divMethod(value2, scale);
			} else if (value == 0)
				sb.append(0);
			while (value > 0) {
				int p = value % scale;
				sb.append(data[p]);
				value /= scale;
			}
		} else if (obj instanceof Short) {
			short value = (Short) obj;
			if (value < 0) {
				// 同byte
				int value2 = value & 0x7fff | 0x8000;
				return divMethod(value2, scale);
			} else if (value == 0)
				sb.append(0);
			while (value > 0) {
				int p = value % scale;
				sb.append(data[p]);
				value /= scale;
			}
		} else if (obj instanceof Integer) {
			int value = (Integer) obj;
			if (value < 0) {
				// 转为无符号数，最后的或操作需要是一个long型
				long value2 = value & 0x7fff_ffff | 0x8000_0000L;
				return divMethod(value2, scale);
			} else if (value == 0)
				sb.append(0);
			while (value > 0) {
				int p = value % scale;
				sb.append(data[p]);
				value /= scale;
			}
		} else if (obj instanceof Long) {
			long value = (Long) obj;
			if (value < 0) {
				// TODO 负long暂不支持，仅支持二、四、八、十六、三十二进制的转换
				BigInteger bint = BigInteger.valueOf(value & 0x7fff_ffff_ffff_ffffL);
				bint = bint.add(BigInteger.valueOf(0x8000_0000_0000_0000L));
				BigInteger bzero = BigInteger.valueOf(0);
				BigInteger bscale = BigInteger.valueOf(scale);
				while (bint.compareTo(bzero) != 0) {
					int p = bint.mod(bscale).intValue();
					sb.append(data[p]);
					bint = bint.divide(bscale);
				}
				System.out.println(sb.reverse().toString());
			} else if (value == 0)
				sb.append(0);
			while (value > 0) {
				int p = (int) (value % scale);
				sb.append(data[p]);
				value /= scale;
			}
		}
		return sb;
	}

	/**
	 * 整数通过<b>移位法</b>转成其他进制的字符串形式
	 * 
	 * @param obj
	 * @param scale
	 * @return
	 */
	private StringBuffer shiftMethod(Object obj, int scale) {
		int move = getMoved(scale);
		int and = scale - 1;
		StringBuffer sb = new StringBuffer();
		if (obj instanceof Byte) {
			byte value = (Byte) obj;
			if (value == 0)
				sb.append(0);
			while (value != 0) {
				int p = (int) (value & and);
				sb.append(data[p]);
				// byte和short均会自动转型为int，无符号右移的结果实际是int的高位被置0，再转型为short时又只是相当于截取了低位，高位的修改没有影响，因此>>>的结果出现了错误。
				// value = (short) (value >>> move); // 错误
				value = (byte) ((value & 0xff) >> move); // 正确：先对已经自动转型的value与上0xff，使得高位为0。
			}
		} else if (obj instanceof Short) {
			short value = (Short) obj;
			if (value == 0)
				sb.append(0);
			while (value != 0) {
				int p = (int) (value & and);
				sb.append(data[p]);
				// 原理同byte
				value = (short) ((value & 0xffff) >> move);
			}
		} else if (obj instanceof Integer) {
			int value = (Integer) obj;
			if (value == 0)
				sb.append(0);
			while (value != 0) {
				int p = (int) (value & and);
				sb.append(data[p]);
				value >>>= move;
			}
		} else if (obj instanceof Long) {
			long value = (Long) obj;
			if (value == 0)
				sb.append(0);
			while (value != 0) {
				int p = (int) (value & and);
				sb.append(data[p]);
				value >>>= move;
			}
		}
		return sb;
	}

	/**
	 * 获得位移的位数
	 * 
	 * @param scale
	 * @return
	 */
	private int getMoved(int scale) {
		int i = 0;
		while (scale > 1) {
			i++;
			scale >>>= 1;
		}
		return i;
	}

	/**
	 * 检测是否是浮点数
	 * 
	 * @param obj
	 * @return
	 */
	private static boolean isFloatPointNumber(Object obj) {
		isNumber(obj);
		if (obj instanceof Float || obj instanceof Double)
			return true;
		return false;
	}

	/**
	 * 是否是二的幂次倍数
	 * 
	 * @param scale
	 * @return
	 */
	private static boolean isPowerOfTwo(int scale) {
		checkScale(scale);
		// 2^6=64>36, 2^5=32<36 => i=1,2,3,4,5
		int base = 2;
		for (int i = 1; i < 6; i++) {
			if (scale == base)
				return true;
			base *= 2;
		}
		return false;
	}

	/**
	 * 检测是否是数字
	 * 
	 * @param obj
	 */
	private static void isNumber(Object obj) {
		if (obj == null || !(obj instanceof Number))
			throw new RuntimeException("object should be a number.");
	}

	/**
	 * 检测scale的范围
	 * 
	 * @param scale
	 */
	private static void checkScale(int scale) {
		if (scale < 2 || scale > 36)
			throw new RuntimeException("scale is between 2 and 36.");
	}

	/**
	 * 获得ScaleConvertUtils的实例
	 * 
	 * @return
	 */
	public static ScaleConvertUtils getInstance() {
		try {
			return clz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return new ScaleConvertUtils();
	}

	/**
	 * 转为八进制字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toOctalString(Object obj) {
		return toOctalString(obj, false);
	}

	/**
	 * 转为八进制字符串，可以格式化字符串（添加空格隔成小段）
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String toOctalString(Object obj, boolean format) {
		if (format)
			return toFormatString(obj, OCT);
		return toString(obj, OCT);
	}

	/**
	 * 转为十六进制字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toHexString(Object obj) {
		return toHexString(obj, false);
	}

	/**
	 * 转为十六进制字符串，可以格式化字符串（添加空格隔成小段）
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String toHexString(Object obj, boolean format) {
		if (format)
			return toFormatString(obj, HEX);
		return toString(obj, HEX);
	}

	/**
	 * 转为二进制字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toBinaryString(Object obj) {
		return toBinaryString(obj, false);
	}

	/**
	 * 转为二进制字符串，可以格式化字符串（添加空格隔成小段）
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String toBinaryString(Object obj, boolean format) {
		if (format)
			return toFormatString(obj, BIN);
		return toString(obj, BIN);
	}

	/**
	 * 转为十进制字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toDecimalString(Object obj) {
		return toDecimalString(obj, false);
	}

	/**
	 * 转为十进制字符串，可以格式化字符串（添加空格隔成小段）
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String toDecimalString(Object obj, boolean format) {
		if (format)
			return toFormatString(obj, DEC);
		return toString(obj, DEC);
	}

	/**
	 * 将二进制字符串转为long型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static long parseBinaryStringToLong(String src) throws Exception {
		return parseString(src, BIN, Long.class);
	}

	/**
	 * 将十六进制字符串转为long型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static long parseHexStringToLong(String src) throws Exception {
		return parseString(src, HEX, Long.class);
	}

	/**
	 * 将八进制字符串转为long型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static long parseOctalStringToLong(String src) throws Exception {
		return parseString(src, OCT, Long.class);
	}

	/**
	 * 将十进制字符串转为long型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static long parseDecimalStringToLong(String src) throws Exception {
		return parseString(src, DEC, Long.class);
	}

	/**
	 * 将二进制字符串转为int型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static int parseBinaryString(String src) throws Exception {
		return parseString(src, BIN, Integer.class);
	}

	/**
	 * 将十六进制字符串转为int型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static int parseHexString(String src) throws Exception {
		return parseString(src, HEX, Integer.class);
	}

	/**
	 * 将八进制字符串转为int型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static int parseOctalString(String src) throws Exception {
		return parseString(src, OCT, Integer.class);
	}

	/**
	 * 将十进制字符串转为int型整数
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static int parseDecimalString(String src) throws Exception {
		return parseString(src, DEC, Integer.class);
	}

	/**
	 * 将二进制字符串转为Double
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static double parseBinaryStringToDouble(String src) throws Exception {
		return parseString(src, BIN, Double.class);
	}

	/**
	 * 将十六进制字符串转为Double
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static double parseHexStringToDouble(String src) throws Exception {
		return parseString(src, HEX, Double.class);
	}

	/**
	 * 将八进制字符串转为Double
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static double parseOctalStringToDouble(String src) throws Exception {
		return parseString(src, OCT, Double.class);
	}

	/**
	 * 将十进制字符串转为Double
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static double parseDecimalStringToDouble(String src) throws Exception {
		return parseString(src, DEC, Double.class);
	}

	/**
	 * 将二进制字符串转为Float
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static float parseBinaryStringToFloat(String src) throws Exception {
		return parseString(src, BIN, Float.class);
	}

	/**
	 * 将十六进制字符串转为Float
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static float parseHexStringToFloat(String src) throws Exception {
		return parseString(src, HEX, Float.class);
	}

	/**
	 * 将八进制字符串转为Float
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static float parseOctalStringToFloat(String src) throws Exception {
		return parseString(src, OCT, Float.class);
	}

	/**
	 * 将十进制字符串转为Float
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static float parseDecimalStringToFloat(String src) throws Exception {
		return parseString(src, DEC, Float.class);
	}

}
