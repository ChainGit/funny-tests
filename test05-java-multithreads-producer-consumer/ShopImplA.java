package com.chain.blog.test.day02;

/**
 * 不使用任何多线程机制
 * 
 * 存在<br>
 * 1、内存可见性<br>
 * 2、库存可能爆仓货、库存可能为负<br>
 * 3、当库存已满时，生产者继续放货，可能出现商品生产丢失的情况<br>
 * 4、当库存不足时，消费者会继续尝试不断的买货，造成没有意义的购买尝试<br>
 * 的情况<br>
 * 
 * @author Chain
 *
 */
public class ShopImplA implements Shop {

	private int product;
	// private volatile int product;

	public int inTimes;
	public int outTimes;

	@Override
	public boolean in() {
		if (now() >= Shop.MAX) {
			System.out.println("shop is full");
			return false;
		}
		product++;
		inTimes++;
		System.out.println("after in, shop left is " + now());
		return true;
	}

	@Override
	public boolean out() {
		if (now() <= Shop.MIN) {
			System.out.println("shop is empty");
			return false;
		}
		product--;
		outTimes++;
		System.out.println("after out, shop left is " + now());
		return true;
	}

	@Override
	public int now() {
		return product;
	}

}
