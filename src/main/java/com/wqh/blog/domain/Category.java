package com.wqh.blog.domain;

import java.util.Date;

/**
 * @author wqh
 */
public class Category extends BaseEntity{
    private String id;

    private Date createTime;

    private String description;

    private String name;

    private Date updateTime;

    public Category(String id, Date createTime, String description, String name, Date updateTime) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
        this.name = name;
        this.updateTime = updateTime;
    }

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
}