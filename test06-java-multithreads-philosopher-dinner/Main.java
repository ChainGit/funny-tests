package com.chain.test.day04;

import java.io.FileInputStream;
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

	public static final int CHOPSTICK_NUM = 5;
	public static final int PHILOSOPHER_NUM = 5;

	private Class<? extends AbstractPhilosopherMethod> pmclass = null;
	private Class<? extends AbstractPhilosopher> pclass = null;
	private Class<? extends AbstractChopstick> cclass = null;

	private AbstractPhilosopherMethod method;

	private CyclicBarrier barrier;

	private Properties prop;

	private AbstractChopstick[] cs;
	private AbstractPhilosopher[] ps;
	private PhilosopherThread[] pts;

	@Test
	public void test() throws Exception {
		loadProperties();
		initChopsticks();
		initPhilosophers();
		initPhilosopherMethod();
		initPhilosopherThreads();
		startPhilosopherThreads();
		joinPhilosopherThreads();
	}

	@SuppressWarnings("unchecked")
	private void loadProperties() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream("bin/com/chain/test/day04/test.properties"));
		cclass = (Class<? extends AbstractChopstick>) Class.forName((String) prop.get("chopstick.class"));
		pclass = (Class<? extends AbstractPhilosopher>) Class.forName((String) prop.get("philosopher.class"));
		pmclass = (Class<? extends AbstractPhilosopherMethod>) Class
				.forName((String) prop.get("philosopher.method.class"));
	}

	private void initPhilosopherMethod() throws Exception {
		method = pmclass.getConstructor().newInstance();
	}

	private class PhilosopherThread extends Thread {

		private AbstractPhilosopher p;

		public PhilosopherThread(AbstractPhilosopher p) {
			this.p = p;
		}

		@Override
		public void run() {
			try {
				barrier.await();
				System.out.println(p.getName() + " is seated already");
				while (true)
					method.method(p, cs);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	private void joinPhilosopherThreads() throws InterruptedException {
		for (int i = 0; i < pts.length; i++)
			pts[i].join();
	}

	private void startPhilosopherThreads() {
		for (int i = 0; i < pts.length; i++)
			pts[i].start();
	}

	private void initPhilosopherThreads() {
		barrier = new CyclicBarrier(PHILOSOPHER_NUM);
		pts = new PhilosopherThread[PHILOSOPHER_NUM];
		for (int i = 0; i < pts.length; i++)
			pts[i] = new PhilosopherThread(ps[i]);
	}

	private void initChopsticks() throws Exception {
		Constructor<? extends AbstractChopstick> constructor = cclass.getConstructor(int.class);
		cs = new AbstractChopstick[CHOPSTICK_NUM];
		for (int i = 0; i < cs.length; i++)
			cs[i] = constructor.newInstance(i);
	}

	private void initPhilosophers() throws Exception {
		Constructor<? extends AbstractPhilosopher> constructor = pclass.getConstructor(int.class);
		ps = new AbstractPhilosopher[PHILOSOPHER_NUM];
		for (int i = 0; i < ps.length; i++)
			ps[i] = constructor.newInstance(i);
	}

}
