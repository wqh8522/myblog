package com.wqh.blog.repository;

import com.wqh.blog.domain.Article;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wqh
 * @Date 2017/10/19 16:47
 * @Description: 文章 Repository
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,String>{

    /**
     * 根据删除标识分页查询
     * @param pageable
     * @param del_flag
     * @returnd
     */
    Page<Article> findByDelFlag(Pageable pageable,Integer del_flag);

    /**
     * 根据用户id查询该用户的文章列表
     * @param pageable
     * @param user_id
     * @return
     */
    Page<Article> findByAuthor(Pageable pageable,String user_id);
}
