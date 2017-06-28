package com.qg.fangrui.LiveChat.dao;

import com.qg.fangrui.LiveChat.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
public interface UserRepositoy extends Repository<User, String> {

    /**
     * Repository是一个标记接口，继承该接口的Bean被Spring容器识别为一个RepositoryBean
     * 1. 可以使用 Query 注解查询
     * 2. spring data repository 接口中的方法默认只有查询和保存,没有更新和删除的事务
     *    (所以一般可以选用CrudRepository)
     * 3. SpringData的命名规范：
     *    (find | get | read) + By + field + ( + 关键字 + field )
     *    如，findByName、findByNameAndId
     */

    // SQL = select * from users where name = ?
    public User findByName(String name);

    public User getById(String id);

    public User save(User user);

    /**
     * 关于 * ，IDEA语法报错但不影响运行，应该是IDEA本身提示的问题
     * 但是有一个语法提示感觉有点别扭，所以我将它注释了
     */
//    @Query(value = "select * from users u where u.name = :name", nativeQuery = true)
//    public User myselect(@Param("name") String name);
}
