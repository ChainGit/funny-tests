package com.chain.test.day06;

/**
 * 吸烟者抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractSmoker {

	private int id;

	private String name;

	protected AbstractSemaphore[] semaphores;
	protected AbstractSemaphore finish;
	protected AbstractMaterials materials;

	public AbstractSmoker(int id, AbstractSemaphore[] semaphores, AbstractSemaphore finish,
			AbstractMaterials materials) {
		this.id = id;
		this.name = "Smoker-" + id;
		this.semaphores = semaphores;
		this.finish = finish;
		this.materials = materials;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * 吸烟者等待材料
	 * 
	 * @throws Exception
	 */
	public abstract void take() throws Exception;

}
