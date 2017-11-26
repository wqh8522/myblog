package com.wqh.blog.util;

import com.wqh.blog.domain.User;
import com.wqh.blog.vo.LoginVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wanqh
 * @date 2017/11/26 14:58
 * @description:
 */
public class UserUtil {

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
