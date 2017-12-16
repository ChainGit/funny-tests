package com.chain.test.day07;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 顾客
 * 
 * @author chain
 *
 */
public class Customer {

	// 顾客序号
	private int id;

	// 顾客姓名
	private String name;

	// 顾客是否进入店里
	private volatile boolean enter;

	// 顾客理过的次数
	private long times;

	public Customer(int id) {
		this.id = id;
		this.name = "customer-" + id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * 顾客理发中
	 * 
	 * @throws InterruptedException
	 */
	public void enjoy() throws InterruptedException {
		System.out.println(name + " is enjoying");
		process(100);
		times++;
		System.out.println(name + " is finished, times " + times);
	}

	public long getTimes() {
		return times;
	}

	/**
	 * 顾客尝试叫醒理发师，成功叫醒理发师的顾客能得到理发
	 * 
	 * @param waitChairs
	 * @param barber
	 * @return
	 * @throws InterruptedException
	 */
	public boolean awake(Chair waitChairs, Barber barber) throws InterruptedException {
		ReentrantLock lock = barber.getLock();
		WorkChair workChair = barber.getWorkChair();
		// 在这里如果理发椅有人，则理发师一定醒着并在理发中，则该顾客放弃叫醒理发师，继续保持等待
		if (!workChair.isFull()) {
			if (lock.tryLock()) {
				try {
					if (!workChair.isFull()) {
						// 顾客先尝试坐在理发椅上
						workChair.sit(this);
						// 然后再离开等待椅
						waitChairs.leave(this);
						System.out.println(name + " has left wait-chair, and sit on the work-chair");
						// 之后通知理发师
						barber.getCondition().signal();
						return true;
					}
				} finally {
					if (lock.isLocked())
						lock.unlock();
				}
			}

		}
		return false;
	}

	public void outdoor() throws InterruptedException {
		process(50);
	}

	public void dosth() throws InterruptedException {
		process(30);
	}

	// cost越小，越接近高并发
	private void process(int cost) throws InterruptedException {
		Thread.sleep(cost * ((int) (Math.random() * 9) + 1));
	}

	/**
	 * 顾客离开理发椅，并走出理发店
	 * 
	 * @param barber
	 * @throws InterruptedException
	 */
	public void bye(Barber barber) throws InterruptedException {
		barber.getWorkChair().leave();
		leave();
	}

	public void leave() {
		this.enter = false;
	}

	public void enter() {
		this.enter = true;
	}

	public boolean isEntered() {
		return enter;
	}

}
