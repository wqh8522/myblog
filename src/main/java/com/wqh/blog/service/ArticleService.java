package com.wqh.blog.service;


import com.wqh.blog.domain.Article;
import com.wqh.blog.mapper.ArticleMapper;
import com.wqh.blog.util.Constants;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wanqh
 * @date 2017/11/26 17:15
 * @description: 文章service
 */
@Service
@CacheConfig(cacheNames = "article")
public class ArticleService extends BaseService<ArticleMapper, Article> {

    @Override
    @CachePut(key = "#p0.id")
    public Article save(Article entity) {
        entity.setLookCount(0);
        entity.setLikeCount(0);
        entity.setCommentCount(0);
        entity.setId(Constants.getID());
        entity = super.save(entity);
        return entity;
    }

    @Override
    @Cacheable(key = "#p0")
    public Article get(String id) {
        return super.get(id);
    }
}
