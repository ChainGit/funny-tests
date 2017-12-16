package com.chain.javase.reflect.dao;

import com.chain.javase.reflect.entities.Person;

public class NewDao extends BaseDao<Person> {

	@Override
	public void show() {
		System.out.println("PersonDao show()");
	}

}
