package com.wqh.blog.service.impl;

import com.wqh.blog.domain.User;
import com.wqh.blog.repository.UserRepository;
import com.wqh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author wqh
 * @Date 2017/10/20 9:13
 * @Description: 用户Service实现类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public JpaRepository<User, String> getJpaRepository() {
        return userRepository;
    }
}
