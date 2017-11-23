package com.wqh.blog.service.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.wqh.blog.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * @Author wqh
 * @Date 2017/10/19 17:26
 * @Description: 公用的service接口实现抽象类，
 */
public abstract class BaseServiceImpl <T> implements BaseService<T> {


    public abstract JpaRepository<T,String> getJpaRepository();

    @Transactional
    @Override
    public T save(T entity) {
        return getJpaRepository().save(entity);
    }

    @Override
    public List<T> findAll() {
        return getJpaRepository().findAll();
    }

    @Override
    public T findOne(String id) {
        return getJpaRepository().findOne(id);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return this.getJpaRepository().findAll(pageable);
    }

    @Override
    public void delete(String Id) {
        this.getJpaRepository().delete(Id);
    }
}
