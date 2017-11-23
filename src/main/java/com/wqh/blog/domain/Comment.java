package com.wqh.blog.domain;

import java.util.Date;

public class Comment {
    private String id;

    private String content;

    private Date createTime;

    private String articleId;

    private String userId;

    private String toUserId;

    private String paentCommentId;

    public Comment(String id, String content, Date createTime, String articleId, String userId, String toUserId, String paentCommentId) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.articleId = articleId;
        this.userId = userId;
        this.toUserId = toUserId;
        this.paentCommentId = paentCommentId;
    }

    public Comment() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    public String getPaentCommentId() {
        return paentCommentId;
    }

    public void setPaentCommentId(String paentCommentId) {
        this.paentCommentId = paentCommentId == null ? null : paentCommentId.trim();
    }
}