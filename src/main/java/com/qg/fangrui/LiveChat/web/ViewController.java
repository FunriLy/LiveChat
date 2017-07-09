package com.qg.fangrui.LiveChat.web;

import com.qg.fangrui.LiveChat.dao.RedisDao;
import com.qg.fangrui.LiveChat.dao.UserDao;
import com.qg.fangrui.LiveChat.model.Message;
import com.qg.fangrui.LiveChat.model.Recording;
import com.qg.fangrui.LiveChat.model.User;
import com.qg.fangrui.LiveChat.utils.IpUtil;
import com.qg.fangrui.LiveChat.utils.NameGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by FunriLy on 2017/7/1.
 * From small beginnings comes great things.
 */
@Controller
public class ViewController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisDao redisDao;

    @RequestMapping(value = "/live_room", method = RequestMethod.GET)
    public String mymain(HttpServletRequest request, Model model){
        String ip = IpUtil.getRemoteAddr(request);
        HttpSession session = request.getSession();
        User user = userDao.findOne(ip);
        if (user == null){
            //用户未曾访问过
            user = new User();
            user.setId(ip);
            user.setName(NameGeneratorUtil.getName());
            userDao.save(user);
        }
        //将用户信息存入缓存
        session.setAttribute("user", user);

        model.addAttribute("online", getOnlineUser());
        model.addAttribute("recording", getRecodingList());
        return "live";
    }

    @RequestMapping(value = "/online", method = RequestMethod.GET)
    @ResponseBody
    public Set getOnlineUser() {
        return redisDao.getAllUser();
    }

    @RequestMapping(value = "/recording", method = RequestMethod.GET)
    @ResponseBody
    public List getRecodingList(){
        return redisDao.getRecordingList();
    }

    /**
     *  @MessageMapping 注解能够在控制器中处理 STOMP 消息
     */

    @MessageMapping(value = "/chat")
    @SendTo("/topic/group")
    public Message testRecord(String msg, @Header(value = "simpSessionAttributes")Map<String, Object> session) {
        User user = (User) session.get("user");
        String username = user.getName();
        Message message = new Message();
        message.setSendTime(Calendar.getInstance());
        message.setAuthor(username);
        message.setBody(msg);
        return message;
    }
}
