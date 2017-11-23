package com.wqh.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wqh.blog.enums.RoleEnum;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author wqh
 * @Date 2017/10/19 11:23
 * @Description: 用户实体类
 */
@Entity
@Table(name = "t_blog_user", schema = "blog", catalog = "")
@DynamicUpdate
public  class User extends BaseEntity{

    /**主键 使用uuid生产主键*/
    @Id
    @Column(name = "ID",length = 50)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**用户名 ，唯一，登陆的唯一标识*/
    @Column(name = "USERNAME",length = 20,unique = true,nullable = false)
    @Size(min = 2,max = 20,message = "用户名在2到20个字之间")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**密码*/
    @Column(name = "PASSWORD",length = 20)
    @NotEmpty(message = "密码不能为空")
    @Size(min = 4,max = 20,message = "密码必须在4到20个字符之间")
    @JsonIgnore
    private String password;

    /**电话*/
    @Column(name = "PHONE",length = 11)
    @Size(min = 11,max = 11,message = "请输入正确的手机号码")
    private String phone;

    /**邮箱，后面添加邮箱验证功能*/
    @Column(name = "EMAIL",length = 50)
    @Email(message = "邮箱格式不对")
    private String email;

    /**地址*/
    @Column(name = "ADDRESS",length = 200)
    private String address;

    /**头像保存的是存头像的地址*/
    @Column(name = "ICON" ,length = 255)
    private String icon;

    /**描述,自我介绍*/
    @Column(name = "DESCRIPTION",length = 100)
    private String description;

    /** 角色，默认为用户 Enumerated:将枚举类型转换为String类型*/
    @Column(name = "ROLE",length = 20)
    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.ROLE_USER;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return String.format("User=[id='%s', username='%s',password='%s',email='%s',role='%s']",
                id,username,password,email,role);
    }
}
