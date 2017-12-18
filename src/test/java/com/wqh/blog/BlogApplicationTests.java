package com.wqh.blog;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(BlogApplicationTests.class);
	@Test
	public void contextLoads() {
	}

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedis() throws Exception{
		stringRedisTemplate.opsForValue().set("name","wqh");
		Assert.assertEquals("wqh",stringRedisTemplate.opsForValue().get("name"));
	}

	@Autowired
	private RedisTemplate<String,User> userRedisTemplate;

	@Test
	public void testUserRedis() throws Exception{
		User user = new User();
		user.setId("1111");
		user.setUsername("wqh");
		user.setPassword("1122222");
		userRedisTemplate.opsForValue().set(user.getId(),user);
		logger.info("======={}===",user);
		Assert.assertEquals("wqh",userRedisTemplate.opsForValue().get(user.getId()).getUsername());

	}

	@Autowired
	private RedisTemplate<String,Article> articleRedisTemplate;

	@Test
	public void testArticleRedis() throws Exception{
		Article article = new Article();
		article.setId("1234");
		article.setTitle("测试redis");
		articleRedisTemplate.opsForValue().set(article.getId(),article);

		Assert.assertEquals("测试redis",articleRedisTemplate.opsForValue().get(article.getId()).getTitle());
	}
}
