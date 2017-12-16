package com.chain.javase.test.day05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Lambda测试2：
 * 
 * 背景：
 * 
 * 员工以List存储，找出工资大于5000，且年龄小于35岁的员工，按姓名排序，得到前两个。
 * 
 * @author Chain
 *
 */
public class LambdaTest2 {

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

	/*
	 * 做法一：传统做法
	 */
	@Test
	public void test1() {
		int salary = 5000;
		int age = 35;
		int limit = 2;

		show(method1(lst, salary, age, limit));
	}

	// 完全写死，虽然可以分成几个小方法，但灵活性不好
	public Set<Employee> method1(List<Employee> lst, int salary, int age, int limit) {
		Set<Employee> set = new TreeSet<>(new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		for (Employee e : lst) {
			if (e.getSalary() > salary && e.getAge() < age) {
				if (set.size() < limit)
					set.add(e);
			}
		}

		return set;
	}

	/*
	 * 做法二：策略设计模式
	 */
	@Test
	public void test2() {
		int salary = 5000;
		int age = 35;
		int limit = 2;

		show(method2(lst, new EmployeeFilterBySalary(salary), new EmployeeFilterByAge(age), limit));
	}

	// 需要同时生成MyFilter的实现类
	public Set<Employee> method2(List<Employee> lst, MyFilter<Employee> filterBySalary, MyFilter<Employee> filterByAge,
			int limit) {
		Set<Employee> set = new TreeSet<>(new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		for (Employee e : lst) {
			if (filterBySalary.gt(e) && filterByAge.lt(e))
				if (set.size() < limit)
					set.add(e);
		}

		return set;
	}

	/*
	 * 方法三：策略设计模式（匿名内部类）
	 */
	@Test
	public void test3() {
		int salary = 5000;
		int age = 35;
		int limit = 2;

		MyPredicate<Employee> employeePredicateBySalary = new MyPredicate<Employee>() {

			@Override
			public boolean test(Employee t) {
				return t.getSalary() > salary;
			}
		};

		MyPredicate<Employee> employeePredicateByAge = new MyPredicate<Employee>() {

			@Override
			public boolean test(Employee t) {
				return t.getAge() < age;
			}
		};

		show(method3(lst, employeePredicateBySalary, employeePredicateByAge, limit));
	}

	// 仍然需要额外的内部类
	public Set<Employee> method3(List<Employee> lst, MyPredicate<Employee> predicateBySalary,
			MyPredicate<Employee> predicateByAge, int limit) {
		Set<Employee> set = new TreeSet<>(new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		for (Employee e : lst) {
			if (predicateBySalary.test(e) && predicateByAge.test(e))
				if (set.size() < limit)
					set.add(e);
		}

		return set;
	}

	/*
	 * 方法四：使用Lambda表达式（使用方法三中的MyPredicate实现）
	 */
	@Test
	public void test4() {
		int salary = 5000;
		int age = 35;
		int limit = 2;

		// lambda的写法1
		MyPredicate<Employee> employeePredicateBySalary = (e) -> {
			return e.getSalary() > salary;
		};

		// lambda的写法2
		MyPredicate<Employee> employeePredicateByAge = e -> e.getAge() < age;

		show(method3(lst, employeePredicateBySalary, employeePredicateByAge, limit));
	}

	/*
	 * 方法五：使用Lambda表达式和StreamAPI
	 */
	@Test
	public void test5() {
		int salary = 5000;
		int age = 35;
		int limit = 2;

		List<Employee> nlst = lst.stream().filter((x) -> x.getSalary() > salary && x.getAge() < age).limit(limit)
				.sorted((e1, e2) -> e1.getName().compareTo(e2.getName())).collect(Collectors.toList());

		show(nlst);
	}

}
