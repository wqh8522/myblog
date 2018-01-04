package com.wqh.blog.service;

import com.wqh.blog.domain.Category;
import com.wqh.blog.mapper.CategoryMapper;
import com.wqh.blog.util.Constants;
import com.wqh.blog.util.DateUtils;
import org.springframework.stereotype.Service;

/**
 * @author wanqh
 * @date 2017/12/04 18:45
 * @description: 分类信息service
 */
@Service
public class CategoryService extends BaseService<CategoryMapper,Category>{


    @Override
    public void save(Category entity) {
        entity.setId(DateUtils.getMillis());
        super.save(entity);
    }
}
