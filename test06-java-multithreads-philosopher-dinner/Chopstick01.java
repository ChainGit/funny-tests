package com.chain.test.day04;

/**
 * 筷子
 * 
 * 使用wait和notify来实现PV操作
 * 
 * @author chain
 *
 */
public class Chopstick01 extends AbstractChopstick {

	public Chopstick01(int id) {
		super(id);
	}

	private volatile int count = 1;

	/**
	 * 筷子是否被使用
	 * 
	 * @return
	 */
	public boolean isUse() {
		return count < 0;
	}

	/**
	 * 使用（抓起）筷子
	 * 
	 * 原语操作
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void use() throws InterruptedException {
		--count;
		// 小于0说明有多个线程进入了使用临界资源
		if (count < 0)
			this.wait();
	}

	/**
	 * 放下筷子
	 * 
	 * 原语操作
	 */
	public synchronized void drop() {
		++count;
		// 通知其他进程
		if (count <= 0)
			this.notify();
	}

}
