package com.wqh.blog;

import com.google.common.collect.Lists;
import com.wqh.blog.handle.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@MapperScan("com.wqh.blog.mapper")
public class BlogApplication {

	/**
	 * 注册jwt拦截器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		List<String>  urlPatterns = Lists.newArrayList();
		urlPatterns.add("/article/insert");
		urlPatterns.add("/category/insert");
		registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
