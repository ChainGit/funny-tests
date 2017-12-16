package com.chain.test.day06;

import java.util.Random;

/**
 * 供应者
 * 
 * 烟草+纸，纸+胶水，胶水+烟草的组合信号量
 * 
 * @author chain
 *
 */
public class SupplierB extends AbstractSupplier {

	private Random random;

	public SupplierB(int id, AbstractSemaphore[] semaphores, AbstractSemaphore finish, AbstractMaterials materials) {
		super(id, semaphores, finish, materials);
		this.random = new Random();
	}

	@Override
	public void put() throws Exception {
		int toMake = Math.abs(random.nextInt()) % semaphores.length;
		String material = materials.get(toMake);

		System.out.println(getName() + " make materials");

		// 提供者放置材料
		process();

		semaphores[toMake].V();

		System.out.println(getName() + " has put materials： " + material);

		// 等待吸烟者取走材料
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
