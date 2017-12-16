package com.chain.test.day08;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 工作流网图测试
 * 
 * @author chain
 *
 */
public class Main {

	// 图
	private Edge[][] diagram;
	// 节点值
	private int[] nodes;

	// 节点的线程
	private NodeThread[] nodeThreads;
	// 边上信号量
	private Semaphore[] semaphores;

	private CyclicBarrier barrier;

	// 节点数量
	private int nums;

	// 信号量的数量
	private int sems;

	// 用于根据边上的权值发送信号量
	private ScheduledThreadPoolExecutor executor;

	private int goods;

	private enum Type {
		// P7和P0不相连，AOE
		AOE,
		// P7和P0相连，AOE
		AOE_RING,
		// P7和P0不相连，AOV
		AOV,
		// P7和P0相连，AOE
		AOV_RING
	}

	private Type type;

	{
		type = Type.AOE;
	}

	private void init() {
		switch (type) {
		case AOE:
		case AOV:
			init0();
			break;
		case AOE_RING:
		case AOV_RING:
			init1();
			break;
		}
	}

	private void input() {
		switch (type) {
		case AOE:
			input0();
			break;
		case AOE_RING:
			input1();
			break;
		case AOV:
			input2();
			break;
		case AOV_RING:
			input3();
			break;
		}
	}

	// P7和P0不相连
	private void init0() {
		nums = 8;
		sems = 12;

		goods = 10;
	}

	// P7和P0相连
	private void init1() {
		init0();

		sems = 13;
	}

	// P7和P0不相连，AOE
	private void input0() {
		nodes[0] = 0;
		nodes[1] = 0;
		nodes[2] = 0;
		nodes[3] = 0;
		nodes[4] = 0;
		nodes[5] = 0;
		nodes[6] = 0;
		nodes[7] = 0;

		Edge e01 = Edge.of(semaphores[0], 2);
		Edge e02 = Edge.of(semaphores[1], 1);
		Edge e03 = Edge.of(semaphores[2], 2);
		Edge e14 = Edge.of(semaphores[3], 3);
		Edge e15 = Edge.of(semaphores[9], 5);
		Edge e25 = Edge.of(semaphores[4], 2);
		Edge e23 = Edge.of(semaphores[10], 5);
		Edge e47 = Edge.of(semaphores[6], 4);
		Edge e57 = Edge.of(semaphores[7], 2);
		Edge e67 = Edge.of(semaphores[8], 3);
		Edge e35 = Edge.of(semaphores[11], 6);
		Edge e36 = Edge.of(semaphores[5], 4);

		diagram[0][1] = e01;
		diagram[0][2] = e02;
		diagram[0][3] = e03;
		diagram[1][4] = e14;
		diagram[1][5] = e15;
		diagram[2][5] = e25;
		diagram[2][3] = e23;
		diagram[4][7] = e47;
		diagram[5][7] = e57;
		diagram[6][7] = e67;
		diagram[3][5] = e35;
		diagram[3][6] = e36;
	}

	// P7和P0相连，AOE
	private void input1() {
		input0();

		Edge e70 = Edge.of(semaphores[12], 0);
		diagram[7][0] = e70;

		try {
			// 假设P7已经有了一个产品
			semaphores[12].V();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// P7和P0不相连，AOV
	private void input2() {
		nodes[0] = 2;
		nodes[1] = 4;
		nodes[2] = 3;
		nodes[3] = 5;
		nodes[4] = 2;
		nodes[5] = 1;
		nodes[6] = 2;
		nodes[7] = 3;

		Edge e01 = Edge.of(semaphores[0], 0);
		Edge e02 = Edge.of(semaphores[1], 0);
		Edge e03 = Edge.of(semaphores[2], 0);
		Edge e14 = Edge.of(semaphores[3], 0);
		Edge e15 = Edge.of(semaphores[9], 0);
		Edge e25 = Edge.of(semaphores[4], 0);
		Edge e23 = Edge.of(semaphores[10], 0);
		Edge e47 = Edge.of(semaphores[6], 0);
		Edge e57 = Edge.of(semaphores[7], 0);
		Edge e67 = Edge.of(semaphores[8], 0);
		Edge e35 = Edge.of(semaphores[11], 0);
		Edge e36 = Edge.of(semaphores[5], 0);

		diagram[0][1] = e01;
		diagram[0][2] = e02;
		diagram[0][3] = e03;
		diagram[1][4] = e14;
		diagram[1][5] = e15;
		diagram[2][5] = e25;
		diagram[2][3] = e23;
		diagram[4][7] = e47;
		diagram[5][7] = e57;
		diagram[6][7] = e67;
		diagram[3][5] = e35;
		diagram[3][6] = e36;
	}

	// P7和P0相连，AOV
	private void input3() {
		input2();

		Edge e70 = Edge.of(semaphores[12], 0);
		diagram[7][0] = e70;

		try {
			// 假设P7已经有了一个产品
			semaphores[12].V();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test() throws InterruptedException {
		init();

		diagram = new Edge[nums][nums];
		nodes = new int[nums];

		semaphores = new Semaphore[sems];
		for (int i = 0; i < sems; i++)
			semaphores[i] = new Semaphore();

		input();

		barrier = new CyclicBarrier(nums);

		executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(sems);
		try {
			nodeThreads = new NodeThread[nums];
			for (int i = 0; i < nums; i++)
				nodeThreads[i] = new NodeThread(i, nodes[i]);

			for (int i = 0; i < nums; i++)
				nodeThreads[i].start();

			for (int i = 0; i < nums; i++)
				nodeThreads[i].join();
		} finally {
			if (executor != null && !executor.isShutdown())
				executor.shutdown();
		}

	}

	private class NodeThread extends Thread {

		private int id;
		private String name;

		// 该节点生产时间
		private int time;

		// 生产的次数
		private int amount;

		private volatile boolean status;

		public NodeThread(int id, int time) {
			this.id = id;
			this.name = "node-" + id;
			this.time = time;
		}

		@Override
		public void run() {
			try {
				status = true;
				barrier.await();
				System.out.println(name + " is ready");
				long begin = System.currentTimeMillis();
				while (status) {
					System.out.println(name + " wait for semaphores");
					long start = System.currentTimeMillis();
					recv();
					System.out.println(name + " is working");
					process();
					long end = System.currentTimeMillis();
					System.out.println(name + " finish work, cost " + (end - start) + ", amount " + amount);
					send();
					System.out.println(name + " has sent semaphores");
				}
				long finish = System.currentTimeMillis();
				System.out.println(name + " IS DONE, TOTAL COST " + (finish - begin) + ", FINAL AMOUNT " + amount);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		// 等待所有入该节点的信号量
		public void recv() throws InterruptedException {
			for (int i = 0; i < nums; i++) {
				Edge e = diagram[i][id];
				if (e == null)
					continue;
				e.semaphore.P();
			}
		}

		// 通知所有出该节点的信号量
		public void send() {
			for (int i = 0; i < nums; i++) {
				Edge e = diagram[id][i];
				if (e == null)
					continue;
				executor.schedule(new Msg(e.semaphore), e.value, TimeUnit.SECONDS);
			}
		}

		// 生产该节点负责的部分
		public void process() throws InterruptedException {
			Thread.sleep(time * 1000);
			if (++amount == goods)
				status = false;
		}

	}

	private class Msg implements Callable<Semaphore> {

		// 信号量
		private Semaphore semaphore;

		public Msg(Semaphore semaphore) {
			this.semaphore = semaphore;
		}

		@Override
		public Semaphore call() throws Exception {
			// 通知节点
			semaphore.V();
			return semaphore;
		}

	}

	/**
	 * 邻接矩阵的边
	 * 
	 * @author chain
	 *
	 */
	private static class Edge {
		// 信号量
		private Semaphore semaphore;
		// 边的权值
		private int value;

		public static Edge of(Semaphore semaphore, int value) {
			Edge edge = new Edge();
			edge.semaphore = semaphore;
			edge.value = value;
			return edge;
		}
	}

}
