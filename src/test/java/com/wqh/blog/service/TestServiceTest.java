package com.wqh.blog.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author wanqh
 * @date 2017/12/26 15:08
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceTest.class);
    @Autowired
    private TestService testService;

    @Test
    public void get() {
        com.wqh.blog.domain.Test test = testService.get("d8e875c8-9425-485b-9665-f5dda1e788bf");
        logger.info("======"+test+"=============");
    }

    @Test
    public void insert() {
        com.wqh.blog.domain.Test test = new com.wqh.blog.domain.Test();
        test.setId(UUID.randomUUID().toString());
        test.setName("redis");
        test.setRemake("hhhhhhhhhhhhhhhhhhhhhhh");
        testService.insert(test);
        Assert.assertEquals("redis",testService.get(test.getId()).getName());

    }

    @Test
    public void update() {
    }
}