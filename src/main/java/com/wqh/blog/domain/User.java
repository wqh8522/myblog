package com.wqh.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wqh.blog.enums.RoleEnum;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * 用户实体类
 * @author wqh
 */
@Data
@ToString
public class User extends BaseEntity{
    /**主键*/
    private String id;

    /**用户名，唯一*/
    @NotNull(message = "用户名不能为空")
    private String username;

    /**邮箱，唯一*/
    @Email(message = "邮箱格式不对")
    @JsonIgnore
    private String email;

    /**密码*/
    @NotNull(message = "密码不能为空")
    @JsonIgnore
    private String password;

    /**地址*/
    @JsonIgnore
    private String address;

    /**创建时间*/
    @JsonIgnore
    private Date createTime;

    /**个人描述*/
    @JsonIgnore
    private String description;

    /**头像地址*/
    private String icon;

    /**电话号码，唯一*/
    @JsonIgnore
    private String phone;

    /**权限，使用枚举类型*/
    @JsonIgnore
    private RoleEnum role;

    /**更新时间*/
    @JsonIgnore
    private Date updateTime;


    public User() {
        super();
    }


}