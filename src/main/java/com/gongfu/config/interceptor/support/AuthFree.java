package com.gongfu.config.interceptor.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2017年1月9日
 *
 * @向治家 yhqb
 * AuthFree.java
 * <p>
 * ＠Target里面的ElementType是用来指定Annotation类型可以用在哪一些元素上的.
 * 说明一下：TYPE(类型), FIELD(属性), METHOD(方法), PARAMETER(参数), CONSTRUCTOR(构造函数),
 * LOCAL_VARIABLE(局部变量), ANNOTATION_TYPE,PACKAGE(包),其中的TYPE(类型)是指可以用在Class,Interface,Enum和Annotation类型上.
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthFree {

}
