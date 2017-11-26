package com.wqh.blog.domain;

import com.wqh.blog.util.Constants;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Article extends BaseEntity{

    /**主键*/
    private String id;

    /**评论数*/
    private Integer commentCount = 0;

    /**创建时间*/
    private Date createTime;

    /**是否删除*/
    private Integer delFlag = Constants.IS_NOT_DEL;

    /**查看数量*/
    private Integer lookCount = 0;

    /**标签*/
    @NotNull(message = "标签不能为空")
    private String tag;

    /**标题*/
    @NotNull(message = "标题不能为空")
    private String title;

    /**更新时间*/
    private Date updateTime;

    /**点赞数*/
    private Integer likeCount = 0;

    /**作者*/
    private User author;

    /**分类*/
    private Category category;

    /**文章内容*/
    @NotNull(message = "文章内容不能为空")
    private String content;


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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}