package com.wqh.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wqh
 * @Date 2017/10/19 17:25
 * @Description: 公用的service接口
 */
public interface BaseService<T> {

    /**
     * 保存和更新对象
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

    /**
     * 根据id查询
     * @param val
     * @return
     */
    T findOne(String val);

    /**
     * 分页查询所有
     * @param var1
     * @return
     */
    Page<T> findAll(Pageable var1);

    /**
     * 根据id删除
     * @param Id
     */
    void delete(String Id);

}
