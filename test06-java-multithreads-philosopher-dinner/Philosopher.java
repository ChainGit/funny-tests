package com.chain.test.day04;

/**
 * 哲学家
 * 
 * @author chain
 *
 */
public class Philosopher extends AbstractPhilosopher {

	public Philosopher(int id) {
		super(id);
	}

	/**
	 * 思考
	 * 
	 * @throws InterruptedException
	 */
	public void think() throws InterruptedException {
		System.out.println(getName() + " is thinking");
		doSometing();
		System.out.println(getName() + " pause thinking");
	}

	// 修改的值越小越能体现出并发的问题
	private static final int DO_TIME = 3;

	private void doSometing() throws InterruptedException {
		Thread.sleep(100 * ((int) (Math.random() * DO_TIME) + 1));
	}

	/**
	 * 抓筷子
	 * 
	 * @throws Exception
	 */
	public void take(AbstractChopstick chopstick) throws Exception {
		chopstick.use();
		System.out.println(getName() + " use " + chopstick.getName());
	}

	/**
	 * 放筷子
	 */
	public void put(AbstractChopstick chopstick) {
		chopstick.drop();
		System.out.println(getName() + " drop " + chopstick.getName());
	}

	/**
	 * 就餐
	 * 
	 * @throws InterruptedException
	 */
	public void eat() throws InterruptedException {
		System.out.println(getName() + " is eating");
		doSometing();
		System.out.println(getName() + " pause eating");
	}

}
