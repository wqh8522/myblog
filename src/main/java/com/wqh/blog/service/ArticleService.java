package com.wqh.blog.service;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author wqh
 * @Date 2017/10/20 9:48
 * @Description:
 */
public interface ArticleService extends BaseService<Article>{

    /**
     * 逻辑删除，更新文章字段del_flag为1
     * @param articele_id
     */
    void logicDel(String articele_id);

    /**
     * 查询用的文章列表
     * @param pageable
     * @param user
     * @return
     */
    Page<Article> findByUserId(Pageable pageable,User user);

}
