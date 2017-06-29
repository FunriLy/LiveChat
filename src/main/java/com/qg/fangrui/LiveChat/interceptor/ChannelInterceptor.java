package com.qg.fangrui.LiveChat.interceptor;

import com.qg.fangrui.LiveChat.dao.RedisDao;
import com.qg.fangrui.LiveChat.model.Recording;
import com.qg.fangrui.LiveChat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
public class ChannelInterceptor extends ChannelInterceptorAdapter {

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    /**
     * SimpMessagingTemplate
     * SimpMessagingTemplate是Spring-WebSocket内置的一个消息发送工具
     * 可以将消息发送到指定的客户端。
     * 有一个重要方法：convertAndSend("path"， value);
     */

    /**
     * convertAndSend("path"， value):
     * 官方介绍：Convert the given Object to serialized form, possibly using a MessageConverter,
     * wrap it as a message and send it to the given destination.
     * 翻译过来：将给定的对象转换为序列化形式，可能使用MessageConverter将其作为消息包装，并将其发送到给定的目标。
     */

    /**
     *
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("ChannelInterceptor : preSend");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        //检测到用户订阅
        if (StompCommand.SUBSCRIBE.equals(command)) {
            //订阅频道集合
            Set<String> subscriptionSet = new HashSet<>();
            subscriptionSet.add("/topic/group");
            subscriptionSet.add("/topic/online_user");
            if (subscriptionSet.contains(accessor.getDestination())){
                //频道合法
                return super.preSend(message, channel);
            } else {
                return null;
            }
        }
        return super.preSend(message, channel);
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        System.out.println("ChannelInterceptor : postSend");
        super.postSend(message, channel, sent);
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        //System.out.println("ChannelInterceptor : afterSendCompletion");
        //检测用户是否连接成功，收集在线的用户信息
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (StompCommand.SUBSCRIBE.equals(command)){
            Map<String, User> map = (Map<String, User>) accessor.getHeader("simpSessionAttributes");
            User user = map.get("user");
            if (user != null){
                redisDao.addUser(user);
                Recording recording = new Recording();
                recording.setUser(user);
                recording.setAccessTime(Calendar.getInstance().getTimeInMillis());
                redisDao.addRecordingList(recording);
                //通过websocket实时返回在线人数
                this.simpMessagingTemplate.convertAndSend("/topic/online_user",redisDao.getAllUser());
            }
        }

        //用户断开连接，删除用户消息
        if (StompCommand.DISCONNECT.equals(command)) {
            Map<String, User> map = (Map<String, User>) accessor.getHeader("simpSessionAttributes");
            User user = map.get("user");
            if (user != null) {
                redisDao.removeUser(user);
                this.simpMessagingTemplate.convertAndSend("/topic/online_user",redisDao.getAllUser());
            }
        }
        super.afterSendCompletion(message, channel, sent, ex);
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        System.out.println("ChannelInterceptor : preReceive");
        return super.preReceive(channel);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        System.out.println("ChannelInterceptor : postReceive");
        return super.postReceive(message, channel);
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        System.out.println("ChannelInterceptor : afterReceiveCompletion");
        super.afterReceiveCompletion(message, channel, ex);
    }


}
