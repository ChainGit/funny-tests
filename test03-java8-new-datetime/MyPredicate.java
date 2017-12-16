package com.chain.javase.test.day05;

/**
 * 策略设计模式2
 * 
 * @author Chain
 *
 * @param <T>
 */
public interface MyPredicate<T> {

	public boolean test(T t);

}
