package com.chain.javase.test.day05;

public class DateTimeOldSolve implements Runnable {

	private String time;
	private DateTimeOldThreadLocal tl = new DateTimeOldThreadLocal();

	@Override
	public void run() {
		try {
			tl.parse(time);
		} catch (Exception e) {
			System.out.println("转换错误");
			// e.printStackTrace();
		}
	}

	public DateTimeOldSolve(String time) {
		super();
		this.time = time;
	}

}
