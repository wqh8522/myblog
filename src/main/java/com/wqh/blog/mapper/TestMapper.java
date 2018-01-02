package com.wqh.blog.mapper;

import com.wqh.blog.annotation.MyBatisMapper;
import com.wqh.blog.domain.Test;

/**
 * @author wanqh
 * @date 2017/12/26 14:52
 * @description:
 */
@MyBatisMapper
public interface TestMapper {

    /**
     * 根据id查询
     * @param id
     * @return
     */
     Test get(String id);

    /**
     * 插入数据
     * @param entity
     * @return
     */
     int insert(Test entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
     int update(Test entity);
}
