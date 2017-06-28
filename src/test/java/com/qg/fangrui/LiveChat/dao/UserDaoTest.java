package com.qg.fangrui.LiveChat.dao;

import com.qg.fangrui.LiveChat.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Userdao 单元测试
 * Created by FunriLy on 2017/6/27.
 * From small beginnings comes great things.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserRepositoy repositoy;

    @Autowired
    private UserDao userDao;

    @Test
    public void test_reposity(){
        //先在数据库中添加一条记录{id='123456', name='funrily'}
        String name = "funrily";
        System.out.println("====测试Reposity===");
        User user = repositoy.findByName("funrily");
        if (user == null){
            System.out.println("user is null");
            return;
        }
        System.out.println(user.toString());

//        User next = repositoy.myselect("funrily");
//        System.out.println(next.toString());
//
//        repositoy.save(new User("654321", "fangrui"));
//        next = repositoy.findByName("fangrui");
//        System.out.println(next.toString());
    }

    /**
     * 查询表种记录的数量
     */
    @Test
    public void test_count(){
        long cout = userDao.count();
        System.out.println("count = " + cout);
    }

    @Test
    public void test_exists(){
        //根据主键判断是否存在
        boolean isExist = userDao.exists("123456");
        System.out.println("IsExist ：" + isExist);
    }

    @Test
    public void test_save(){
        //单个保存
        User user = new User("127.0.0.1", "host");
        System.out.println(userDao.save(user));

        //批量保存
        User user1 = new User("1", "1");
        User user2 = new User("2", "2");
        User user3 = new User("3", "3");
        User user4 = new User("4", "4");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        System.out.println("批量保存：" + userDao.save(users));
    }

    @Test
    public void test_find(){
        //通过id查询一个记录
        User user = userDao.findOne("127.0.0.1");
        System.out.println("通过id查找一个记录：" + user);

        //根据id列表查询，其内部实际上使用了 in 关键字
        List<String> idList = new ArrayList<>();
        idList.add("1");
        idList.add("2");
        System.out.println("根据id列表查询:" + userDao.findAll(idList));

        //查找所有的记录
        System.out.println("所有的记录:" + userDao.findAll());
    }

    /**
     * 关于delete的单元测试需要在上面的单元测试通过后
     * 数据库中有相应的记录才可以执行成功
     */
    @Test
    public void test_delete(){
        //通过id删除一条记录
        userDao.delete("127.0.0.1");
        System.out.println("通过id删除一条记录后，记录是否存在:" + userDao.exists("127.0.0.1"));

        //删除某个特定的记录
        User user = userDao.findOne("123456");  //查找一个记录
        if (user != null && user.getId() != null && !user.getId().equals("")){
            userDao.delete(user);
            System.out.println("通过id删除一条记录后，记录是否存在:" + userDao.exists("123456"));
        }

        //根据id列表删除记录
        List<User> userList = new ArrayList<>();
        userList.add(userDao.findOne("1"));
        userList.add(userDao.findOne("2"));
        userDao.delete(userList);

        //删除所有的记录
        userDao.deleteAll();
    }
}
