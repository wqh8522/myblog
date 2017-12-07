package com.wqh.blog.vo;

/**
 * @author wanqh
 * @date 2017/12/07 11:01
 * @description: 文章VO
 */
public class ArticleVO {

    /**主键*/
    private String id;

    /**标题*/
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
