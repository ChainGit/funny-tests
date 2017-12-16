package com.chain.javase.test.day05;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	@Test
	public void test1() {
		Employee e = new Employee("小明");
		Optional<Employee> op = Optional.of(e);
		System.out.println(op);
	}

	@Test
	public void test2() {
		Optional<Employee> op = Optional.empty();
		System.out.println(op);
	}

	@Test
	public void test3() {
		Employee e1 = new Employee("小明");
		Optional<Employee> op = Optional.ofNullable(e1);
		System.out.println(op);

		Employee e2 = null;
		Optional<Employee> op2 = Optional.ofNullable(e2);
		System.out.println(op2);
	}

	@Test
	public void test4() {
		Employee e1 = null;
		Employee e2 = new Employee("小红");
		Employee e3 = new Employee("小刚");

		boolean b1 = Optional.ofNullable(e1).isPresent();
		boolean b2 = Optional.ofNullable(e2).isPresent();
		System.out.println(b1);
		System.out.println(b2);

		Employee e4 = Optional.ofNullable(e1).orElse(e3);
		System.out.println(e4);

		Employee e5 = Optional.ofNullable(e2).orElse(e3);
		System.out.println(e5);
	}

	@Test
	public void test5() {
		Employee e1 = null;
		Employee e2 = new Employee("小红");
		Employee e3 = new Employee("小刚");

		Employee e4 = Optional.ofNullable(e1).orElseGet(Employee::new);
		System.out.println(e4);

		Employee e5 = Optional.ofNullable(e2).orElseGet(() -> e3);
		System.out.println(e5);
	}

	@Test
	public void test6() {
		Employee e1 = null;
		Employee e2 = new Employee("小红");

		Optional<Employee> op = Optional.ofNullable(e1).map(e -> new Employee());
		System.out.println(op);

		Optional<Employee> op2 = Optional.ofNullable(e2).map(e -> new Employee());
		System.out.println(op2);
	}

}
