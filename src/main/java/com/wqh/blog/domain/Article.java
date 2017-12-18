package com.wqh.blog.domain;

import com.wqh.blog.util.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wqh
 */
@Data
public class Article extends BaseEntity implements Serializable{

    /**主键*/
    private String id;

    /**评论数*/
    private Integer commentCount;

    /**创建时间*/
    private Date createTime;

    /**是否删除*/
    private Integer delFlag = Constants.IS_NOT_DEL;

    /**查看数量*/
    private Integer lookCount;


    /**更新时间*/
    private Date updateTime;

    /**点赞数*/
    private Integer likeCount;

    /**作者*/
    private User author;

    /**分类*/
    private Category category;

    /**标题*/
    @NotNull(message = "标题不能为空")
    private String title;


    /**文章内容*/
    @NotNull(message = "文章内容不能为空")
    private String content;

    /**标签*/
    @NotNull(message = "标签不能为空")
    private String tag;



    public Article() {
        super();
    }


    public void addLookCount(){
        this.lookCount += 1;
    }
}