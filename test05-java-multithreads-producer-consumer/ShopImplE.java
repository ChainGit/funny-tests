package com.chain.blog.test.day02;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用自定义的阻塞队列，基于显式锁和显示条件
 * 
 * @author Chain
 *
 */
public class ShopImplE implements Shop {

	private MyBlockArrayQueue2 product = new MyBlockArrayQueue2(MAX);

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

class MyBlockArrayQueue2 {

	private int limit;
	private int floor;

	private Queue<Object> queue;

	public MyBlockArrayQueue2(int limit) {
		this.limit = limit;
		this.queue = new ArrayDeque<>(limit);
	}

	private ReentrantLock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();

	public void add() {
		lock.lock();
		try {
			while (size() >= limit) {
				System.out.println("queue is full");
				try {
					notFull.await();
				} catch (InterruptedException e) {
				}
			}
			queue.add(new Object());
			System.out.println("after add, queue left is " + size());
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void poll() {
		lock.lock();
		try {
			while (size() <= floor) {
				System.out.println("queue is empty");
				try {
					notEmpty.await();
				} catch (InterruptedException e) {
				}
			}
			queue.poll();
			System.out.println("after poll, queue left is " + size());
			notFull.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		lock.lock();
		try {
			return queue.size();
		} finally {
			lock.unlock();
		}
	}

}
