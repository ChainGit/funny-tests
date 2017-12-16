package com.chain.test.day06;

/**
 * 材料
 * 
 * 烟草、纸、胶水的单独信号量
 * 
 * @author chain
 *
 */
public class MaterialsA extends AbstractMaterials {

	public MaterialsA() {
		// 编译器会自动添加
		// super();
		init();
	}

	private void init() {
		// 烟草
		list.put(0, "tobacco");
		// 胶水
		list.put(1, "gluewater");
		// 纸
		list.put(2, "paper");
	}

	@Override
	public String get(int id) {
		return list.get(id);
	}

}
