package com.qg.fangrui.LiveChat.config;

import com.qg.fangrui.LiveChat.interceptor.ChannelInterceptor;
import com.qg.fangrui.LiveChat.interceptor.HandShkeInceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 启用STOMP协议WebSocket配置
 * Created by FunriLy on 2017/7/1.
 * From small beginnings comes great things.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    //注入bean
    @Bean
    public ChannelInterceptor channelInterceptor(){
        return new ChannelInterceptor();
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        System.out.println("注册WebSocket配置");
        stompEndpointRegistry.addEndpoint("/mylive").setAllowedOrigins("*").addInterceptors(new HandShkeInceptor()).withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        System.out.println("启动服务");
        registry.setApplicationDestinationPrefixes("/demo");    //客户端发送消息到服务端
        registry.enableSimpleBroker("/topic","/queue"); //服务器往客户端发送消息
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration){
        ChannelRegistration channelRegistration = registration.setInterceptors(channelInterceptor());
        super.configureClientInboundChannel(registration);
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        super.configureClientOutboundChannel(registration);
    }
}
