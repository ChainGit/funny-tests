package com.chain.javase.test.day05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * 另一道题目：每个部门下工资最高的员工。
 * 
 * @author Chain
 *
 */
public class LambdaTest5 {

	private static List<Employee> lst = null;

	static {
		lst = new ArrayList<>();
		lst.add(new Employee(11, "ego", 19, 8888, "AA"));
		lst.add(new Employee(12, "abc", 22, 1111, "BB"));
		lst.add(new Employee(13, "Ofv", 47, 5555, "AA"));
		lst.add(new Employee(19, "dia", 19, 0, null));
		lst.add(new Employee(14, "Pas", 46, 6666, "AA"));
		lst.add(new Employee(15, "vfr", 26, 4444, "CC"));
		lst.add(new Employee(16, "ocn", 40, 9999, "BB"));
		lst.add(new Employee(17, "Xus", 24, 2222, "AA"));
		lst.add(new Employee(18, "ias", 16, 7777, "BB"));
		lst.add(new Employee(19, "Ucn", 39, 1234, "CC"));
		lst.add(new Employee(19, "sbc", 29, 3333, null));
		show(lst);
	}

	public static void show(Collection<?> lst) {
		for (Object obj : lst) {
			System.out.println(obj);
		}
		System.out.println();
	}

	// 做法一：使用Lambda和Stream
	@Test
	public void test1() {
		show(method1(lst));
	}

	private List<Employee> method1(List<Employee> lst) {
		List<Employee> nlst = new ArrayList<>();
		lst.stream().collect(Collectors.groupingBy((e) -> Optional.ofNullable(e.getDepartment()))).forEach((k, v) -> {
			// 既然已经分组，则意味着List不为null，且至少有一个数据
			// forEach以及高级for循环是利用集合的迭代iterator实现，有不能获得下标的缺点
			v.sort((e1, e2) -> e2.getSalary() - e1.getSalary());
			nlst.add(v.get(0));
		});
		return nlst;
	}

	// 做法二：传统做法
	@Test
	public void test2() {
		show(method2(lst));
	}

	// 可以拆分成小方法
	private List<Employee> method2(List<Employee> lst) {
		List<Employee> nlst = new ArrayList<>();

		// 存储分组
		Map<String, List<Employee>> groupMap = new LinkedHashMap<>();
		for (Employee e : lst) {
			// HashMap允许null作为键和值
			String dept = e.getDepartment();
			List<Employee> partLst = groupMap.get(dept);
			if (partLst == null) {
				// 在这道题里，如果有分类（null也看作一个分类），则该分类下必然有至少一名员工
				partLst = new ArrayList<>();
				groupMap.put(dept, partLst);
			}
			partLst.add(e);
		}

		// 遍历Map，这里没有使用Map.Entry或着keySet
		Collection<List<Employee>> colls = groupMap.values();
		for (List<Employee> partLst : colls) {
			int len = partLst.size();
			int max = 0;
			int mSalary = partLst.get(max).getSalary();
			for (int i = 1; i < len; i++) {
				int s = partLst.get(i).getSalary();
				if (s > mSalary) {
					max = i;
					mSalary = s;
				}
			}
			nlst.add(partLst.get(max));
		}

		return nlst;
	}

	// 做法三：传统做法优化
	@Test
	public void test3() {
		show(method3(lst));
	}

	private Collection<Employee> method3(List<Employee> lst) {
		Map<String, Employee> groupMap = new LinkedHashMap<>();
		for (Employee e : lst) {
			// HashMap允许null作为键和值
			String dept = e.getDepartment();
			Employee ep = groupMap.get(dept);
			if (ep == null || ep.getSalary() < e.getSalary()) {
				groupMap.put(dept, e);
			}
		}

		return groupMap.values();
	}
}
