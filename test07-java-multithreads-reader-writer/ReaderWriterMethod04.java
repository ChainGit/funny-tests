package com.chain.test.day05;

/**
 * 写者优先
 * 
 * 自定义信号量做法
 * 
 * @author chain
 *
 */
public class ReaderWriterMethod04 extends AbstractReaderWriterMethod {

	// 用于记录当前读者进程在读的数量
	private volatile int readCount;
	// 保证readCount的互斥操作
	private AbstractSemaphore readMutex;

	// 用于记录当前写者进程在写的数量
	private volatile int writeCount;
	// 保证writeCount的互斥操作
	private AbstractSemaphore writeMutex;

	// 用于保证读者和写者能互斥的访问文件
	private AbstractSemaphore w;

	private AbstractSemaphore q;

	public ReaderWriterMethod04() {
		w = new Semaphore01(1);
		q = new Semaphore01(1);
		writeMutex = new Semaphore01(1);
		readMutex = new Semaphore01(1);
	}

	@Override
	public void read(AbstractReader reader) throws Exception {
		System.out.println(reader.getName() + " wait for reading");

		// 只有当没有写进程在执行时，才能进行读操作
		q.P();

		readMutex.P();
		if (readCount == 0)
			w.P();
		++readCount;
		readMutex.V();

		q.V();

		reader.read();

		readMutex.P();
		--readCount;
		if (readCount == 0)
			w.V();
		readMutex.V();

		System.out.println(reader.getName() + " finish reading");

		reader.rest();
	}

	@Override
	public void write(AbstractWriter writer) throws Exception {
		System.out.println(writer.getName() + " wait for writing");

		writeMutex.P();
		if (writeCount == 0)
			q.P();
		++writeCount;
		writeMutex.V();

		w.P();
		writer.write();
		w.V();

		writeMutex.P();
		--writeCount;
		if (writeCount == 0)
			// 没有要写操作的写者，读者可以读文件
			q.V();
		writeMutex.V();

		System.out.println(writer.getName() + " finish writing");

		writer.rest();
	}

}
