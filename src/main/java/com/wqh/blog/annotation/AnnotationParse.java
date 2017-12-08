package com.wqh.blog.annotation;

import java.lang.reflect.Method;

/**
 * @author wanqh
 * @date 2017/12/08 10:41
 * @description: 注解解析类
 */
public class AnnotationParse {

    /***
     * 解析权限注解
     * @param targetClass 目标类的class形式
     * @param methodName 所调用的方法
     * @return
     * @throws Exception
     */
    public static String privilegeParse(Class targetClass,String methodName) throws Exception {
        //获取该方法
        Method method = targetClass.getMethod(methodName);
        if(method.isAnnotationPresent(Permission.class)){
            Permission annotation = method.getAnnotation(Permission.class);
            return annotation.authorities();
        }
        return null;
    }
    /***
     * 解析权限注解
     * @return
     * @throws Exception
     */
    public static String privilegeParse(Method method) throws Exception {
        //获取该方法
        if(method.isAnnotationPresent(Permission.class)){
            Permission annotation = method.getAnnotation(Permission.class);
            return annotation.authorities();
        }
        return null;
    }
}
