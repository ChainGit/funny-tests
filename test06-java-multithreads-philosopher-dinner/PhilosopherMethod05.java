package com.chain.test.day04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 当且仅当两个筷子都可以
 * 
 * 使用ReentrantLock和Condition
 * 
 * @author chain
 *
 */
public class PhilosopherMethod05 extends AbstractPhilosopherMethod {

	private ReentrantLock lock;
	private Condition[] cond;

	private volatile Status[] status;

	public PhilosopherMethod05() {
		status = new Status[Main.PHILOSOPHER_NUM];
		for (int i = 0; i < status.length; i++)
			status[i] = Status.HUNGRY;

		lock = new ReentrantLock();

		cond = new Condition[Main.PHILOSOPHER_NUM];
		for (int i = 0; i < cond.length; i++)
			cond[i] = lock.newCondition();
	}

	@Override
	public void method(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		takes(p, cs);

		p.eat();

		puts(p, cs);

		p.think();
	}

	/**
	 * 放下手中的筷子，并通知两边的哲学家
	 * 
	 * @param p
	 * @param cs
	 */
	private void puts(AbstractPhilosopher p, AbstractChopstick[] cs) {
		lock.lock();
		try {
			int id = p.getId();
			int n = Main.PHILOSOPHER_NUM;
			int left = (id - 1 + n) % n;
			int right = (id + 1) % n;
			p.put(cs[id]);
			p.put(cs[right]);
			status[p.getId()] = Status.THINK;
			trial(left, cs);
			trial(right, cs);
		} finally {
			if (lock.isLocked())
				lock.unlock();
		}
	}

	/**
	 * 尝试同时抓起两边的筷子
	 * 
	 * @param p
	 * @param cs
	 * @throws Exception
	 */
	private void takes(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		lock.lock();
		try {
			int id = p.getId();
			status[id] = Status.HUNGRY;
			trial(id, cs);
			while (status[id] != Status.EAT)
				cond[id].await();
			if (status[id] == Status.EAT) {
				int right = (id + 1) % Main.PHILOSOPHER_NUM;
				p.take(cs[id]);
				p.take(cs[right]);
			}
		} finally {
			if (lock.isLocked())
				lock.unlock();
		}
	}

	private void trial(int id, AbstractChopstick[] cs) {
		int n = Main.PHILOSOPHER_NUM;
		int left = (id - 1 + n) % n;
		int right = (id + 1) % n;
		if (status[id] == Status.HUNGRY && status[left] != Status.EAT && status[right] != Status.EAT) {
			status[id] = Status.EAT;
			cond[id].signal();
		}
	}

}
