package com.chain.javase.reflect.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

import com.chain.javase.reflect.annotation.ScoreValidator;
import com.chain.javase.reflect.entities.Student;
import com.chain.javase.reflect.utils.ReflectionUtils;

public class AnnotationTest {

	@Test
	public void test() {
		Class studentClass = Student.class;
		Method m1 = ReflectionUtils.getDeclaredMethod(studentClass, "setScore", int.class);
		Annotation a1 = ReflectionUtils.getDeclaredAnnotation(m1, ScoreValidator.class);
		System.out.println(a1);
		ScoreValidator sv = (ScoreValidator) a1;
		System.out.println(sv.max());
		System.out.println(sv.min());
	}

}
