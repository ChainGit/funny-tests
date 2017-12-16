package com.chain.javase.test.day05;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * Java8 内置的四大核心函数式接口
 * 
 * Consumer<T> : 消费型接口 void accept(T t);
 * 
 * Supplier<T> : 供给型接口 T get();
 * 
 * Function<T, R> : 函数型接口 R apply(T t);
 * 
 * Predicate<T> : 断言型接口 boolean test(T t);
 * 
 * 
 * @author Chain
 *
 */
public class LambdaTest3 {

	// 消费型接口
	@Test
	public void test1() {
		method1(1000, System.out::println);
	}

	public void method1(Integer a, Consumer<Integer> c) {
		c.accept(a);
	}

	// 供给型接口
	@Test
	public void test2() {
		System.out.println(method2(() -> 1000));
	}

	public Integer method2(Supplier<Integer> s) {
		return s.get();
	}

	// 函数型接口
	@Test
	public void test3() {
		System.out.println(method3(1000, (x) -> x + "x"));
	}

	public String method3(Integer c, Function<Integer, String> f) {
		return f.apply(c);
	}

	// 断言型接口(assert)
	@Test
	public void test4() {
		System.out.println(method4(1000, (x) -> x == 1000));
	}

	public boolean method4(Integer t, Predicate<Integer> p) {
		return p.test(t);
	}

}
