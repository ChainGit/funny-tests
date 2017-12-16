package com.chain.test.day05;

import java.io.RandomAccessFile;
import java.util.Random;

/**
 * 写者抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractWriter {

	protected volatile RandomAccessFile file;

	protected Random rand;

	private int id;
	private String name;

	public AbstractWriter(int id, RandomAccessFile file) {
		this.id = id;
		// 减少每次的getName生产的多余String对象
		this.name = "Writer-" + id;
		this.file = file;
		this.rand = new Random();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * 写文件
	 * 
	 * @throws Exception
	 */
	public abstract void write() throws Exception;

	/**
	 * 停止读取
	 */
	public abstract void stop();

	/**
	 * 开始读取
	 */
	public abstract void begin();

	/**
	 * 是否已完成工作
	 * 
	 * @return
	 */
	public abstract boolean isFinished();

	/**
	 * 休息一会
	 * 
	 * @throws InterruptedException
	 */
	public void rest() throws InterruptedException {
		System.out.println(getName() + " have a rest");
		Thread.sleep(100 * ((int) (Math.random() * 9) + 0));
	}
}
