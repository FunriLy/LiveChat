package com.qg.fangrui.LiveChat.dao;

import com.qg.fangrui.LiveChat.model.Recording;
import com.qg.fangrui.LiveChat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Redis 操作类
 * Created by FunriLy on 2017/6/28.
 * From small beginnings comes great things.
 */
@Repository
public class RedisDao {

    /**
     * 原本是按照用一个synchronizedSet来存储在线用户的资料
     * 后来有大佬建议我考虑一下在线人数，尽可能优化一下
     * 故改用Redis来存储在线用户资料
     */

    @Autowired
    private RedisTemplate redisTemplate;    //SpringBoot提供的关于Redis的简单封装

    public void addUser(User user){
        redisTemplate.opsForSet().add("OnlineUser", user);
    }

    public void removeUser(User user){
        redisTemplate.opsForSet().remove("OnlineUser", user);
    }

    public Set getAllUser(){
        return redisTemplate.opsForSet().members("OnlineUser");
    }

    public void addRecordingList(Recording recording){
        //访客数量超过100，保证线程安全
        synchronized (recording) {
            if (redisTemplate.opsForList().size("Recording") >= 100) {
                redisTemplate.opsForList().rightPop("Recording");
            }
            redisTemplate.opsForList().leftPush("Recording", recording);
        }
    }

    public void removeRecordingList(Recording recording){
        redisTemplate.opsForList().remove("Recording", 0, recording);
    }

    public List getRecordingList(){
        return redisTemplate.opsForList().range("Recording", 0, -1);
    }

}
