package com.chain.test.day07;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 理发师
 * 
 * @author chain
 *
 */
public class Barber {

	// 理发师的姓名
	private String name = "barber";

	// 理发椅
	private WorkChair workChair;

	private ReentrantLock lock;
	private Condition cond;

	// 理发师理过的顾客数量
	private long times;

	public long getTimes() {
		return times;
	}

	public Condition getCondition() {
		return cond;
	}

	public WorkChair getWorkChair() {
		return workChair;
	}

	public ReentrantLock getLock() {
		return lock;
	}

	public Barber(WorkChair workChair) {
		this.workChair = workChair;
		this.lock = new ReentrantLock();
		this.cond = this.lock.newCondition();
	}

	public String getName() {
		return name;
	}

	/**
	 * 理发师睡觉
	 * 
	 * @throws InterruptedException
	 */
	public void sleep() throws InterruptedException {
		// 如果理发椅上没有顾客就睡觉
		if (workChair.get() == null) {
			lock.lock();
			try {
				if (workChair.get() == null) {
					// 在这里理发师不会睡在理发椅上
					System.out.println(name + " is sleeping");
					cond.await();
					System.out.println(name + " is awake");
				}
			} finally {
				if (lock.isLocked())
					lock.unlock();
			}
		}
	}

	/**
	 * 给顾客理发
	 * 
	 * @throws InterruptedException
	 */
	public void work() throws InterruptedException {
		Customer customer = workChair.get();
		if (customer == null) {
			System.out.println("no customer in chair, the barber is confused");
			return;
		}
		System.out.println(name + " is working");
		customer.enjoy();
		customer.bye(this);
		++times;
		System.out.println(name + " is completed, times " + times);
	}

}
