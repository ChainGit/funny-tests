package com.chain.javase.test.day05;

import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

/**
 * 包含传统的Fork-Join框架，以及到了Java8的简化版Fork-Join
 * 
 * Fork-Join采用分治思想
 * 
 * 测试：计算0...100，0000，0000的累加和
 * 
 * 注意：底层的for循环比较快；win10对多线程做了大幅优化，win7比较明显
 * 
 * @author Chain
 *
 */
public class ForkJoinTest {

	private static long start = 0;
	private static long end = 1000_0000_0000L;

	// 单线程做法
	@Test
	public void test1() {
		Instant begin = Instant.now();
		long sum = 0;
		// 结果肯定会溢出，这里只是比较运算速度
		for (long i = start; i <= end; i++)
			sum += i;
		System.out.println(sum);
		Instant exit = Instant.now();
		System.out.println("耗时：" + (exit.getEpochSecond() - begin.getEpochSecond()));
	}

	// 使用Fork-Join
	@Test
	public void test2() {
		Instant begin = Instant.now();
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(start, end);
		long sum = pool.invoke(task);
		System.out.println(sum);
		Instant exit = Instant.now();
		System.out.println("耗时：" + (exit.getEpochSecond() - begin.getEpochSecond()));
	}

	// 使用并行流，CPU利用率几乎100%
	@Test
	public void test3() {
		Instant begin = Instant.now();
		long sum = LongStream.rangeClosed(start, end).parallel().sum();
		System.out.println(sum);
		Instant exit = Instant.now();
		System.out.println("耗时：" + (exit.getEpochSecond() - begin.getEpochSecond()));

	}
}
