package com.gongfu.config.interceptor.constant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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


    public void objectSet(String key, Object value,long timeout) {
        redisTemplate.opsForValue().set(key, value,timeout, TimeUnit.SECONDS);
        log.info("objectSet key success: {}", key);
    }

    public Object objectGet(String key,long timeout) {
        //获取缓存数据时，先更新缓存时间
        Object o =this.redisTemplate.opsForValue().get(key);
        //redisTemplate.opsForValue().set(key, o,timeout, TimeUnit.SECONDS);
        return o;
    }

    public void objectDel(String key) {
        this.redisTemplate.delete(key);
    }
}
