package com.wqh.blog.service.impl;

import com.wqh.blog.enums.RoleEnum;
import com.wqh.blog.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wqh
 * @Date 2017/10/20 9:16
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void save() throws Exception {

        User user = new User();
//        user.setId("40287f815f38f987015f38f996910000");
        user.setUsername("wanqinghua");
        user.setPassword("123456");
        user.setRole(RoleEnum.ROLE_USER);
        user.setPhone("15770640991");
        user.setEmail("15770640991@163.com");
        userService.save(user);


    }

}