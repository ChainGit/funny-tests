package com.chain.javase.test.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Stream练习
 * 
 * @author Chain
 *
 */
public class StreamTest4 {

	/*
	 * 1. 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？ ，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
	 */
	@Test
	public void test1() {
		List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> nlst = lst.stream().map((x) -> x * x).collect(Collectors.toList());
		// System.out.println(nlst);
		nlst.forEach(System.out::println);

		System.out.println();
	}

	private static List<Employee> emps = null;

	static {
		emps = new ArrayList<>();
		emps.add(new Employee(11, "ego", 19, 8888, "AA"));
		emps.add(new Employee(12, "abc", 22, 1111, "BB"));
		emps.add(new Employee(12, "abc", 22, 1111, "BB"));
		emps.add(new Employee(13, "Ofv", 47, 5555, "AA"));
		emps.add(new Employee(19, "dia", 19, 0, null));
		emps.add(new Employee(14, "Pas", 46, 6666, "AA"));
		emps.add(new Employee(15, "vfr", 26, 4444, "CC"));
		emps.add(new Employee(16, "ocn", 40, 9999, "BB"));
		emps.add(new Employee(15, "vfr", 26, 4444, "CC"));
		emps.add(new Employee(17, "Xus", 24, 2222, "AA"));
		emps.add(new Employee(18, "ias", 16, 7777, "BB"));
		emps.add(new Employee(19, "Ucn", 39, 1234, "CC"));
		emps.add(new Employee(20, "sbc", 29, 3333, null));
	}

	/*
	 * 2. 怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
	 **/
	@Test
	public void test2() {
		Optional<Integer> sum = emps.stream().map(e -> 1).reduce(Integer::sum);
		System.out.println(sum.get());

		System.out.println();
	}
	
	

}
