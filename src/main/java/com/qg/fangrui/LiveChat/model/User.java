package com.qg.fangrui.LiveChat.model;

import java.io.Serializable;

/**
 * 用户实体
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1l;

    private String id;
    private String randomName;

    public User(){

    }

    public User(String id, String randomName) {
        this.id = id;
        this.randomName = randomName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRandomName() {
        return randomName;
    }

    public void setRandomName(String randomName) {
        this.randomName = randomName;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User that = (User) o;
        //如果id为null，则要求that.id也为null才返回true
        //如果id不为null，则进行字符串对比成功加个否定来返回true
        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (randomName != null ? !randomName.equals(that.randomName) : that.randomName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (randomName != null ? randomName.hashCode() : 0);
        return result;
    }
}
