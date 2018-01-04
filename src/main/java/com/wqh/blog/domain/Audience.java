package com.wqh.blog.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wanqh
 * @date 2017/12/08 12:06
 * @description: jwt
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {

    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

}
