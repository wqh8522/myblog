package com.wqh.blog.domain;

import java.util.Date;

public class Article {
    private String id;

    private Integer commentCount;

    private Date createTime;

    private Integer delFlag;

    private Integer lookCount;

    private String tag;

    private String title;

    private Date updateTime;

    private Integer likeCount;

    private String userId;

    private String categoryId;

    private String content;

    public Article(String id, Integer commentCount, Date createTime, Integer delFlag, Integer lookCount, String tag, String title, Date updateTime, Integer likeCount, String userId, String categoryId, String content) {
        this.id = id;
        this.commentCount = commentCount;
        this.createTime = createTime;
        this.delFlag = delFlag;
        this.lookCount = lookCount;
        this.tag = tag;
        this.title = title;
        this.updateTime = updateTime;
        this.likeCount = likeCount;
        this.userId = userId;
        this.categoryId = categoryId;
        this.content = content;
    }

    public Article() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getLookCount() {
        return lookCount;
    }

    public void setLookCount(Integer lookCount) {
        this.lookCount = lookCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}