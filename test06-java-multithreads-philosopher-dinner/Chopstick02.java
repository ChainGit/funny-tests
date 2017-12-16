package com.chain.test.day04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 筷子
 * 
 * 使用ReentrantLock和Condition来实现PV操作
 * 
 * @author chain
 *
 */
public class Chopstick02 extends AbstractChopstick {

	private ReentrantLock lock;
	private Condition cond;

	public Chopstick02(int id) {
		super(id);
		lock = new ReentrantLock();
		cond = lock.newCondition();
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
	public void use() throws InterruptedException {
		lock.lock();
		try {
			--count;
			// 小于0说明有多个线程进入了使用临界资源
			if (count < 0)
				cond.await();
		} finally {
			if (lock.isLocked())
				lock.unlock();
		}
	}

	/**
	 * 放下筷子
	 * 
	 * 原语操作
	 */
	public void drop() {
		lock.lock();
		try {
			++count;
			// 通知其他进程
			if (count <= 0)
				cond.signal();
		} finally {
			if (lock.isLocked())
				lock.unlock();
		}
	}

}
