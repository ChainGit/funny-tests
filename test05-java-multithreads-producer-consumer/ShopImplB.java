package com.chain.blog.test.day02;

/**
 * 使用synchronized、wait/notify实现线程同步
 * 
 * 注意虚假唤醒的问题
 * 
 * @author Chain
 *
 */
public class ShopImplB implements Shop {

	private volatile int product;

	public int inTimes;
	public int outTimes;

	@Override
	public synchronized int now() {
		return product;
	}

	@Override
	public synchronized boolean in() {
		// 注意这里需要是while，不能是if，因为线程除了会唤醒out操作，也会唤醒in的操作
		while (now() >= Shop.MAX) {
			System.out.println("shop is full");
			try {
				wait();
			} catch (InterruptedException e) {
			}
			// return false;
		}
		product++;
		inTimes++;
		System.out.println("after in, shop left is " + now());
		notifyAll();
		return true;
	}

	@Override
	public synchronized boolean out() {
		while (now() <= Shop.MIN) {
			System.out.println("shop is empty");
			try {
				wait();
			} catch (InterruptedException e) {
			}
			// return false;
		}
		product--;
		outTimes++;
		System.out.println("after out, shop left is " + now());
		notifyAll();
		return true;
	}

}
