package com.wqh.blog.service;

import com.wqh.blog.domain.BaseEntity;
import com.wqh.blog.domain.Page;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author wqh
 * @Date 2017/10/19 17:25
 * @Description: 公用的service接口
 */
public abstract class BaseService<M extends BaseMapper<T>, T extends BaseEntity> {


    /**
     * 持久层对象
     */
    @Autowired
    protected M mapper;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id) {
        T t = mapper.get(id);
        return t;
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return mapper.getByEntity(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return mapper.findList(entity);
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(mapper.findList(entity));
        return page;
    }

    /**
     * 保存数据
     * @param entity
     */
    @Transactional(rollbackFor = BusinessException.class)
    public T save(T entity) {
        mapper.insert(entity);
        return entity;
    }

    /**
     * 更新数据
     * @param entity
     */
    @Transactional(rollbackFor = BusinessException.class)
    public void update(T entity) {
        mapper.update(entity);
    }
    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false,rollbackFor = BusinessException.class)
    public void delete(T entity) {
        mapper.delete(entity);
    }


    /**
     * 删除全部数据
     * @param entitys
     */
    @Transactional(rollbackFor = BusinessException.class)
    public void deleteAll(Collection<T> entitys) {
        for (T entity : entitys) {
            mapper.delete(entity);
        }
    }

    /**
     * 删除全部数据
     * @param entitys
     */
    @Transactional(readOnly = false,rollbackFor = BusinessException.class)
    public void deleteAllByLogic(Collection<T> entitys) {
        for (T entity : entitys) {
            mapper.deleteByLogic(entity);
        }
    }


    /**
     * 获取单条数据
     * @param propertyName, value
     * @return
     */
    public T findUniqueByProperty(String propertyName, Object value){
        return mapper.findUniqueByProperty(propertyName, value);
    }

    /**
     * 动态sql
     * @param sql
     */

    public List<Object> executeSelectSql(String sql){
        return mapper.execSelectSql(sql);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void executeInsertSql(String sql){
        mapper.execInsertSql(sql);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void executeUpdateSql(String sql){
        mapper.execUpdateSql(sql);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public void executeDeleteSql(String sql){
        mapper.execDeleteSql(sql);
    }
}
