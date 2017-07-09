package com.qg.fangrui.LiveChat;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by FunriLy on 2017/7/6.
 * From small beginnings comes great things.
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {


    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LiveChatApplication.class);
    }
}
