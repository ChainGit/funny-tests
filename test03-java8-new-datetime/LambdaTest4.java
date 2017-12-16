package com.chain.javase.test.day05;

import java.io.PrintStream;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用 （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 * 
 * 1. 对象的引用 :: 实例方法名
 * 
 * 2. 类名 :: 静态方法名
 * 
 * 3. 类名 :: 实例方法名
 * 
 * 注意： ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！ ②若Lambda
 * 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 * 
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 * 
 * 1. 类名 :: new
 * 
 * 三、数组引用
 * 
 * 类型[] :: new;
 * 
 * 
 * @author Chain
 *
 */
public class LambdaTest4 {

	// 方法引用：对象的引用 :: 实例方法名
	@Test
	public void test1() {
		Consumer<String> c1 = System.out::println;
		c1.accept("test1");

		PrintStream ps = System.out;
		Consumer<String> c2 = ps::println;
		c2.accept("test2");

		Employee e = new Employee(1, "ABC", 20, 1200, "XXX");
		Supplier<String> s1 = e::getName;
		System.out.println(s1.get());

	}

	// 方法引用：类名 :: 静态方法名
	@Test
	public void test2() {
		BiFunction<Integer, Integer, Integer> bf1 = (x, y) -> Math.max(x, y);
		System.out.println(bf1.apply(100, 120));

		BiFunction<Integer, Integer, Integer> bf2 = Math::max;
		System.out.println(bf2.apply(100, 120));
	}

	// 类名 :: 实例方法名
	@Test
	public void test3() {
		Employee e = new Employee(1, "ABC", 20, 1200, "XXX");
		// 第一个参数需要是传入调用者
		Function<Employee, String> bf1 = Employee::getName;
		System.out.println(bf1.apply(e));

		// 类名::实例方法名 的局限性，需要自己创建MyFunction，以支持更多的参数传递
		// BiFunction<Integer, String, Integer> bf2 = Integer::parseInt;
		BiFunction<Employee, Integer, String> bf2 = Employee::show;
		System.out.println(bf2.apply(e, 100));
	}

	// 构造器引用: 类名::new
	@Test
	public void test4() {
		// 只能使用无参的构造函数
		Supplier<Employee> s = Employee::new;
		Employee e = s.get();
		e.setName("XYZ");
		System.out.println(e.getName());
	}

	// 数组引用：类型[]::new
	@Test
	public void test5() {
		// 第一个参数是数组的长度，第二个是返回
		Function<Integer, String[]> f = String[]::new;
		// 创建数组，长度为20
		String[] strs = f.apply(20);
		System.out.println(strs.length);
	}

}
