package com.chain.test.day05;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

import com.chain.utils.FileDirectoryUtils;

/**
 * 主测试方法
 * 
 * @author chain
 *
 */
public class Main {

	private Properties prop;

	private String sharePath;
	private String shareFileName;
	private RandomAccessFile shareFile;

	private AbstractReader[] readers;
	private AbstractWriter[] writers;

	private int readersNum;
	private int writersNum;

	private ReaderWriterThread[] threads;

	private OutputStream[] os;

	private CyclicBarrier barrier;

	private AbstractReaderWriterMethod method;
	private Class<? extends AbstractReaderWriterMethod> mclass;

	@Test
	public void test() throws Exception {
		try {
			initProperties();
			initShareFile();
			initReaders();
			initWriters();
			initReaderWriterMethod();
			initReaderWriterThreads();
			startReaderWriterThreads();
			initAndStartAndJoinReaderWriterControlThread();
			joinReaderWriterThreads();
		} finally {
			closeResources();
		}
	}

	private void closeResources() {
		for (int i = 0; i < readersNum; i++)
			if (os[i] != null)
				try {
					os[i].close();
				} catch (IOException e) {
					System.out.println("stream close io exception, but do noting");
				}

		if (shareFile != null)
			try {
				shareFile.close();
			} catch (IOException e) {
				System.out.println("file close io exception, but do noting");
			}

		System.out.println("exit");
	}

	private void initAndStartAndJoinReaderWriterControlThread() throws InterruptedException {
		Thread rwct = new ReaderWriterControlThread();
		rwct.start();
		rwct.join();
	}

	private static final int RUNNING_TIME = 20000;

	private class ReaderWriterControlThread extends Thread {

		@Override
		public void run() {
			try {
				// 等待
				Thread.sleep(RUNNING_TIME);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			for (int i = 0; i < writersNum; i++)
				writers[i].stop();

			for (int i = 0; i < readersNum; i++)
				readers[i].stop();
		}

	}

	private void initReaderWriterMethod() throws Exception {
		Constructor<? extends AbstractReaderWriterMethod> constructor = mclass.getConstructor();
		method = constructor.newInstance();
	}

	private void joinReaderWriterThreads() throws InterruptedException {
		for (int i = 0; i < threads.length; i++)
			threads[i].join();
	}

	private void startReaderWriterThreads() {
		for (int i = 0; i < threads.length; i++)
			threads[i].start();
	}

	private void initReaderWriterThreads() {
		int totalNum = readersNum + writersNum;
		barrier = new CyclicBarrier(totalNum);
		threads = new ReaderWriterThread[totalNum];
		int i = 0;
		for (; i < readersNum; i++) {
			threads[i] = new ReaderWriterThread(method, false);
			threads[i].setReader(readers[i]);
		}
		for (; i < totalNum; i++) {
			threads[i] = new ReaderWriterThread(method, true);
			threads[i].setWriter(writers[i - readersNum]);
		}
	}

	private class ReaderWriterThread extends Thread {

		private AbstractReaderWriterMethod method;
		private boolean isWriter;

		private AbstractWriter writer;
		private AbstractReader reader;

		public ReaderWriterThread(AbstractReaderWriterMethod method, boolean isWriter) {
			this.method = method;
			this.isWriter = isWriter;
		}

		public void setWriter(AbstractWriter writer) {
			if (!isWriter)
				throw new RuntimeException("only for writer");
			this.writer = writer;
		}

		public void setReader(AbstractReader reader) {
			if (isWriter)
				throw new RuntimeException("only for reader");
			this.reader = reader;
		}

		@Override
		public void run() {
			try {
				barrier.await();
				if (isWriter) {
					System.out.println(writer.getName() + " is ready");
					while (!writer.isFinished())
						method.write(writer);
				} else {
					System.out.println(reader.getName() + " is ready");
					while (!reader.isFinished())
						method.read(reader);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	private void initWriters() throws FileNotFoundException {
		readers = new AbstractReader[readersNum];
		os = new OutputStream[readersNum];
		for (int i = 0; i < readersNum; i++) {
			os[i] = new BufferedOutputStream(new FileOutputStream(sharePath + File.separator + "reader_" + i + ".txt"));
			readers[i] = new Reader(i, shareFile, os[i]);
		}
	}

	private void initReaders() {
		writers = new AbstractWriter[writersNum];
		for (int i = 0; i < writersNum; i++)
			writers[i] = new Writer(i, shareFile);
	}

	private static final int SLEEP_TIME = 2000;

	private void initShareFile() throws FileNotFoundException, InterruptedException {
		FileDirectoryUtils.emptyDirectory(new File(sharePath));
		Thread.sleep(SLEEP_TIME);
		// rw保证可读可写
		shareFile = new RandomAccessFile(sharePath + File.separator + shareFileName, "rw");
	}

	@SuppressWarnings("unchecked")
	private void initProperties() throws IOException, ClassNotFoundException {
		prop = new Properties();
		prop.load(new FileInputStream("bin/com/chain/test/day05/test.properties"));
		sharePath = prop.getProperty("file.path");
		shareFileName = prop.getProperty("file.name");
		readersNum = Integer.valueOf(prop.getProperty("readers.num"));
		writersNum = Integer.valueOf(prop.getProperty("writers.num"));
		mclass = (Class<? extends AbstractReaderWriterMethod>) Class.forName(prop.getProperty("method.class"));
	}
}
