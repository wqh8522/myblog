package com.wqh.blog.vo;

import java.io.Serializable;

/**
 * @author wanqh
 * @date 2017/11/26 13:59
 * @description: 用户登录VO
 */
public class LoginVO implements Serializable{

    /**用户登录标识*/
    private String token;

    /**主键*/
    private String id;

    /**用户名*/
    private String username;


    /**用户邮箱*/
    private String email;

    /**用户权限*/
    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "token='" + token + '\'' +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
