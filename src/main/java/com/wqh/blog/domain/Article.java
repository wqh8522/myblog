package com.wqh.blog.domain;

import com.wqh.blog.util.Constants;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author wqh
 * @Date 2017/10/19 11:23
 * @Description: 文章实体类
 */
@Entity
@Table(name = "t_blog_article", schema = "blog", catalog = "")
@DynamicUpdate
public class Article extends BaseEntity{


    /**主键id*/
    @Id
    @Column(name = "ID",length = 50)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**创建时间*/
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**更新时间*/
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**文章标题*/
    @NotEmpty(message = "文章标题不能为空")
    @Column(name = "TITLE",nullable = false,length = 100)
    @Size(max = 100,message = "标题最多为100个字符")
    private String title;

    /**文章内容*/
    @NotEmpty(message = "文章内容不能为空")
    @Lob
    @Column(name = "CONTENT",nullable = false,columnDefinition = "TEXT")
    private String content;


    /**是否删除，这里删除做逻辑删除*/
    @Column(name = "DEL_FLAG")
    private Integer delFlag = Constants.IS_NOT_DEL;

    /**查看次数*/
    @Column(name = "LOOK_COUNT")
    private Integer lookCount;

    /**评论数量*/
    @Column(name = "COMMENT_COUNT")
    private Integer commentCount;

    /**点赞次数*/
    @Column(name = "LIKE_COUNT")
    private Integer voteCount;

    /**文章标签，多个标签已逗号隔开，前端使用jquery tag input*/
    @Column(name = "TAG",length = 100)
    private String tag;

    /**文章作者*/
    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User author;

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    /*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "T_ARTICLE_COMMENT",
                joinColumns = @JoinColumn(name = "ARTICLE_ID",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "COMMENT_ID",referencedColumnName = "id"))*/
    /**文章评论，*/
    @Transient
    private List<Comment> comments;

    /*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "T_ARTICLE_VOTE",
                joinColumns = @JoinColumn(name = "ARTICLE_ID",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "VOTE_ID",referencedColumnName = "id"))*/
    /**点赞,一对多关系，*/
    @Transient
    private List<Vote> votes;



    public void setComments(List<Comment> comments) {
        this.comments = comments;
        if (comments == null){
            this.commentCount = 0;
        }else{
            this.commentCount = comments.size();
        }
    }


    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 添加评论是条用该方法更新评论数量
     * @param comment
     */
    public void addCommrnt(Comment comment){
        this.comments.add(comment);
        this.commentCount = this.comments.size();
    }

    /**
     * 删除评论处理评论数量
     * @param commentId
     */
    public void removeComment(String commentId){
        for(Comment comment : comments){
            if(comment.getId().equals(commentId)){
                this.comments.remove(comment);
                break;
            }
        }
        this.commentCount = this.comments.size();
    }


    public void setVotes(List<Vote> votes) {
        this.votes = votes;
        if(votes == null){
            voteCount = 0;
        }else{
            voteCount = votes.size();
        }
    }

    /**
     * 点赞数量处理
     * @param vote
     */
    public void addVote(Vote vote){
        this.votes.add(vote);
        this.voteCount  = this.votes.size();
    }

    /**
     * 取消点赞是更新点赞数量
     * @param voteId
     */
    public void removeVote(String voteId){
        for(Vote vote : this.votes){
            if(vote.getId().equals(voteId)){
                this.votes.remove(vote);
                break;
            }
        }
        this.voteCount = votes.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getCommentCount() {
        return commentCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public List<Comment> getComments() {
        return comments;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}
