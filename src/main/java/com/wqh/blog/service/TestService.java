package com.wqh.blog.service;

import com.wqh.blog.domain.Test;
import com.wqh.blog.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wanqh
 * @date 2017/12/26 14:57
 * @description:
 */
@Service
@CacheConfig(cacheNames = "test")
public class TestService {

    @Autowired
    private TestMapper testMapper;

    @Cacheable(key = "#p0")
    public Test get(String id) {
        return testMapper.get(id);
    }

    @CachePut(key = "#p0.id")
    public Test insert(Test test) {
        testMapper.insert(test);
        return test;
    }

    @CachePut(key = "#p0.id")
    public Test update(Test test) {
        testMapper.update(test);
        return test;
    }

}
