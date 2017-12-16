package com.chain.test.day04;

/**
 * 哲学家抓放筷子的方法的抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractPhilosopherMethod {

	/**
	 * 哲学家
	 * 
	 * 吃饭在前，思考在后
	 * 
	 * @throws Exception
	 */
	public abstract void method(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception;
}
