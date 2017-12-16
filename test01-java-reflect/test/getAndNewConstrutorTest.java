package com.chain.javase.reflect.test;

import java.lang.reflect.Constructor;

import org.junit.Test;

import com.chain.javase.reflect.entities.Person;
import com.chain.javase.reflect.entities.Student;
import com.chain.javase.reflect.utils.ReflectionUtils;

public class getAndNewConstrutorTest {

	@Test
	public void test() {
		Class studentClass = Student.class;
		Constructor cs = ReflectionUtils.getConstructor(studentClass, String.class, Integer.class);
		Person person1 = (Person) ReflectionUtils.newInstanceByConstrutor(cs, "Jack", 22);
		System.out.println(person1);
		// 不允许父类直接强转为子类
		// Student student1 = (Student) ReflectionUtils.newInstanceByConstrutor(cs,
		// "Jack", 22);
		// System.out.println(student1);
	}

}
