package com.wqh.blog.annotation;

import java.lang.annotation.*;

/**
 * @author wanqh
 * @date 2017/12/08 10:00
 * @description: 权限注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

      String authorities() default "ADMIN";

}
