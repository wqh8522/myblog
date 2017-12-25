package com.wqh.blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private String id;

    private String content;

    private Date createTime;

    private String articleId;

    private String userId;

    private String toUserId;

    private String paentCommentId;


    public Comment() {
        super();
    }

}