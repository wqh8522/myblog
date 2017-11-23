package com.wqh.blog.service.impl;

import com.wqh.blog.domain.Category;
import com.wqh.blog.repository.CategoryReponsitory;
import com.wqh.blog.service.BaseService;
import com.wqh.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @Author wqh
 * @Date 2017/10/20 15:12
 * @Description: 分类Service
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryReponsitory categoryReponsitory;

    @Override
    public JpaRepository<Category, String> getJpaRepository() {
        return this.categoryReponsitory;
    }
}
