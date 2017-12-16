package com.chain.test.day06;

import java.util.Random;

/**
 * 供应者
 * 
 * 烟草、纸、胶水的单独信号量
 * 
 * @author chain
 *
 */
public class SupplierA extends AbstractSupplier {

	private Random random;

	public SupplierA(int id, AbstractSemaphore[] semaphores, AbstractSemaphore finish, AbstractMaterials materials) {
		super(id, semaphores, finish, materials);
		this.random = new Random();
	}

	@Override
	public void put() throws Exception {
		int mlen = semaphores.length;
		int notMake = Math.abs(random.nextInt()) % mlen;

		System.out.println(getName() + " make materials");

		StringBuffer sb = new StringBuffer();
		sb.append(getName() + " has put materials： ");
		for (int i = 0; i < mlen; i++) {
			if (i == notMake)
				continue;
			sb.append(materials.get(i) + " ");
		}

		String msg = sb.toString();

		process();

		for (int i = 0; i < mlen; i++) {
			if (i == notMake)
				continue;
			semaphores[i].V();
		}

		System.out.println(msg);

		finish.P();
	}

	// 处理其他事情
	private void process() {
		try {
			Thread.sleep(100 * ((int) (Math.random() * 9 + 1)));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
