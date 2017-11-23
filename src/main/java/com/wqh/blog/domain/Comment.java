package com.wqh.blog.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Author wqh
 * @Date 2017/10/19 11:23
 * @Description: 评论实体类
 */
@Entity
@Table(name = "t_blog_comment", schema = "blog", catalog = "")
@Data
public class Comment extends BaseEntity{

    @Id
    @Column(name = "ID",length = 50)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    public String id;//主键id

    @Column(name = "CREATE_TIME")
    public Date createTime;


    @NotEmpty(message = "评论内容不能为空")
    @Column(name = "CONTENT", nullable = false)
    @Size(max = 255,message = "字数超出范围")
    private String content;//评论的内容


  /*  private String replyId;//回复的评论id*/

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAENT_COMMENT_ID")
    private List<Comment> replys;//回复的列表

    @JoinColumn(name = "TO_USER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private User to_user;//回复谁，如果是评论文章，该字段为空

    @NotEmpty(message = "必须传入评论者的id")
    @OneToOne(cascade = CascadeType.DETACH , fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID",nullable = false)
    private User author; // 作者

    @NotEmpty(message = "必须传入要评论的文章id")
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID",nullable = false)
    private Article article; //所评论的文章 多对一关系,当回复评论是，该字段为空

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
/*
    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }*/

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getTo_user() {
        return to_user;
    }

    public void setTo_user(User to_user) {
        this.to_user = to_user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Comment> getReplys() {
        return replys;
    }

    public void setReplys(List<Comment> replys) {
        this.replys = replys;
    }


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
