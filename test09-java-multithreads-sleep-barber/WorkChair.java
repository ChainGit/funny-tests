package com.chain.test.day07;

/**
 * 理发椅
 * 
 * @author chain
 *
 */
public class WorkChair extends Chair {

	private Customer customer;

	public WorkChair(String name, int limit) {
		super(name, limit);
	}

	@Override
	public synchronized void sit(Customer customer) throws InterruptedException {
		// 避免直接使用ConcurrentHashMap.size()
		while (n >= limit)
			wait();
		this.customer = customer;
		++n;
		notify();
	}

	@Override
	public synchronized void leave(Customer customer) throws InterruptedException {
		while (n <= 0)
			wait();
		this.customer = null;
		--n;
		notify();
	}

	/**
	 * 获得椅子上的顾客
	 * 
	 * @return
	 */
	public Customer get() {
		return customer;
	}

	/**
	 * 顾客离开椅子
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void leave() throws InterruptedException {
		leave(null);
	}

}
