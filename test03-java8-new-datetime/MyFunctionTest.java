package com.chain.javase.test.day05;

import org.junit.Test;

/**
 * 接口默认方法的”类优先”原则 若一个接口中定义了一个默认方法，而另外一个父类或接口中 又定义了一个同名的方法时 
 * 选择父类中的方法。如果一个父类提供了具体的实现，那么 接口中具有相同名称和参数的默认方法会被忽略。 
 * 接口冲突。如果一个父接口提供一个默认方法，而另一个接 口也提供了一个具有相同名称和参数列表的方法（不管方法 是否是默认方法），那么必须覆盖该方法来解决冲突
 * 
 * @author Chain
 *
 */
public class MyFunctionTest {

	// 测试静态方法，interface中的静态方法和普通类没什么区别
	@Test
	public void test1() {
		System.out.println(MyFunction.show());
		System.out.println(MyFunction2.show());
		System.out.println(AbstractMyFunction.show());
		System.out.println();
	}

	@Test
	public void test2() {
		MyFunctionImpl mfi = new MyFunctionImpl();
		System.out.println(mfi.getInfo());
		System.out.println(mfi.show());
		mfi.print();
		System.out.println();
	}

	@Test
	public void test3() {
		MyFunctionImpl2 mfi = new MyFunctionImpl2();
		System.out.println(mfi.getInfo());
		System.out.println();
	}

	@Test
	public void test4() {
		MyFunctionImpl3 mfi = new MyFunctionImpl3();
		System.out.println(mfi.getInfo());
		System.out.println(mfi.show());
		mfi.print();
		System.out.println();
	}

	@Test
	public void test5() {
		MyFunctionImpl4 mfi = new MyFunctionImpl4();
		System.out.println(mfi.getInfo());
		System.out.println();
	}

}
