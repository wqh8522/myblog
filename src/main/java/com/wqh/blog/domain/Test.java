package com.wqh.blog.domain;

import com.wqh.blog.config.cache.RedisObjectSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wanqh
 * @date 2017/12/26 14:51
 * @description:
 */
@Data
public class Test implements Serializable{

    private String id;

    private String name;

    private String remake;
}
