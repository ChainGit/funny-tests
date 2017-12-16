package com.chain.javase.test.day05;

public class EmployeeFilterBySalary implements MyFilter<Employee> {

	private Integer c;

	public EmployeeFilterBySalary(Integer salary) {
		super();
		this.c = salary;
	}

	@Override
	public boolean gt(Employee t) {
		return t.getSalary() > c;
	}

	@Override
	public boolean lt(Employee t) {
		return t.getSalary() < c;
	}

	@Override
	public boolean eq(Employee t) {
		return t.getSalary() == c;
	}

}
