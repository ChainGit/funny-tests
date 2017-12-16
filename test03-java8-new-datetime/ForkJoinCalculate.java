package com.chain.javase.test.day05;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long start;
	private long end;

	private static final long THRESHOLD = 10000L;

	public ForkJoinCalculate(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long length = end - start;
		if (length < THRESHOLD) {
			long sum = 0;
			for (long i = start; i <= end; i++)
				sum += i;
			return sum;
		} else {
			long mid = (start + end) >> 1;
			ForkJoinCalculate left = new ForkJoinCalculate(start, mid);
			ForkJoinCalculate right = new ForkJoinCalculate(mid + 1, end);

			left.fork();
			right.fork();

			return left.join() + right.join();
		}
	}

}
