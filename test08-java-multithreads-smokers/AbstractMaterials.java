package com.chain.test.day06;

import java.util.HashMap;
import java.util.Map;

/**
 * 材料的抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractMaterials {

	protected Map<Integer, String> list;

	public AbstractMaterials() {
		list = new HashMap<>();
	}

	/**
	 * 获取材料的名称
	 * 
	 * @param id
	 * @return
	 */
	public abstract String get(int id);

}
