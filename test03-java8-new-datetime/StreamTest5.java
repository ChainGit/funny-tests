package com.chain.javase.test.day05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Stream练习2
 * 
 * @author Chain
 *
 */
public class StreamTest5 {

	private static List<Transaction> transactions = null;

	static {
		Trader t1 = new Trader("T1", "X1");
		Trader t2 = new Trader("T2", "X2");
		Trader t3 = new Trader("T3", "X3");
		Trader t4 = new Trader("T4", "X1");

		transactions = Arrays.asList(new Transaction(t1, 2008, 300), new Transaction(t2, 2011, 800),
				new Transaction(t1, 2008, 600), new Transaction(t3, 2011, 400), new Transaction(t1, 2017, 300),
				new Transaction(t3, 2011, 200), new Transaction(t4, 2004, 372));
	}

	// 1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
	@Test
	public void test1() {
		transactions.stream().filter(e -> e.getYear() == 2011).sorted((e1, e2) -> e1.getValue() - e2.getValue())
				.collect(Collectors.toList()).forEach(System.out::println);
	}

	// 2. 交易员都在哪些不同的城市工作过？
	@Test
	public void test2() {
		List<String> lst1 = transactions.stream().map(e -> e.getTrader().getCity()).distinct()
				.collect(Collectors.toList());
		System.out.println(lst1);
	}

	// 3. 查找所有来自X1的交易员，并按姓名排序
	@Test
	public void test3() {
		List<String> lst1 = transactions.stream().map(e -> e.getTrader()).filter(e -> "X1".equals(e.getCity()))
				.map(e -> e.getName()).distinct().sorted().collect(Collectors.toList());
		System.out.println(lst1);
	}

	// 4. 返回所有交易员的姓名字符串，按字母顺序排序
	@Test
	public void test4() {
		transactions.stream().map(e -> e.getTrader().getName()).distinct().sorted().forEach(System.out::println);
	}

	// 5. 有没有交易员是在X3工作的？
	@Test
	public void test5() {
		boolean c1 = transactions.stream().filter(e -> "X3".equals(e.getTrader().getCity())).count() > 0;
		boolean c2 = transactions.stream().anyMatch(e -> "X3".equals(e.getTrader().getCity()));
		System.out.println(c1);
		System.out.println(c2);
	}

	// 6. 打印生活在X1的交易员的所有交易额
	@Test
	public void test6() {
		int sum1 = transactions.stream().filter(e -> "X1".equals(e.getTrader().getCity()))
				.collect(Collectors.summingInt(e -> e.getValue())).intValue();
		int sum2 = transactions.stream().filter(e -> "X1".equals(e.getTrader().getCity())).map(e -> e.getValue())
				.reduce(Integer::sum).get();
		System.out.println(sum1);
		System.out.println(sum2);
	}

	// 7. 所有交易中，最高的交易额是多少
	@Test
	public void test7() {
		int max = transactions.stream().map(e -> e.getValue()).max(Integer::max).get();
		System.out.println(max);
	}

	// 8. 找到交易额最小的交易
	@Test
	public void test8() {
		int min = transactions.stream().map(e -> e.getValue()).collect(Collectors.minBy(Integer::compareTo)).get();
		System.out.println(min);
	}

}
