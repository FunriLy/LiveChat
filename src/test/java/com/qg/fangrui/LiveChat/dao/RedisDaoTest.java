package com.qg.fangrui.LiveChat.dao;

import com.qg.fangrui.LiveChat.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Redis 数据库简单操作
 * Created by FunriLy on 2017/6/28.
 * From small beginnings comes great things.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisDaoTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test_saveString(){
        User user = new User("123456", "funrily");
        //存入数据库
        redisTemplate.opsForSet().add("save", user);
        //判断是否存在
        boolean isExit = redisTemplate.opsForSet().isMember("save", user);
        Assert.assertEquals(true, isExit);

        //删除
        redisTemplate.opsForSet().remove("save", user);
        isExit = redisTemplate.opsForSet().isMember("save", user);
        Assert.assertEquals(false, isExit);
    }
}
