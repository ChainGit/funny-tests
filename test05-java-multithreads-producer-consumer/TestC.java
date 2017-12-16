package com.chain.blog.test.day02;

import java.util.concurrent.CountDownLatch;

public class TestC {

	public static void main(String[] args) {
		// test1();
		test2();
	}

	// 多个生产者和消费者，每个生产者和消费者也具有多个线程
	private static void test2() {
		CountDownLatch latch = new CountDownLatch(8);
		Shop shop = new ShopImplC();
		Consumer consumer1 = new Consumer(shop, latch);
		Producer producer1 = new Producer(shop, latch);
		Consumer consumer2 = new Consumer(shop, latch);
		Producer producer2 = new Producer(shop, latch);
		new Thread(consumer1, "consumerA1").start();
		new Thread(producer1, "producerA1").start();
		new Thread(consumer1, "consumerA2").start();
		new Thread(producer1, "producerA2").start();
		new Thread(consumer2, "consumerB1").start();
		new Thread(producer2, "producerB1").start();
		new Thread(consumer2, "consumerB2").start();
		new Thread(producer2, "producerB2").start();

		while (latch.getCount() != 0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}

		System.out.println();
		System.out.println("in times: " + ((ShopImplC) shop).inTimes);
		System.out.println("out times: " + ((ShopImplC) shop).outTimes);
		System.out.println("shop left: " + shop.now());
	}

	// 只有一个消费者和一个生产者，似乎没有出现爆仓和负仓的问题，但是商品有时有剩余，不能刚好买多少卖多少
	private static void test1() {
		CountDownLatch latch = new CountDownLatch(2);
		Shop shop = new ShopImplC();
		Consumer consumer = new Consumer(shop, latch);
		Producer producer = new Producer(shop, latch);
		new Thread(consumer, "consumerA").start();
		new Thread(producer, "producerA").start();

		while (latch.getCount() != 0) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}

		System.out.println();
		System.out.println("in times: " + ((ShopImplC) shop).inTimes);
		System.out.println("out times: " + ((ShopImplC) shop).outTimes);
		System.out.println("shop left: " + shop.now());
	}

}
