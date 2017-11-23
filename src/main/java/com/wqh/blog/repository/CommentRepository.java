package com.wqh.blog.repository;

import com.wqh.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author wqh
 * @Date 2017/10/20 14:13
 * @Description: 评论的Repository
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,String>{
}
