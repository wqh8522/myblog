package com.wqh.blog.service;

import com.wqh.blog.domain.User;
import com.wqh.blog.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wqh
 * @Date 2017/10/19 17:23
 * @Description: 用户 Service
 */
@Service
public class UserService extends BaseService<UserMapper,User> {
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
}
