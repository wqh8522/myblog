package com.wqh.blog.service;


import com.wqh.blog.domain.Article;
import com.wqh.blog.mapper.ArticleMapper;
import com.wqh.blog.util.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wanqh
 * @date 2017/11/26 17:15
 * @description:
 */
@Service
public class ArticleService extends BaseService<ArticleMapper,Article> {

    @Override
    public void save(Article entity) {
        entity.setId(Constants.getID());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        super.save(entity);
}

    @Override
    public void update(Article entity) {
        entity.setUpdateTime(new Date());
        super.update(entity);
    }
}
