package com.wqh.blog.service.impl;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.repository.ArticleRepository;
import com.wqh.blog.service.ArticleService;
import com.wqh.blog.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author wqh
 * @Date 2017/10/20 9:49
 * @Description: 文章Service实现类
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public JpaRepository<Article, String> getJpaRepository() {
        return this.articleRepository;
    }


    @Override
    public void logicDel(String article_id) {
        Article article = this.articleRepository.findOne(article_id);
        if (article != null){
            //做逻辑删除，将删除标识改为1
            article.setDelFlag(Constants.IS_DEL);
            this.articleRepository.save(article);
        }
    }

    @Override
    public Page<Article> findByUserId(Pageable pageable, User user) {
        if(user == null || user.getId().equals("")){
            throw new BusinessException(ResultEnum.UNKNOWN_USER_ERROR.getCode(),ResultEnum.UNKNOWN_USER_ERROR.getMsg());
        }
        return articleRepository.findByAuthor(pageable,user.getId());
        
    }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        //查询所有删除标识位为0，也就是没被逻辑删除的记录
        return this.articleRepository.findByDelFlag(pageable,Constants.IS_NOT_DEL);
    }
}
