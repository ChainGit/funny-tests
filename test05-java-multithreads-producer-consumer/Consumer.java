package com.chain.blog.test.day02;

import java.util.concurrent.CountDownLatch;

/**
 * 消费者
 * 
 * @author Chain
 *
 */
public class Consumer implements Runnable {

	private Shop shop;

	private CountDownLatch latch;

	public Consumer(Shop shop, CountDownLatch latch) {
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
					Thread.sleep(300);
				} catch (InterruptedException e) {
				}

				buy();
			}
		} finally {
			latch.countDown();
		}
	}

	/**
	 * 消费者购买商品
	 */
	private void buy() {
		boolean res = shop.out();
		if (res) {
			System.out.println("consumer " + Thread.currentThread().getName() + " has buy one");
		} else {
			System.out.println("consumer " + Thread.currentThread().getName() + " buy one fail");
		}
	}

}
