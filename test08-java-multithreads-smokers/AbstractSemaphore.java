package com.chain.test.day06;

/**
 * 自定义信号量的抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractSemaphore {

	public abstract void P() throws Exception;

	public abstract void V() throws Exception;

	public abstract int available();
}
