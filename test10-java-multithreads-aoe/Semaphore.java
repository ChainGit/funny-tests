package com.chain.test.day08;

/**
 * 自定义信号量
 * 
 * @author chain
 *
 */
public class Semaphore {

	private volatile int n;

	private static final int PERMITS = Integer.MAX_VALUE;

	public synchronized void V() throws InterruptedException {
		while (n >= PERMITS)
			wait();
		++n;
		notify();
	}

	public synchronized void P() throws InterruptedException {
		while (n <= 0)
			wait();
		--n;
		notify();
	}

}
