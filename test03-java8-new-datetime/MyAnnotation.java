package com.chain.javase.test.day05;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(MyAnnotations.class)
@Target(value = { ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	public String value() default "NULL";

}
