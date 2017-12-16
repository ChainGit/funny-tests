package com.chain.javase.test.day05;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

public class MyAnnotationTest {

	@Test
	public void test1() {
		MyAnnotationTestClass matc = new MyAnnotationTestClass();
		matc.show("Jack");
		matc.show(null);
	}

	@Test
	public void test2() throws NoSuchMethodException, SecurityException {
		Class clz = MyAnnotationTestClass.class;
		Method method = clz.getDeclaredMethod("show", String.class);
		System.out.println(method);
		MyAnnotations mas = method.getAnnotation(MyAnnotations.class);
		MyAnnotation[] ma = mas.value();
		for (int i = 0; i < ma.length; i++) {
			System.out.println(ma[i].value());
		}
	}
}
