package com.wqh.blog.service.impl;

import com.wqh.blog.domain.Comment;
import com.wqh.blog.repository.CommentRepository;
import com.wqh.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @Author wqh
 * @Date 2017/10/20 14:14
 * @Description: 评论Service
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public JpaRepository<Comment, String> getJpaRepository() {
        return this.commentRepository;
    }
}
