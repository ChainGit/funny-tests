package com.chain.blog.test.day02;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用自定义的阻塞队列，基于synchronized、wait/notify
 * 
 * @author Chain
 *
 */
public class ShopImplD implements Shop {

	private MyBlockArrayQueue product = new MyBlockArrayQueue(Shop.MAX);

	public int inTimes;
	public int outTimes;

	@Override
	public int now() {
		return product.size();
	}

	@Override
	public boolean in() {
		product.add();
		synchronized (this) {
			inTimes++;
			return true;
		}
	}

	@Override
	public boolean out() {
		product.poll();
		synchronized (this) {
			outTimes++;
			return true;
		}
	}

}

/**
 * 自定义的阻塞队列
 * 
 * @author Chain
 *
 */
class MyBlockArrayQueue {

	private int limit;
	private int floor;

	private Queue<Object> queue;

	public MyBlockArrayQueue(int limit) {
		this.limit = limit;
		this.queue = new ArrayDeque<>(limit);
	}

	public synchronized void add() {
		while (size() >= limit) {
			try {
				System.out.println("queue is full");
				wait();
			} catch (InterruptedException e) {
			}
		}
		queue.add(new Object());
		System.out.println("after add, queue left is " + size());
		notifyAll();
	}

	public synchronized void poll() {
		while (size() <= floor) {
			System.out.println("queue is empty");
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		queue.poll();
		System.out.println("after poll, queue left is " + size());
		notifyAll();
	}

	public synchronized int size() {
		return queue.size();
	}
}
