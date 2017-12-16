package com.chain.test.day05;

import java.io.OutputStream;
import java.io.RandomAccessFile;

/**
 * 读者
 * 
 * @author chain
 *
 */
public class Reader extends AbstractReader {

	private volatile boolean stop;
	private volatile boolean finished;

	public Reader(int id, RandomAccessFile file, OutputStream os) {
		super(id, file, os);
	}

	@Override
	public void stop() {
		this.stop = true;
		System.out.println(getName() + " will stop reading");
	}

	@Override
	public void begin() {
		this.stop = false;
		System.out.println(getName() + " will begin to read");
	}

	private long position;

	// 执行次数
	private long times;

	@Override
	public void read() throws Exception {
		++times;
		int sleepTime = 100 * ((int) (Math.random() * 9) + 1);
		if (stop && position == file.length()) {
			Thread.sleep(sleepTime);
			System.out.println(getName() + " has stopped reading, times " + times);
			finished = true;
			return;
		}
		int mounts = 0;
		byte[] buf = new byte[1024 * 8];
		// 读者可以同时读文件，所以会导致seek+read的并发问题
		synchronized (file) {
			file.seek(position);
			mounts = file.read(buf);
		}
		if (mounts == -1) {
			Thread.sleep(sleepTime);
			System.out.println(getName() + " read none, total  " + position + ", times " + times);
			return;
		}
		position += mounts;
		os.write(buf, 0, mounts);
		os.flush();
		Thread.sleep(sleepTime);
		System.out.println(getName() + " has read " + mounts + ", total  " + position + ", times " + times);
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

}
