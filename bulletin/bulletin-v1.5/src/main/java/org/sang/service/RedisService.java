package org.sang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean set(Integer uid){
        ValueOperations ops = stringRedisTemplate.opsForValue();
        ops.set(String.valueOf(uid),"有公共消息");
        return true;
    }

    public boolean hasUid(Integer uid){
       boolean flag = stringRedisTemplate.hasKey(String.valueOf(uid));
       if (flag)
           delete(uid);
       return flag;
    }

    public boolean delete(Integer uid){
        return stringRedisTemplate.delete(String.valueOf(uid));
    }
}
