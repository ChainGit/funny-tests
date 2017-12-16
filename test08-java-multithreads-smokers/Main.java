package com.chain.test.day06;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

/**
 * 主测试方法
 * 
 * @author chain
 *
 */
public class Main {

	private Properties prop;

	private CyclicBarrier barrier;

	private AbstractSmoker[] smokers;
	private AbstractSupplier[] suppliers;
	private AbstractSemaphore[] semaphores;
	private AbstractSemaphore finish;

	private AbstractMaterials materials;

	private Class<? extends AbstractSmoker> smokerclass;
	private Class<? extends AbstractSupplier> supplierclass;
	private Class<? extends AbstractSemaphore> semaphoreclass;
	private Class<? extends AbstractMaterials> materialsclass;

	private static final int SUPPLIERS_NUM = 1;
	private static final int SMOKERS_NUM = 3;
	private static final int MATERIALS_NUM = 3;

	private SmokerSupplierThread[] threads;

	@Test
	public void test() throws Exception {
		initProperties();
		initMaterials();
		initSemaphores();
		initSmokers();
		initSuppliers();
		initAndStartAndJoinSmokerSupplierThreads();
	}

	private void initMaterials() throws Exception {
		materials = materialsclass.getConstructor().newInstance();
	}

	private void initAndStartAndJoinSmokerSupplierThreads() throws InterruptedException {
		int totalNum = SMOKERS_NUM + SUPPLIERS_NUM;
		barrier = new CyclicBarrier(totalNum);
		threads = new SmokerSupplierThread[totalNum];
		for (int i = 0; i < SMOKERS_NUM; i++)
			threads[i] = new SmokerSupplierThread(smokers[i]);
		for (int i = 0; i < SUPPLIERS_NUM; i++)
			threads[i + SMOKERS_NUM] = new SmokerSupplierThread(suppliers[i]);

		for (int i = 0; i < totalNum; i++)
			threads[i].start();

		for (int i = 0; i < totalNum; i++)
			threads[i].join();
	}

	private void initSemaphores() throws Exception {
		Constructor<? extends AbstractSemaphore> constructor = semaphoreclass.getConstructor(int.class);
		semaphores = new AbstractSemaphore[MATERIALS_NUM];
		for (int i = 0; i < MATERIALS_NUM; i++)
			semaphores[i] = constructor.newInstance(0);
		finish = constructor.newInstance(1);
	}

	private void initSuppliers() throws Exception {
		Constructor<? extends AbstractSupplier> constructor = supplierclass.getConstructor(int.class,
				AbstractSemaphore[].class, AbstractSemaphore.class, AbstractMaterials.class);
		suppliers = new AbstractSupplier[SUPPLIERS_NUM];
		for (int i = 0; i < SUPPLIERS_NUM; i++)
			suppliers[i] = constructor.newInstance(i, semaphores, finish, materials);
	}

	private void initSmokers() throws Exception {
		Constructor<? extends AbstractSmoker> constructor = smokerclass.getConstructor(int.class,
				AbstractSemaphore[].class, AbstractSemaphore.class, AbstractMaterials.class);
		smokers = new AbstractSmoker[SMOKERS_NUM];
		for (int i = 0; i < SMOKERS_NUM; i++)
			smokers[i] = constructor.newInstance(i, semaphores, finish, materials);
	}

	@SuppressWarnings("unchecked")
	private void initProperties() throws IOException, ClassNotFoundException {
		prop = new Properties();
		prop.load(new FileInputStream("bin/com/chain/test/day06/test.properties"));
		smokerclass = (Class<? extends AbstractSmoker>) Class.forName(prop.getProperty("smoker.class"));
		supplierclass = (Class<? extends AbstractSupplier>) Class.forName(prop.getProperty("supplier.class"));
		semaphoreclass = (Class<? extends AbstractSemaphore>) Class.forName(prop.getProperty("semaphore.class"));
		materialsclass = (Class<? extends AbstractMaterials>) Class.forName(prop.getProperty("material.class"));
	}

	private class SmokerSupplierThread extends Thread {

		private boolean isSupplier;
		private AbstractSmoker smoker;
		private AbstractSupplier supplier;

		public SmokerSupplierThread(AbstractSmoker abstractSmoker) {
			this.smoker = abstractSmoker;
			this.isSupplier = false;
		}

		public SmokerSupplierThread(AbstractSupplier abstractSupplier) {
			this.supplier = abstractSupplier;
			this.isSupplier = true;
		}

		@Override
		public void run() {
			try {
				barrier.await();
				if (isSupplier) {
					System.out.println(supplier.getName() + " is ready");
					while (true)
						supplier.put();
				} else {
					System.out.println(smoker.getName() + " is ready");
					while (true)
						smoker.take();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

}
