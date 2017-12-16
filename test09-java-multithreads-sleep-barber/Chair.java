package com.chain.test.day07;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 椅子
 * 
 * 不同类型的椅子看成一个整体
 * 
 * 类似一个阻塞队列
 * 
 * @author chain
 *
 */
public class Chair {

	// 已经坐在椅子上的顾客
	private Map<Integer, Customer> customers;

	// 已经坐在椅子上的顾客的数量
	protected int n;

	// 该种类椅子总共的数量
	protected volatile int limit;

	// 椅子类型
	private String name;

	public Chair(String name, int limit) {
		this.name = name;
		this.limit = limit;
		this.customers = new ConcurrentHashMap<>(limit << 1);
	}

	public String getName() {
		return name;
	}

	/**
	 * 顾客尝试坐下
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void sit(Customer customer) throws InterruptedException {
		// 避免直接使用ConcurrentHashMap.size()
		while (n >= limit)
			wait();
		customers.put(customer.getId(), customer);
		++n;
		notify();
	}

	/**
	 * 顾客尝试离开
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public synchronized void leave(Customer customer) throws InterruptedException {
		while (n <= 0)
			wait();
		customers.remove(customer.getId());
		--n;
		notify();
	}

	/**
	 * 获得椅子上的顾客
	 * 
	 * @return
	 */
	public Customer get(Customer customer) {
		return customers.get(customer.getId());
	}

	/**
	 * 椅子是否坐满了
	 * 
	 * @return
	 */
	public boolean isFull() {
		return n >= limit;
	}

	/**
	 * 已经坐在椅子上的顾客
	 * 
	 * @return
	 */
	public int current() {
		return n;
	}

}
