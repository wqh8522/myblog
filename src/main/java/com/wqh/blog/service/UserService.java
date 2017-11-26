package com.wqh.blog.service;

import com.wqh.blog.domain.User;
import com.wqh.blog.mapper.UserMapper;
import com.wqh.blog.util.Constants;
import com.wqh.blog.util.WebUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wqh
 * @Date 2017/10/19 17:23
 * @Description: 用户 Service
 */
@Service
public class UserService extends BaseService<UserMapper,User> {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public void save(User entity) {
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        super.save(entity);
    }

    @Override
    public void update(User entity) {
        entity.setUpdateTime(new Date());
        super.update(entity);
    }

    /**
     * 获取当前用户
     * @return
     */
    public User getCurrentUser(){
        HttpServletRequest request = WebUtil.getRequest();
        Claims claims = (Claims) request.getAttribute(Constants.CLAIMS);
        User user = new User();
        if(claims != null){
            String username = claims.getSubject();
            log.info(username);
            user.setUsername(username);
            user = super.get(user);
        }
        return  user;
    }
}
