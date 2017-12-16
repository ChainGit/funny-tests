package com.chain.blog.test.day02;

/**
 * 商店接口
 * 
 * @author Chain
 *
 */
public interface Shop {

	/**
	 * 最大的商品库存数
	 */
	public static final int MAX = 10;

	/**
	 * 最小的商品库存数
	 */
	public static final int MIN = 0;

	/**
	 * 查找商品的库存
	 * 
	 * @return
	 */
	public int now();

	/**
	 * 商店进货
	 */
	public boolean in();

	/**
	 * 商店卖货
	 */
	public boolean out();
}
