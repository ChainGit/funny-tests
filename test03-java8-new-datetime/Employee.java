package com.chain.javase.test.day05;

public class Employee {

	private int id;
	private String name;
	private int age;
	private int salary;
	private String department;

	public Employee(int id, String name, int age, int salary, String department) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", name=").append(name).append(", age=").append(age)
				.append(", salary=").append(salary).append(", department=").append(department).append("]");
		return builder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Employee() {
		super();
	}

	public String show(Integer x) {
		return x + "x";
	}

	public Employee(String name) {
		super();
		this.name = name;
	}

}
