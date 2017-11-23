package com.wqh.blog.service;

import com.wqh.blog.BlogApplication;
import com.wqh.blog.domain.User;
import com.wqh.blog.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @author wanqh
 * @date 2017/11/23 21:31
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userMapper;
    @Test
    public void save() throws Exception {
        User user = new User();
        user.setPhone("1233");
        user.setPassword("123");
        user.setUsername("wqh");
        userMapper.save(user);
    }

}