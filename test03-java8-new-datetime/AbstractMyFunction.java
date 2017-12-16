package com.chain.javase.test.day05;

public abstract class AbstractMyFunction {

	static String show() {
		return "ccccc";
	}

	public abstract String getInfo();

	public void print() {
		System.out.println("hello");
	}

	// default String getInfo() {
	// return "zzzzzz";
	// }
}
