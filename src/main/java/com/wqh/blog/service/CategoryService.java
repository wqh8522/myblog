package com.wqh.blog.service;

import com.wqh.blog.domain.Category;
import com.wqh.blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wanqh
 * @date 2017/12/04 18:45
 * @description: 分类信息service
 */
@Service
public class CategoryService extends BaseService<CategoryMapper,Category>{

    @Override
    public void save(Category entity) {
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        super.save(entity);
    }

    @Override
    public void update(Category entity) {
        entity.setUpdateTime(new Date());
        super.update(entity);
    }


}
