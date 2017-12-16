package com.chain.test.day04;

/**
 * 哲学家抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractPhilosopher {

	// 哲学家序号
	protected int id;

	public AbstractPhilosopher(int id) {
		this.id = id;
	}

	/**
	 * 获得哲学家的序号
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 获得哲学家的名字
	 * 
	 * @return
	 */
	public String getName() {
		return "Philosopher-" + getId();
	}

	/**
	 * 思考
	 * 
	 * @throws Exception
	 */
	public abstract void think() throws Exception;

	/**
	 * 抓筷子
	 * 
	 * @throws Exception
	 */
	public abstract void take(AbstractChopstick chopstick) throws Exception;

	/**
	 * 放筷子
	 */
	public abstract void put(AbstractChopstick chopstick);

	/**
	 * 就餐
	 * 
	 * @throws Exception
	 */
	public abstract void eat() throws Exception;

}
