package com.chain.javase.test.day05;

//类优先
public class MyFunctionImpl2 implements MyFunction<Integer, Integer> {

	@Override
	public Integer apply(Integer t) {
		return t + 1;
	}

	// getInfo的类优先原则

}
