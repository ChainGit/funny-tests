package com.chain.test.day06;

/**
 * 吸烟者
 * 
 * 烟草、纸、胶水的单独信号量
 * 
 * @author chain
 *
 */
public class SmokerA extends AbstractSmoker {

	private AbstractSemaphore[] takes;

	public SmokerA(int id, AbstractSemaphore[] semaphores, AbstractSemaphore finish, AbstractMaterials materials) {
		super(id, semaphores, finish, materials);
		takes = semaphores;
	}

	@Override
	public void take() throws Exception {
		int mlen = takes.length;
		int notTake = getId();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mlen; i++) {
			if (i == notTake)
				continue;
			sb.append(materials.get(i) + " ");
		}

		String msg = sb.toString();

		// 等待供应者的材料
		System.out.println(getName() + " wait for materials： " + msg);

		for (int i = 0; i < mlen; i++) {
			if (i == notTake)
				continue;
			takes[i].P();
		}

		System.out.println(getName() + " get materials： " + msg);

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
