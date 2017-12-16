package com.chain.blog.test.day02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用java的ArrayBlockingQueue，基于显式锁和显示条件
 * 
 * @author Chain
 *
 */
public class ShopImplF implements Shop {

	private BlockingQueue<Object> product = new ArrayBlockingQueue<>(MAX);

	public int inTimes;
	public int outTimes;

	@Override
	public synchronized int now() {
		return product.size();
	}

	@Override
	public synchronized boolean in() {
		while (product.size() >= MAX) {
			System.out.println("shop is full");
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		product.add(new Object());
		inTimes++;
		System.out.println("after in, shop left is " + now());
		notifyAll();
		return true;
	}

	@Override
	public synchronized boolean out() {
		while (product.size() <= MIN) {
			System.out.println("shop is empty");
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		product.poll();
		outTimes++;
		System.out.println("after out, shop left is " + now());
		notifyAll();
		return true;
	}

}
