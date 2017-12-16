package com.chain.test.day06;

/**
 * 自定义实现信号量
 * 
 * 使用wait和notify
 * 
 * @author chain
 *
 */
public class SemaphoreA extends AbstractSemaphore {

	// 资源数量
	private int permits;

	public SemaphoreA(int permits) {
		this.permits = permits;
	}

	@Override
	public synchronized void P() throws InterruptedException {
		--permits;
		if (permits < 0)
			this.wait();
	}

	@Override
	public synchronized void V() {
		++permits;
		if (permits <= 0)
			this.notify();
	}

	@Override
	public synchronized int available() {
		return permits;
	}
}
