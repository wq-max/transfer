package org.sang.controller;

import org.sang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis")
    public String test(){
        stringRedisTemplate.opsForValue().set("21172111","test");
        if (stringRedisTemplate.hasKey("21172111"))
            System.out.println("查到21172111");
        if (stringRedisTemplate.delete("21172111"))
            System.out.println("删除21172111");
        return "21172122";
    }
}
