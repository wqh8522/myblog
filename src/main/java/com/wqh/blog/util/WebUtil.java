package com.wqh.blog.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wanqh
 * @date 2017/11/26 18:11
 * @description: web工具类
 */
public class WebUtil {
    /**
     * 获取request对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return  request;
    }

    /**
     * 获取 session
     * @return
     */
    public static HttpSession getSession(){
        return  getRequest().getSession();
    }
}
