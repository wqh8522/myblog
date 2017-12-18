package com.wqh.blog.config;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author wanqh
 * @date 2017/12/11 10:25
 * @description: redis配置类
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String,User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,User> template = new RedisTemplate<String,User>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String,Article> articleRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Article> template = new RedisTemplate<String,Article>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
