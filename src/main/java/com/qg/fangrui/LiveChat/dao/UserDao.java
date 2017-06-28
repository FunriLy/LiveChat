package com.qg.fangrui.LiveChat.dao;

import com.qg.fangrui.LiveChat.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserDao层接口
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
public interface UserDao extends CrudRepository<User, String> {

    /**
     *  CrudRepository 接口继承于 Repository 接口，并新增了简单的增、删、查等方法
     */

    /**
     * 方法列表：
        long count();
        boolean exists(Integer arg0);

        <S extends StudentPO> S save(S arg0);
        <S extends StudentPO> Iterable<S> save(Iterable<S> arg0);

        void delete(Integer arg0);
        void delete(Iterable<? extends StudentPO> arg0);
        void delete(StudentPO arg0);
        void deleteAll();

        StudentPO findOne(Integer arg0);
        Iterable<StudentPO> findAll();
        Iterable<StudentPO> findAll(Iterable<Integer> arg0);
     */
}
