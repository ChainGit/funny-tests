package com.chain.javase.test.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * Lambda测试1：
 * 
 * 一、Lambda 表达式的基础语法：<br/>
 * Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符<br/>
 * 箭头操作符将 Lambda 表达式拆分成两部分：
 * 
 * 左侧：Lambda 表达式的参数列表 <br/>
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 * 
 * 语法格式一：<br/>
 * 无参数，无返回值 <br/>
 * () -> System.out.println("Hello Lambda!");
 * 
 * 语法格式二：<br/>
 * 有一个参数，并且无返回值 <br/>
 * (x) -> System.out.println(x)
 * 
 * 语法格式三：<br/>
 * 若只有一个参数，小括号可以省略不写 <br/>
 * x -> System.out.println(x)
 * 
 * 语法格式四：<br/>
 * 有两个以上的参数，有返回值，并且 Lambda 体中有多条语句 <br/>
 * Comparator<Integer> com = (x, y) -> { System.out.println("函数式接口"); return
 * Integer.compare(x, y); };
 *
 * 语法格式五：<br/>
 * 若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写 <br/>
 * Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 * 
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写。<br/>
 * 因为JVM编译器通过上下文推断出，数据类型，即<b>“类型推断”</b> <br/>
 * (Integer x, Integer y) -> Integer.compare(x, y);
 * 
 * 上联：左右遇一括号省<br/>
 * 下联：左侧推断类型省 <br/>
 * 横批：能省则省
 * 
 * 二、Lambda 表达式需要“函数式接口”的支持<br/>
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 <br/>
 * 可以使用注解 @FunctionalInterface 修饰 可以检查是否是函数式接口
 * 
 * @author Chain
 *
 */
public class LambdaTest {

	@Test
	public void test1() {
		Runnable runnable1 = new Runnable() {

			@Override
			public void run() {
				System.out.println("lambda1");
			}
		};

		new Thread(runnable1).start();

		Runnable runnable2 = () -> System.out.println("lambda2");
		new Thread(runnable2).start();
	}

	@Test
	public void test2() {
		// Consumer是Lambda的内置接口
		Consumer<String> consumer = x -> System.out.println(x);
		consumer.accept("lambda3");
	}

	@Test
	public void test3() {
		List<String> lst = new ArrayList<>();
		lst.add("aaa");
		lst.add("123");
		lst.sort((e1, e2) -> {
			System.out.println("lambda4");
			return e1.compareTo(e2);
		});
		System.out.println(lst);
	}

	@Test
	public void test4() {
		List<String> lst = new ArrayList<>();
		lst.add("aaa");
		lst.add("123");
		lst.sort((e1, e2) -> e1.compareTo(e2));
		System.out.println(lst);
	}

	@Test
	public void test5() {
		System.out.println(method1(3, x -> x * x));
	}

	// 策略设计模式
	public Integer method1(Integer x, MyFunction<Integer, Integer> f) {
		return f.apply(x);
	}

}
