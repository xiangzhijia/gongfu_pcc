package com.gongfu.config.interceptor.constant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * 2017年1月7日
 *
 * @向治家
 */
@Component
@Slf4j
public class RedisComponent {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void strSet(String key, String value) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        // 1、key值在缓存中是否存在
        if (!this.stringRedisTemplate.hasKey(key)) {
            ops.set(key, value);
            log.info("strSet key success: {}", key);
        } else {
            // 2、存在则打印value
            log.info("this key: {}", ops.get(key));
        }
    }

    public String strGet(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    public void strDel(String key) {
        this.stringRedisTemplate.delete(key);
    }


    public void objectSet(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        log.info("objectSet key success: {}", key);
    }

    public Object objectGet(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public void objectDel(String key) {
        this.redisTemplate.delete(key);
    }
}
