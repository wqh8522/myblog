package com.wqh.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author wanqh
 * @date 2017/11/23 20:50
 * @description: 基础实体类
 */
public class BaseEntity implements Serializable{

    @JsonIgnore
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
