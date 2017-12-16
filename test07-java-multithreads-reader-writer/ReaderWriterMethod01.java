package com.chain.test.day05;

/**
 * 读者优先算法
 * 
 * 自定义信号量做法
 * 
 * @author chain
 *
 */
public class ReaderWriterMethod01 extends AbstractReaderWriterMethod {

	// 用于记录当前读者进程在读的数量
	private volatile int count;
	// 保证count的互斥操作
	private AbstractSemaphore mutex;
	// 用于保证读者和写者能互斥的访问文件
	private AbstractSemaphore rw;

	public ReaderWriterMethod01() {
		rw = new Semaphore01(1);
		mutex = new Semaphore01(1);
	}

	@Override
	public void read(AbstractReader reader) throws Exception {
		System.out.println(reader.getName() + " wait for reading");

		// 读者进程互斥访问count
		mutex.P();
		if (count == 0)
			// 阻止写者进程写
			rw.P();
		// 在执行读操作的读者进程+1
		++count;
		mutex.V();

		reader.read();

		mutex.P();
		--count;
		if (count == 0)
			// 允许写者进程写
			rw.V();
		mutex.V();

		System.out.println(reader.getName() + " finish reading");

		reader.rest();
	}

	@Override
	public void write(AbstractWriter writer) throws Exception {
		System.out.println(writer.getName() + " wait for writing");

		// 阻止读者进程读
		rw.P();
		writer.write();
		// 允许读者进程读
		rw.V();

		System.out.println(writer.getName() + " finish writing");

		writer.rest();
	}

}
