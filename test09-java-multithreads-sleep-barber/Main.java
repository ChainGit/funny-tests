package com.chain.test.day07;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import org.junit.Test;

/**
 * 主测试方法
 * 
 * @author chain
 *
 */
public class Main {

	private BarberThread barberThread;
	private CustomerThread[] customerThreads;

	private Semaphore door;

	private Chair waitChairs;
	private WorkChair workChair;

	private Barber barber;
	private Customer[] customers;

	private int customersNum;

	private CyclicBarrier barrier;

	private MonitorThread monitorThread;

	@Test
	public void test() throws Exception {
		customersNum = (int) (Math.random() * 8) + 2;

		System.out.println("there is " + customersNum + " customers");

		barrier = new CyclicBarrier(customersNum + 1);

		door = new Semaphore(1);

		workChair = new WorkChair("work-chair", 1);

		// 等待椅的数量小于顾客的数量
		waitChairs = new Chair("wait-chair", customersNum / 2);

		System.out.println("there is " + customersNum / 2 + " wait-chairs");

		barber = new Barber(workChair);
		customers = new Customer[customersNum];
		for (int i = 0; i < customersNum; i++)
			customers[i] = new Customer(i);

		barberThread = new BarberThread();
		customerThreads = new CustomerThread[customersNum];
		for (int i = 0; i < customersNum; i++)
			customerThreads[i] = new CustomerThread(customers[i]);

		monitorThread = new MonitorThread();

		barberThread.start();
		for (int i = 0; i < customersNum; i++)
			customerThreads[i].start();

		monitorThread.start();

		barberThread.join();
		for (int i = 0; i < customersNum; i++)
			customerThreads[i].join();

		calcResult();
	}

	private void calcResult() {
		System.out.println();
		System.out.println("============= calc result =============");
		long barberTimes = barber.getTimes();
		long customersTimes = 0;
		for (int i = 0; i < customersNum; i++) {
			long ct = customers[i].getTimes();
			customersTimes += ct;
			System.out.println(customers[i].getName() + " times：" + ct);
		}
		System.out.println("barber times：" + barberTimes);
		System.out.println("customers total times：" + customersTimes);
		System.out.println("correct：" + (barberTimes == customersTimes));
	}

	private class MonitorThread extends Thread {

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			try {
				// 持续运行10分钟
				Thread.sleep(10 * 60 * 1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			barberThread.close();
			for (int i = 0; i < customersNum; i++)
				customerThreads[i].close();

			barberThread.interrupt();
			for (int i = 0; i < customersNum; i++)
				customerThreads[i].interrupt();

			barberThread.stop();
			for (int i = 0; i < customersNum; i++)
				customerThreads[i].stop();
		}

	}

	private class CustomerThread extends Thread {

		private volatile boolean status;

		private Customer customer;

		public CustomerThread(Customer customer) {
			this.customer = customer;
		}

		@Override
		public void run() {
			try {
				status = true;
				barrier.await();
				String name = customer.getName();
				System.out.println(name + " is ready");
				while (status) {
					System.out.println(name + " try to enter this barbershop");
					try {
						if (!door.tryAcquire())
							continue;
						customer.enter();
						System.out.println(name + " has entered this barbershop");
						try {
							if (waitChairs.isFull()) {
								System.out.println(name + " failed to sit for full");
								customer.leave();
								continue;
							}
							waitChairs.sit(customer);
							System.out.println(name + " has sit on one wait-chair, current " + waitChairs.current());
						} finally {
							door.release();
						}

						int max = (int) (Math.random() * 5) + 5;
						int wait = 0;
						while (wait++ < max && !customer.awake(waitChairs, barber))
							customer.dosth();

						// 此时等的不耐烦的顾客可能正要走时却成功叫醒了理发师
						if (wait >= max && waitChairs.get(customer) != null) {
							waitChairs.leave(customer);
							customer.leave();
							System.out.println(name + " couldn't wait any more");
						}
					} finally {
						while (customer.isEntered())
							customer.dosth();
						System.out.println(name + " leave this barbershop");
						customer.outdoor();
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public void close() {
			this.status = false;
		}

	}

	private class BarberThread extends Thread {

		private volatile boolean status;

		@Override
		public void run() {
			try {
				status = true;
				barrier.await();
				System.out.println(barber.getName() + " is ready");
				while (status) {
					barber.sleep();
					barber.work();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public void close() {
			this.status = false;
		}
	}

}
