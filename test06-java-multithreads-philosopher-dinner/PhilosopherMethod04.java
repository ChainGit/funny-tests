package com.chain.test.day04;

/**
 * 当且仅当两个筷子都可以
 * 
 * 使用wait和notify
 * 
 * @author chain
 *
 */
public class PhilosopherMethod04 extends AbstractPhilosopherMethod {

	private volatile Status[] status;

	public PhilosopherMethod04() {
		status = new Status[Main.PHILOSOPHER_NUM];
		for (int i = 0; i < status.length; i++)
			status[i] = Status.HUNGRY;
	}

	@Override
	public void method(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		takes(p, cs);

		p.eat();

		puts(p, cs);

		p.think();
	}

	/**
	 * 放下手中的筷子，并通知其他所有的哲学家
	 * 
	 * @param p
	 * @param cs
	 */
	private synchronized void puts(AbstractPhilosopher p, AbstractChopstick[] cs) {
		int id = p.getId();
		int right = (id + 1) % Main.PHILOSOPHER_NUM;
		p.put(cs[id]);
		p.put(cs[right]);
		status[p.getId()] = Status.THINK;
		this.notifyAll();
	}

	/**
	 * 尝试同时抓起两边的筷子
	 * 
	 * @param p
	 * @param cs
	 * @throws Exception
	 */
	private synchronized void takes(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		int id = p.getId();
		status[id] = Status.HUNGRY;
		trial(p, cs);
		while (status[id] != Status.EAT) {
			this.wait();
			trial(p, cs);
		}
	}

	private synchronized void trial(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		int id = p.getId();
		int n = Main.PHILOSOPHER_NUM;
		int left = (id - 1 + n) % n;
		int right = (id + 1) % n;
		if (status[id] == Status.HUNGRY && status[left] != Status.EAT && status[right] != Status.EAT) {
			p.take(cs[id]);
			p.take(cs[right]);
			status[id] = Status.EAT;
			this.notifyAll();
		}
	}

}
