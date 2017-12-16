package com.chain.javase.test;

import java.util.List;

public class Student extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Score> scores;

	public Student() {
	}

	public Student(int id, String name, int age, List<Score> scores) {
		super(id, name, age);
		this.scores = scores;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [score=").append(scores).append("] ");
		builder.append(super.toString());
		return builder.toString();
	}

}
