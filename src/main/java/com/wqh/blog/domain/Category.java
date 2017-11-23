package com.wqh.blog.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wqh
 * @Date 2017/10/19 11:23
 * @Description: 分类信息
 */
@Entity
@Table(name = "t_blog_category", schema = "blog", catalog = "blog")
//使用lombok包，自动生成get、set方法
@Data
public class Category extends BaseEntity{


    @Id
    @Column(name = "ID",length = 50)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    public String id;//主键id

    @Column(name = "CREATE_TIME")
    @CreationTimestamp//由数据库自动创建时间
    public Timestamp createTime;

    @Column(name = "UPDATE_TIME")
    @CreationTimestamp//由数据库自动创建时间
    public Timestamp updateTime;

    @Column(name = "NAME",length = 20)
    private String name;  //分类名

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "DESCRIPTION",length = 100)
    private String description; // 分类描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
