package com.chain.test.day05;

import java.util.concurrent.Semaphore;

/**
 * 读者优先算法
 * 
 * 使用JavaAPI信号量做法
 * 
 * @author chain
 *
 */
public class ReaderWriterMethod02 extends AbstractReaderWriterMethod {

	// 用于记录当前读者进程在读的数量
	private volatile int count;
	// 保证count的互斥操作
	private Semaphore mutex;
	// 用于保证读者和写者能互斥的访问文件
	private Semaphore rw;

	public ReaderWriterMethod02() {
		rw = new Semaphore(1);
		mutex = new Semaphore(1);
	}

	@Override
	public void read(AbstractReader reader) throws Exception {
		System.out.println(reader.getName() + " wait for reading");

		// 读者进程互斥访问count
		mutex.acquire();
		if (count == 0)
			// 阻止写者进程写
			rw.acquire();
		// 在执行读操作的读者进程+1
		++count;
		mutex.release();

		reader.read();

		mutex.acquire();
		--count;
		if (count == 0)
			// 允许写者进程写
			rw.release();
		mutex.release();

		System.out.println(reader.getName() + " finish reading");

		reader.rest();
	}

	@Override
	public void write(AbstractWriter writer) throws Exception {
		System.out.println(writer.getName() + " wait for writing");
		// 阻止读者进程读
		rw.acquire();
		writer.write();
		// 允许读者进程读
		rw.release();
		System.out.println(writer.getName() + " finish writing");

		writer.rest();
	}

}
