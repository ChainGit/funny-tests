package com.chain.blog.test.day02;

import java.util.concurrent.CountDownLatch;

/**
 * 生产者
 * 
 * @author Chain
 *
 */
public class Producer implements Runnable {

	private Shop shop;

	private CountDownLatch latch;

	public Producer(Shop shop, CountDownLatch latch) {
		this.shop = shop;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < Constant.TIMES; i++) {
				try {
					// 随机暂停一段时间，模拟不同的情况
					// Thread.sleep((int) (Math.random() * 301 + 100));
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}

				put();
			}
		} finally {
			latch.countDown();
		}
	}

	private void put() {
		boolean res = shop.in();
		if (res) {
			System.out.println("producer " + Thread.currentThread().getName() + " has put one");
		} else {
			System.out.println("producer " + Thread.currentThread().getName() + " put one fail");
		}
	}

}
