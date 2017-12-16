package com.chain.test.day05;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 读者优先算法
 * 
 * 使用ReentrantLock实现
 * 
 * @author chain
 *
 */
public class ReaderWriterMethod03 extends AbstractReaderWriterMethod {

	// 用于记录当前读者进程在读的数量
	private volatile int count;

	// 保证count的互斥操作
	private ReentrantLock countLock;

	// 用于保证读者和写者能互斥的访问文件
	private ReentrantLock rwLock;

	public ReaderWriterMethod03() {
		countLock = new ReentrantLock();
		rwLock = new ReentrantLock();
	}

	@Override
	public void read(AbstractReader reader) throws Exception {
		System.out.println(reader.getName() + " wait for reading");

		// 读者进程互斥访问count
		countLock.lock();
		try {
			if (count == 0)
				// 阻止写者进程写
				rwLock.lock();
			// 在执行读操作的读者进程+1
			++count;
		} finally {
			if (countLock.isLocked())
				countLock.unlock();
		}

		reader.read();

		countLock.lock();
		try {
			--count;
			if (count == 0)
				// 允许写者进程写
				if (rwLock.isHeldByCurrentThread() && rwLock.isLocked())
					rwLock.unlock();
		} finally {
			if (countLock.isLocked())
				countLock.unlock();
		}

		System.out.println(reader.getName() + " finish reading");

		reader.rest();
	}

	@Override
	public void write(AbstractWriter writer) throws Exception {
		System.out.println(writer.getName() + " wait for writing");
		// 阻止读者进程读
		rwLock.lock();
		try {
			writer.write();
		} finally {
			// 允许读者进程读
			if (rwLock.isHeldByCurrentThread() && rwLock.isLocked())
				rwLock.unlock();
		}
		System.out.println(writer.getName() + " finish writing");

		writer.rest();
	}

}
