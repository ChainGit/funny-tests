package com.chain.javase.test.day05;

public class MyAnnotationTestClass {

	@MyAnnotation("Hello")
	@MyAnnotation("World")
	public void show(@MyAnnotation String name) {
		System.out.println(name);
	}
}
