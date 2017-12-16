package com.chain.javase.test.day05;

/**
 * lambda的Function接口
 * 
 * @author Chain
 *
 */
// 这个注解的目的在于检测是否符合Lambda接口：只有一个抽象方法
@FunctionalInterface
public interface MyFunction<T, R> {

	public R apply(T t);

	// Java8新特性
	default String getInfo() {
		return "xxxxx";
	}

	public static String show() {
		return "aaaaa";
	}
}
