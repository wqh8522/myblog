package com.wqh.blog.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wanqh
 * @date 2017/12/07 11:01
 * @description: 文章VO
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleVO implements Serializable{

    /**主键*/
    private String id;


    /**标题*/
    @NotNull(message = "标题不能为空")
    private String title;


    /**文章内容*/
    @NotNull(message = "文章内容不能为空")
    private String content;

    /**标签*/
    @NotNull(message = "标签不能为空")
    private String tag;

    @NotNull(message = "请选择分类信息")
    private String catrgoryId;

    public ArticleVO() {
    }
}
