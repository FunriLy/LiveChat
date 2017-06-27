package com.qg.fangrui.LiveChat.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 消息实体
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1l;

    private String author;
    private String body;
    private Calendar sendTime;

    public Message(){}

    public Message(String author, String body) {
        this.author = author;
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Calendar getSendTime() {
        return sendTime;
    }

    public void setSendTime(Calendar sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
