package com.chain.test.day04;

/**
 * 每个哲学家先拿起左叉子，再拿起右叉子
 * 
 * @author chain
 *
 */
public class PhilosopherMethod01 extends AbstractPhilosopherMethod {

	@Override
	public void method(AbstractPhilosopher p, AbstractChopstick[] cs) throws Exception {
		int left = p.getId();
		int right = (left + 1) % Main.CHOPSTICK_NUM;

		p.take(cs[left]);
		p.take(cs[right]);

		p.eat();

		p.put(cs[left]);
		p.put(cs[right]);

		p.think();
	}

}
