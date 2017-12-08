package com.wqh.blog.util;

import com.wqh.blog.domain.User;
import com.wqh.blog.service.UserService;
import com.wqh.blog.vo.LoginVO;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wanqh
 * @date 2017/11/26 14:58
 * @description:
 */
public class UserUtil {

    private static final Logger log = LoggerFactory.getLogger(UserUtil.class);

    @Autowired
    private static UserService userService;


    /**
     * 验证用户是否登录
     * @param token
     * @return
     */
    public static boolean validate(String token){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        LoginVO user = (LoginVO) session.getAttribute("user");
        if(user != null && token.equals(user.getToken())){
            return true;
        }
        return false;
    }


}
