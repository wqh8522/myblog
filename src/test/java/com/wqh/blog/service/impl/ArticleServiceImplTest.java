package com.wqh.blog.service.impl;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wqh
 * @Date 2017/10/20 10:17
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private VoteServiceImpl voteService;

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    public void findPageList() throws Exception {
    }


    @Test
    public void save() throws Exception {
        Article article = new Article();
        article.setTitle("第一篇博客");
        article.setContent("asasdaasdasdasdasdasdasds");
        articleService.save(article);
    }

    @Test
    public void findAll() throws Exception {
        articleService.findAll();
    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findAll1() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}