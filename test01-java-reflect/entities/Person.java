package com.chain.javase.reflect.entities;

public class Person {

	private String name;
	private Integer age;

	static {
		System.out.println("Person的静态代码块");
	}

	{
		System.out.println("Person的代码块");
	}

	static {
		System.out.println("Person的静态代码块2");
	}

	{
		System.out.println("Person的代码块2");
	}

	public Person() {
		System.out.println("Person的无参构造器");
	}

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("Person的有参构造器");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", age=").append(age).append("]");
		return builder.toString();
	}

	public void show() {
		System.out.println("Person show(): ...");
	}

	public void show(String msg) {
		System.out.println("Person show(): " + msg);
	}

	private void show(String msg, int count) {
		System.out.println("Person show(): " + msg + " : " + count);
	}
}
