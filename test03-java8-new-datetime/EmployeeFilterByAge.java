package com.chain.javase.test.day05;

public class EmployeeFilterByAge implements MyFilter<Employee> {

	private Integer c;

	public EmployeeFilterByAge(Integer age) {
		super();
		this.c = age;
	}

	@Override
	public boolean gt(Employee t) {
		return t.getAge() > c;
	}

	@Override
	public boolean lt(Employee t) {
		return t.getAge() < c;
	}

	@Override
	public boolean eq(Employee t) {
		return t.getAge() == c;
	}

}
