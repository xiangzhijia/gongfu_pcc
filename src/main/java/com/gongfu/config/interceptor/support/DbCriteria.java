package com.gongfu.config.interceptor.support;


import com.gongfu.config.interceptor.support.enums.MethodType;

import java.lang.annotation.*;

/**
 * Created by feng on 2017/2/27.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbCriteria {
    MethodType method();
}
