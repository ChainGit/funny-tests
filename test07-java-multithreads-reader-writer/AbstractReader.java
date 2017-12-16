package com.chain.test.day05;

import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * 读者抽象类
 * 
 * @author chain
 *
 */
public abstract class AbstractReader {

	protected volatile RandomAccessFile file;
	protected OutputStream os;

	private int id;
	private String name;

	public AbstractReader(int id, RandomAccessFile file, OutputStream os) {
		this.id = id;
		this.name = "Reader-" + id;
		this.file = file;
		this.os = os;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * 读文件
	 * 
	 * @throws Exception
	 */
	public abstract void read() throws Exception;

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
