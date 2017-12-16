package com.chain.test.day06;

/**
 * 吸烟者
 * 
 * 烟草+纸，纸+胶水，胶水+烟草的组合信号量
 * 
 * @author chain
 *
 */
public class SmokerB extends AbstractSmoker {

	private AbstractSemaphore take;

	public SmokerB(int id, AbstractSemaphore[] semaphores, AbstractSemaphore finish, AbstractMaterials materials) {
		super(id, semaphores, finish, materials);
		take = semaphores[id];
	}

	@Override
	public void take() throws Exception {
		String material = materials.get(getId());

		System.out.println(getName() + " wait for material " + material);

		// 等待供应者的材料
		take.P();

		System.out.println(getName() + " get material " + material);

		// 告知供应者自己的到所需要的材料
		finish.V();

		System.out.println(getName() + " signal supplier");

		process();
	}

	/**
	 * 卷烟和吸烟
	 */
	private void process() {
		try {
			Thread.sleep(100 * ((int) (Math.random() * 9 + 1)));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(getName() + " cigarette and smoke");
	}

}
