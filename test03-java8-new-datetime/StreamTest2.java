package com.chain.javase.test.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Stream测试2
 * 
 * 中间操作：map,flatMap,filter,skip,limit,distinct,sorted
 * 
 * @author Chain
 *
 */
public class StreamTest2 {

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

	// map和flatMap
	@Test
	public void test1() {
		Stream<String> s1 = lst.stream().map(Employee::getName);
		s1.forEach(System.out::println);

		System.out.println();

		Stream<String> s2 = lst.stream().map(e -> e.getName().toUpperCase());
		s2.forEach(System.out::println);

		System.out.println();

		// 将每个元素映射到另一个元素上
		Stream<Stream<String>> s3 = lst.stream().map((e) -> method1(e));
		s3.forEach((e) -> e.forEach(System.out::println));

		System.out.println();

		// 将对象转换为流，然后再由各个流连接起来成为一个单独的流
		Stream<String> s4 = lst.stream().flatMap((e) -> method1(e));
		s4.forEach(System.out::println);

		System.out.println();
	}

	public Stream<String> method1(Employee e) {
		List<String> lst = new ArrayList<>();
		String name = e.getName();
		lst.add(name);
		lst.add("x");
		return lst.stream();
	}

}
