package com.wqh.blog.annotation;

import java.lang.annotation.*;

/**
 * @author wanqh
 * @date 2017/12/25 16:38
 * @description: 当前登录用户用在方法
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentLogin {

}
