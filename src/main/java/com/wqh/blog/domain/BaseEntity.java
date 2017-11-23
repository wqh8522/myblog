package com.wqh.blog.domain;

import java.io.Serializable;

/**
 * @author wanqh
 * @date 2017/11/23 20:50
 * @description: 基础实体类
 */
public class BaseEntity implements Serializable{

    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
