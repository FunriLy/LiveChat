package com.qg.fangrui.LiveChat.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
@Entity //实体类，利用对象关系映射生成数据库表
@Table(name = "users", schema = "livechat", catalog = "")
public class User implements Serializable{

    private static final long serialVersionUID = 1l;

    @Id
    private String id;
    @Column(nullable = false, name = "name")
    private String name;

    public User(){

    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
