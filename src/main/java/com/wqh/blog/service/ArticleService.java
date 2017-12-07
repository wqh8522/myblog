package com.wqh.blog.service;


import com.wqh.blog.domain.Article;
import com.wqh.blog.mapper.ArticleMapper;
import com.wqh.blog.util.Constants;
import org.springframework.stereotype.Service;

/**
 * @author wanqh
 * @date 2017/11/26 17:15
 * @description: 文章service
 */
@Service
public class ArticleService extends BaseService<ArticleMapper,Article> {

    @Override
    public void save(Article entity) {
        entity.setLookCount(0);
        entity.setLikeCount(0);
        entity.setCommentCount(0);
        entity.setId(Constants.getID());
        super.save(entity);
}

}
