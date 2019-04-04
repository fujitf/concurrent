package com.concurrent.demo.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author：fangmeixiu
 * @Description:
 * @Date :create in 下午3:41 2019/3/31
 */
/**
 *
 * 用来标记线程不安全的类或者写法
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {
    String value() default "";
}
