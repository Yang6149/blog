package com.yang.blog;

import com.yang.blog.dao.BlogRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;

public class RedisListTest extends BlogApplicationTests {
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    BlogRepository repository;
    @Test
    public void test(){
        RedisSerializer<String> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.opsForList().leftPush("lilii",new Date());
        redisTemplate.opsForList().leftPush("lilii",222);
        System.out.println(redisTemplate.opsForList().range("lilii",0,-1));
    }

}
