package com.chain.javase.test.day05;

public class MyFunctionImpl4 implements MyFunction<Integer, Integer>, MyFunction2<Integer, Integer> {

	@Override
	public Integer apply(Integer t) {

		return null;
	}

	@Override
	public String getInfo() {
		// 选择调用接口的默认方法，只能调用一个
		return MyFunction.super.getInfo();
		// return MyFunction2.super.getInfo();
	}

}
