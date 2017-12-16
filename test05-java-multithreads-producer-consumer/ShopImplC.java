package com.chain.blog.test.day02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显式锁和显示条件
 * 
 * @author Chain
 *
 */
public class ShopImplC implements Shop {

	private volatile int product;

	private ReentrantLock lock = new ReentrantLock();
	private Condition conditionIn = lock.newCondition();
	private Condition conditionOut = lock.newCondition();

	public int inTimes;
	public int outTimes;

	@Override
	public int now() {
		lock.lock();
		try {
			return product;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean in() {
		lock.lock();
		try {
			while (now() >= Shop.MAX) {
				System.out.println("shop is full");
				try {
					conditionIn.await();
				} catch (InterruptedException e) {
				}
			}
			product++;
			inTimes++;
			System.out.println("after in, shop left is " + now());
			conditionOut.signalAll();
			return true;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean out() {
		lock.lock();
		try {
			while (now() <= Shop.MIN) {
				System.out.println("shop is empty");
				try {
					conditionOut.await();
				} catch (InterruptedException e) {
				}
			}
			product--;
			outTimes++;
			System.out.println("after out, shop left is " + now());
			conditionIn.signalAll();
			return true;
		} finally {
			lock.unlock();
		}
	}

}
