package org.interestTeam.model.models;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Documented 
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyAnnotation {
	String desc() default "无信息";
}
