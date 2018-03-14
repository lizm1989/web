package com.javanull.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by lizhiming on 2018/3/13.
 */
@Service
public class RedisAndMysqlTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
