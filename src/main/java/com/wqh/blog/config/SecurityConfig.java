package com.wqh.blog.config;

import com.wqh.blog.enums.RoleEnum;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wanqh
 * @date 2017/11/26 10:27
 * @description: spring security配置类
 */
//@EnableWebSecurity
//// 启用方法安全设置
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置验证规则，/admins/**下的资源只有admin权限可以访问
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll()
                .antMatchers("/admins/**").hasRole(RoleEnum.ADMIN.toString())
                .and()
                .openidLogin();
    }

}
