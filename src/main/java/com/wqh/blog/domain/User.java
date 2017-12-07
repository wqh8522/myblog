package com.wqh.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wqh.blog.enums.RoleEnum;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * 用户实体类
 * @author wqh
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}