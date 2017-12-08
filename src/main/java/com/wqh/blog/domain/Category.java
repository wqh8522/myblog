package com.wqh.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author wqh
 * 文章分类实体类
 */

public class Category extends BaseEntity{
    /**主键*/
    private String id;

    /**描述*/
    @NotNull(message = "描述不能为空")
    private String description;

    /**名称*/
    @NotNull(message = "名称不能为空")
    private String name;

    /**创建时间*/
    @JsonIgnore
    private Date createTime;

    /**更新时间*/
    @JsonIgnore
    private Date updateTime;

    /**排序*/
    @JsonIgnore
    @NotNull(message = "排序不能为空")
    private String sort;

    public Category() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}