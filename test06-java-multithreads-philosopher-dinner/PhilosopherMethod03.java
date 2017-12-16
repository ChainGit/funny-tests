package com.chain.test.day04;

/**
 * 哲学家分成奇偶
 * 
 * @author chain
 *
 */
public class PhilosopherMethod03 extends AbstractPhilosopherMethod {

	@Override
	public void method(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		int id = p.getId();
		int left = id;
		int right = (left + 1) % Main.CHOPSTICK_NUM;

		if ((id & 1) == 1) {
			p.take(cs[left]);
			p.take(cs[right]);
		} else {
			p.take(cs[right]);
			p.take(cs[left]);
		}

		p.eat();

		p.put(cs[left]);
		p.put(cs[right]);

		p.think();
	}

}
