package org.lsy.learn.security.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate template;

    private static StringRedisTemplate redisTemplate;

    @PostConstruct
    private void init() {
        redisTemplate = this.template;
    }

    public static String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static void del(String key) {
        redisTemplate.delete(key);
    }

    public static void set(String key, String value) {
        RedisUtils.set(key, value, -1);
    }

    public static void set(String key, String value, int expired) {
        redisTemplate.opsForValue().set(key, value, expired, TimeUnit.SECONDS);
    }
}
