package com.chain.javase.test.day05;

//类中的静态方法不会被继承，接口也是一样的道理
public class MyFunctionImpl3 extends AbstractMyFunction
		implements MyFunction<Integer, Integer>, MyFunction2<Integer, Integer> {

	@Override
	public Integer apply(Integer t) {
		return t + 1;
	}

	@Override
	public String getInfo() {
		// 可以复写实现抽象类的实现
		// return "123456";
		// 也可以选择调用接口的默认方法
		return MyFunction.super.getInfo();
		// return MyFunction2.super.getInfo();
	}

}
