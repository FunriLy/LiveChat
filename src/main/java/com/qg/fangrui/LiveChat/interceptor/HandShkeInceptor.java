package com.qg.fangrui.LiveChat.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 握手处理类
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
public class HandShkeInceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        System.out.println("握手前"+request.getURI());

        if (request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

            //获取HttpSession
            HttpSession session = servletRequest.getServletRequest().getSession();
            System.out.println(session);
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("握手后");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
