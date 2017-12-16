package com.chain.test.day04;

/**
 * 筷子的抽象类
 * 
 * 临界资源，只能同时被一个哲学家使用（抓起）
 * 
 * @author chain
 *
 */
public abstract class AbstractChopstick {

	// 筷子序号
	protected int id;

	public AbstractChopstick(int id) {
		this.id = id;
	}

	/**
	 * 获得筷子序号
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 获得筷子名字
	 * 
	 * @return
	 */
	public String getName() {
		return "Chopstick-" + getId();
	}

	/**
	 * 筷子是否被使用
	 * 
	 * @return
	 */
	public abstract boolean isUse();

	/**
	 * 使用（抓起）筷子
	 * 
	 * @throws Exception
	 */
	public abstract void use() throws Exception;

	/**
	 * 放下筷子
	 */
	public abstract void drop();

}
