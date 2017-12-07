package com.wqh.blog;

import com.wqh.blog.handle.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

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
		registrationBean.addUrlPatterns("/u/test","/article/insert");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
