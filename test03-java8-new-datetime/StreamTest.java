package com.chain.javase.test.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Java的StreamAPI测试
 * 
 * 创建、中间操作、结束
 * 
 * 结束时的懒处理
 * 
 * 创建流：集合（Collection）的自带stream、Arrays创建流、Stream的of静态方法；
 * 
 * 生成流：iterate、generate
 * 
 * @author Chain
 *
 */
public class StreamTest {

	private static List<Employee> lst = null;

	static {
		lst = new ArrayList<>();
		lst.add(new Employee(11, "ego", 19, 8888, "AA"));
		lst.add(new Employee(12, "abc", 22, 1111, "BB"));
		lst.add(new Employee(12, "abc", 22, 1111, "BB"));
		lst.add(new Employee(13, "Ofv", 47, 5555, "AA"));
		lst.add(new Employee(19, "dia", 19, 0, null));
		lst.add(new Employee(14, "Pas", 46, 6666, "AA"));
		lst.add(new Employee(15, "vfr", 26, 4444, "CC"));
		lst.add(new Employee(16, "ocn", 40, 9999, "BB"));
		lst.add(new Employee(15, "vfr", 26, 4444, "CC"));
		lst.add(new Employee(17, "Xus", 24, 2222, "AA"));
		lst.add(new Employee(18, "ias", 16, 7777, "BB"));
		lst.add(new Employee(19, "Ucn", 39, 1234, "CC"));
		lst.add(new Employee(20, "sbc", 29, 3333, null));
	}

	// 创建流
	@Test
	public void test1() {
		// 由集合（Collection）创建流
		Collection<Integer> c1 = new HashSet<>();
		Stream<Integer> s1 = c1.stream();
		s1.forEach(System.out::print);

		System.out.println();

		List<String> lst1 = new ArrayList<>();
		Stream<String> s2 = lst1.stream();
		s2.forEach(System.out::print);

		System.out.println();

		// 由Arrays工具类（Arrays）创建流
		// Collections每一个collection自带，所以没有提供stream
		IntStream s3 = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		s3.forEach(System.out::print);

		System.out.println();

		// 通过Stream的静态方法创建
		Stream<Integer> s4 = Stream.of(1, 2, 3, 4, 5);
		s4.forEach(System.out::print);

		System.out.println();

		Stream<Integer> s5 = Stream.of(new Integer[] { 1, 2, 3, 4, 5 });
		s5.forEach(System.out::print);

		System.out.println();

		// 创建无限流
		// 迭代(适合有初始值，和迭代条件)
		Stream<Integer> s6 = Stream.iterate(0, (x) -> x + 2).limit(10);
		s6.forEach(System.out::println);

		System.out.println();

		// 生成(适合循环,其实也可以折中实现迭代)
		Stream<Double> s7 = Stream.generate(Math::random).limit(2);
		s7.forEach(System.out::println);

		System.out.println();

		// Map类创建
		Map<String, List<Integer>> map = new HashMap<>();
		map.put("AA", Arrays.asList(1, 2, 3, 4, 5));
		map.put("BB", Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));
		map.values().stream().forEach(System.out::println);

		System.out.println();
	}

	@Test
	public void test2() {
		lst.stream().distinct().filter((x) -> x.getSalary() > 2000).skip(4).limit(8)
				.sorted((e1, e2) -> e1.getName().compareTo(e2.getName())).forEach(System.out::println);
	}
}
