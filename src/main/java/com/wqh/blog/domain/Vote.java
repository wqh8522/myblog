package com.wqh.blog.domain;

import java.util.Date;

public class Vote {
    private String id;

    private Date createTime;

    private String articleId;

    private String userId;

    public Vote(String id, Date createTime, String articleId, String userId) {
        this.id = id;
        this.createTime = createTime;
        this.articleId = articleId;
        this.userId = userId;
    }

    public Vote() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}