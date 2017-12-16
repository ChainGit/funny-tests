package com.chain.javase.test.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Stream的终止操作：allMatch,anyMatch,noneMatch,findFirst,findAny,count,max,min,forEach
 * 还有：reduce,collect
 * 
 * 流一旦结束后就不能再使用
 * 
 * 流的真正处理是在结束时再处理"中间过程"，称为懒处理
 * 
 * @author Chain
 *
 */
public class StreamTest3 {

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

	@Test
	public void test1() {
		// 是否每一个元素都匹配
		System.out.println(lst.stream().allMatch((e) -> e.getName().contains("c")));
		// 是否至少有一个元素匹配
		System.out.println(lst.stream().anyMatch((e) -> e.getName().contains("c")));
		// 是否没有元素匹配
		System.out.println(lst.stream().noneMatch((e) -> e.getName().contains("c")));
		System.out.println(lst.stream().noneMatch((e) -> e.getName().contains("A")));
	}

	@Test
	public void test2() {
		// 返回当前流的第一个元素
		System.out.println(lst.stream().findFirst());
		// 返回当前流的任意一个元素
		System.out.println(lst.stream().findAny());
		System.out.println(lst.stream().filter(e -> e.getSalary() > 5000).findFirst().get());
		System.out.println(lst.stream().sorted((e1, e2) -> e2.getSalary() - e1.getSalary()).findAny().get());
	}

	@Test
	public void test3() {
		// 找到最大(其实是返回排序后的元素的最后一个)
		System.out.println(lst.stream().max((e1, e2) -> e1.getSalary() - e2.getSalary()));
		System.out.println(lst.stream().max((e1, e2) -> e2.getSalary() - e1.getSalary()));
		// 找到最下(其实是返回排序后的元素的第一个)
		System.out.println(lst.stream().min((e1, e2) -> e1.getSalary() - e2.getSalary()));
		System.out.println(lst.stream().min((e1, e2) -> e2.getSalary() - e1.getSalary()));
	}

	@Test
	public void test4() {
		// 返回最终流中元素的个数
		System.out.println(lst.stream().filter(e -> e.getSalary() > 5000).count());
	}

	// 小练习
	@Test
	public void test5() {
		System.out.println(lst.stream().map(Employee::getSalary).max(Integer::compareTo).get());
		System.out.println(
				lst.stream().sorted((e1, e2) -> e2.getSalary() - e1.getSalary()).findFirst().get().getSalary());
	}

	// reduce：将流中的数据反复结合起来，最终得到一个值
	@Test
	public void test6() {
		List<Integer> lst1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Integer sum = lst1.stream().reduce(0, (x, y) -> x + y);
		System.out.println(sum);

		// Map-Reduce模型
		Optional<Integer> s1 = lst.stream().map(Employee::getSalary).reduce(Integer::sum);
		System.out.println(s1.get());

		Optional<Integer> s2 = lst.stream().map(Employee::getName).map(x -> {
			if (x.contains("c"))
				return 1;
			else
				return 0;
		}).reduce(Integer::sum);

		System.out.println(s2.get());
	}

	// collect：将Stream转为其他形式，可以使用Collectors转为其他方式，当然也可以做reduce的功能
	@Test
	public void test7() {
		List<Employee> lst1 = lst.stream().collect(Collectors.toList());
		lst1.forEach(System.out::println);

		System.out.println();

		Set<Employee> lst2 = lst.stream().collect(Collectors.toSet());
		lst2.forEach(System.out::println);

		System.out.println();

	}

	@Test
	public void test8() {

		// 统计总共有多少元素
		Long count = lst.stream().collect(Collectors.counting());
		System.out.println(count);

		// 中间，头，尾的连接
		String s = lst.stream().map(Employee::getName).collect(Collectors.joining("---", "....", "xxx"));
		System.out.println(s);

		// 和reduce的功能差不多
		Optional<Integer> sum = lst.stream().map(Employee::getSalary).collect(Collectors.reducing(Integer::sum));
		System.out.println(sum);

		// 按某个条件分为true或者false，这两组
		Map<Boolean, List<Employee>> map = lst.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
		System.out.println(map);

		// 按某个条件分组
		Map<String, List<Employee>> map1 = lst.stream().collect(Collectors.groupingBy(Employee::getName));
		System.out.println(map1);

		// 嵌套（分级）分组
		Map<String, Map<String, List<Employee>>> map2 = lst.stream()
				.collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy((e) -> {
					if (e.getSalary() > 7000) {
						return "A";
					} else if (e.getSalary() > 5000) {
						return "B";
					} else if (e.getSalary() > 2000) {
						return "C";
					}
					return "D";
				})));
		System.out.println(map2);

		// 依据某个条件找出最大，类似max
		Optional<Integer> max = lst.stream().map(Employee::getSalary).collect(Collectors.maxBy(Integer::compare));
		System.out.println(max);

		// 类似min
		Optional<Employee> op = lst.stream()
				.collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
		System.out.println(op.get());

		// 类似sum
		Double sum1 = lst.stream().collect(Collectors.summingDouble(Employee::getSalary));
		System.out.println(sum1);

		// 求平均值
		Double avg = lst.stream().collect(Collectors.averagingDouble(Employee::getSalary));
		System.out.println(avg);

		// 先统计，后再根据统计信息求具体信息
		DoubleSummaryStatistics dss = lst.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println(dss.getMax());
	}

}
