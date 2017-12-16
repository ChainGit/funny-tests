package com.chain.test.day05;

/**
 * 公平算法，在读者优先的基础上
 * 
 * 使用自定义信号量实现
 * 
 * @author chain
 *
 */
public class ReaderWriterMethod05 extends AbstractReaderWriterMethod {

	// 用于记录当前读者进程在读的数量
	private volatile int count;
	// 保证count的互斥操作
	private AbstractSemaphore mutex;
	// 用于保证读者和写者能互斥的访问文件
	private AbstractSemaphore w;
	// 用于保证只有在写进程不需要写文件时读进程才去读文件
	private AbstractSemaphore q;

	public ReaderWriterMethod05() {
		q = new Semaphore01(1);
		w = new Semaphore01(1);
		mutex = new Semaphore01(1);
	}

	@Override
	public void read(AbstractReader reader) throws Exception {
		System.out.println(reader.getName() + " wait for writer to have a rest");

		// 只有在写进程不需要写文件时读进程才去读文件
		q.P();

		System.out.println(reader.getName() + " wait for reading");

		mutex.P();
		if (count == 0)
			w.P();
		++count;
		mutex.V();

		// 写进程可以尝试去写文件
		// 但是写进程仍然需要去判断rw
		q.V();

		reader.read();

		mutex.P();
		--count;
		if (count == 0)
			w.V();
		mutex.V();

		System.out.println(reader.getName() + " finish reading");

		reader.rest();
	}

	@Override
	public void write(AbstractWriter writer) throws Exception {
		System.out.println(writer.getName() + " wait for reader to have a rest");

		// 写进程在读进程无需读取时进入
		q.P();

		System.out.println(writer.getName() + " wait for writing");

		w.P();

		writer.write();

		// 读进程可以进行读操作
		q.V();

		w.V();

		System.out.println(writer.getName() + " finish writing");

		writer.rest();
	}

}
