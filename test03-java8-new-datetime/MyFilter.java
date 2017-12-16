package com.chain.javase.test.day05;

/**
 * 策略设计模式
 * 
 * @author Chain
 *
 * @param <T>
 * @param <C>
 */
public interface MyFilter<T> {

	public boolean gt(T t);

	public boolean lt(T t);

	public boolean eq(T t);

}
