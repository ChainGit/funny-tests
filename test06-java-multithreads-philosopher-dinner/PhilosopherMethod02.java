package com.chain.test.day04;

/**
 * 同时只允许一位哲学家就餐
 * 
 * @author chain
 *
 */
public class PhilosopherMethod02 extends AbstractPhilosopherMethod {

	@Override
	public void method(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		int left = p.getId();
		int right = (left + 1) % Main.CHOPSTICK_NUM;

		synchronized (cs) {
			p.take(cs[left]);
			p.take(cs[right]);
		}

		p.eat();

		p.put(cs[left]);
		p.put(cs[right]);

		p.think();
	}

}
