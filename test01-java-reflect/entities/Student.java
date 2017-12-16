package com.chain.javase.reflect.entities;

import com.chain.javase.reflect.annotation.ScoreValidator;

public class Student extends Person {

	private int score;

	static {
		System.out.println("Student的静态代码块");
	}

	{
		System.out.println("Student的代码块");
	}

	public Student() {
		System.out.println("Student的无参构造器");
	}

	public Student(String name, Integer age, int score) {
		super(name, age);
		this.score = score;
		System.out.println("Student的有参构造器");
	}

	public int getScore() {
		return score;
	}

	@ScoreValidator(max = 120, min = 0)
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void show(String msg) {
		System.out.println("Student show(): " + msg);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [score=").append(score).append("]");
		builder.append(",");
		builder.append(super.toString());
		return builder.toString();
	}

}
