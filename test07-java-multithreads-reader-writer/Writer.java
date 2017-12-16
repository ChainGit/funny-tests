package com.chain.test.day05;

import java.io.ByteArrayOutputStream;
import java.io.RandomAccessFile;

/**
 * 写者
 * 
 * @author chain
 *
 */
public class Writer extends AbstractWriter {

	private volatile boolean stop;
	private volatile boolean finished;

	public Writer(int id, RandomAccessFile file) {
		super(id, file);
	}

	@Override
	public void stop() {
		this.stop = true;
		System.out.println(getName() + " will stop writing");
	}

	@Override
	public void begin() {
		this.stop = false;
		System.out.println(getName() + " will begin to write");
	}

	// 写者写入的总字符数
	private long count;

	// 执行次数
	private long times;

	@Override
	public void write() throws Exception {
		++times;
		int sleepTime = 100 * ((int) (Math.random() * 9) + 1);
		if (stop) {
			Thread.sleep(sleepTime);
			System.out.println(getName() + " has stopped writing, times " + times);
			finished = true;
			return;
		}
		int mounts = (int) (Math.random() * 9000) + 1000;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i = 0; i < mounts; i++)
			baos.write(rand.nextInt());
		baos.flush();
		// 追加写在文件的末尾
		// 因为写者进程之间是互斥的，这里不用对write加锁
		file.write(baos.toByteArray());
		count += mounts;
		Thread.sleep(sleepTime);
		System.out.println(getName() + " has writen " + mounts + ", total  " + count + ", times " + times);
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

}
