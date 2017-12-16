package com.chain.javase.reflect.test;

import java.lang.reflect.Field;

import org.junit.Test;

import com.chain.javase.reflect.entities.Student;
import com.chain.javase.reflect.utils.ReflectionUtils;

public class GetAndSetFieldTest {

	@Test
	public void test() {
		Class studentClass = Student.class;
		Field f1 = ReflectionUtils.getDeclaredField(studentClass, "name");
		System.out.println(f1);
		Student student1 = (Student) ReflectionUtils.setField(studentClass, f1, "jack");
		System.out.println(student1);
		Field f2 = ReflectionUtils.getDeclaredField(studentClass, "age");
		System.out.println(f2);
		ReflectionUtils.setField(student1, f2, 22);
		System.out.println(student1);
		Field f3 = ReflectionUtils.getDeclaredField(studentClass, "score");
		System.out.println(f3);
		ReflectionUtils.setField(student1, f3, 90);
		System.out.println(student1);
	}

}
