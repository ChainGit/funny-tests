package com.chain.javase.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsTest {

	@Test
	public void test() throws Exception {

		List<Score> scores = new ArrayList<>();
		scores.add(new Score("数学", 90));
		scores.add(new Score("语文", 86));
		scores.add(new Score("英语", 78));

		Student student1 = new Student(1, "Jack", 22, scores);
		System.out.println(student1);

		Student student2 = new Student();
		BeanUtils.copyProperties(student2, student1);
		System.out.println(student2);

		Student student3 = (Student) SerializeUtils.copy(student1);
		System.out.println(student3);

		Map<String, Object> objmap = MapObjectConvertUtils.objectToMap(student1);
		// System.out.println(objmap);
		Student student4 = MapObjectConvertUtils.mapToObject(objmap, Student.class, null);
		System.out.println(student4);

		System.out.println("objects' hashcode: ");
		System.out.println(student1.hashCode());
		System.out.println(student2.hashCode());
		System.out.println(student3.hashCode());
		System.out.println(student4.hashCode());
		System.out.println("objects' field-object hashcode: ");
		System.out.println(student1.getScores().hashCode());
		System.out.println(student2.getScores().hashCode());
		System.out.println(student3.getScores().hashCode());
		System.out.println(student4.getScores().hashCode());

		student1.getScores().set(0, new Score("地理", 81));
		student2.getScores().set(0, new Score("地理", 82));
		student3.getScores().set(0, new Score("地理", 83));
		student4.getScores().set(0, new Score("地理", 84));

		System.out.println(student1);
		System.out.println(student2);
		System.out.println(student3);
		System.out.println(student4);
	}

}
