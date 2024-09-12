package net.journal.JournalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;//check the config file for serialize-deserialize


    @Disabled
    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email","gmail@email.com");//we need to serialise data to make it compatible to redis
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 1;
    }
}