package com.chain.test.day04;

import java.util.concurrent.Semaphore;

/**
 * 筷子
 * 
 * 使用Semaphore来实现PV操作
 * 
 * @author chain
 *
 */
public class Chopstick03 extends AbstractChopstick {

	private Semaphore mutex;

	public Chopstick03(int id) {
		super(id);
		mutex = new Semaphore(1);
	}

	/**
	 * 筷子是否被使用
	 * 
	 * @return
	 */
	public boolean isUse() {
		return mutex.availablePermits() <= 0;
	}

	/**
	 * 使用（抓起）筷子
	 * 
	 * 原语操作
	 * 
	 * @throws InterruptedException
	 */
	public void use() throws InterruptedException {
		mutex.acquire();
	}

	/**
	 * 放下筷子
	 * 
	 * 原语操作
	 */
	public void drop() {
		mutex.release();
	}

}
