package com.chain.javase.reflect.test;

import java.lang.reflect.Method;

import org.junit.Test;

import com.chain.javase.reflect.entities.Person;
import com.chain.javase.reflect.entities.Student;
import com.chain.javase.reflect.utils.ReflectionUtils;

public class GetAndInvokeMethodTest {

	@Test
	public void test() {
		Class personClass = Person.class;
		Method m1 = ReflectionUtils.getDeclaredMethod(personClass, "show");
		System.out.println(m1);
		ReflectionUtils.invokeMethod(personClass, m1);
		Method m2 = ReflectionUtils.getDeclaredMethod(personClass, "show", String.class);
		System.out.println(m2);
		ReflectionUtils.invokeMethod(personClass, m2, "msg");

		Class studentClass = Student.class;
		Method m3 = ReflectionUtils.getDeclaredMethod(studentClass, "show", String.class);
		System.out.println(m3);
		ReflectionUtils.invokeMethod(studentClass, m3, "msg");
		Method m4 = ReflectionUtils.getDeclaredMethod(studentClass, "show", String.class, int.class);
		System.out.println(m4);
		ReflectionUtils.invokeMethod(studentClass, m4, "Msg", 1);

	}

}
