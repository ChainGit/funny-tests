package com.chain.test.day06;

/**
 * 供应者抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractSupplier {

	private int id;

	private String name;

	protected AbstractSemaphore[] semaphores;
	protected AbstractSemaphore finish;
	protected AbstractMaterials materials;

	public AbstractSupplier(int id, AbstractSemaphore[] semaphores, AbstractSemaphore finish,
			AbstractMaterials materials) {
		this.id = id;
		this.name = "Supplier-" + id;
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
	 * 供应者提供材料
	 * 
	 * @throws Exception
	 */
	public abstract void put() throws Exception;

}
