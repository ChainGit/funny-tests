package com.chain.test.day06;

/**
 * 材料
 * 
 * 烟草+纸，纸+胶水，胶水+烟草的组合信号量
 * 
 * @author chain
 *
 */
public class MaterialsB extends AbstractMaterials {

	public MaterialsB() {
		// 编译器会自动添加
		// super();
		init();
	}

	private void init() {
		// 烟草+纸
		list.put(0, "tobacco+paper");
		// 胶水+纸
		list.put(1, "gluewater+paper");
		// 烟草+胶水
		list.put(2, "gluewater+tobacco");
	}

	@Override
	public String get(int id) {
		return list.get(id);
	}

}
