package com.chain.javase.reflect.test;

import org.junit.Test;

import com.chain.javase.reflect.dao.NewDao;
import com.chain.javase.reflect.dao.NewDao2;
import com.chain.javase.reflect.utils.ReflectionUtils;

public class SuperGenericTypeTest {

	@Test
	public void test() {
		Class newDao = NewDao.class;
		System.out.println(ReflectionUtils.getSuperGenericTypes(newDao));

		Class newDao2 = NewDao2.class;
		System.out.println(ReflectionUtils.getSuperGenericTypes(newDao2));
	}

}
