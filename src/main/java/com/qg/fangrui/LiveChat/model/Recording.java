package com.qg.fangrui.LiveChat.model;

import java.io.Serializable;

/**
 * 集合实体
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
public class Recording implements Serializable {

    private static final long serialVersionUID = 1l;

    private User user;
    private Message message;
    private long accessTime;

    public Recording(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        return "Recording{" +
                "user=" + user +
                ", message=" + message +
                '}';
    }
}
