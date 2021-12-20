package org.sang.service;


import org.sang.mapper.UserAndBulletinMapper;
import org.sang.mapper.UserMapper;
import org.sang.model.Bulletin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ServerEndpoint("/websocket/{uid}")
public class WebSocketService {


    private static ConcurrentHashMap<Integer, Session> userMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("uid")Integer uid, Session session){
        System.out.println("用户：" + uid + "连接成功");
        userMap.put(uid, session);
    }

    @OnClose
    public void onClose(@PathParam("uid")Integer uid){
        System.out.println("用户：" + uid + "关闭连接");
        userMap.remove(uid);
    }

    @OnMessage
    public void onMessage(String message) throws IOException{
        /*Integer uid = Integer.parseInt(message.substring(message.length() - 8));
        Integer bid = Integer.parseInt(message.substring(0,6));
        String bMessage = message.substring(6,message.length() - 8);
        System.out.println("向用户："+ uid + " 发送公告：" + bid + " " + bMessage);
        Session target = userMap.get(uid);
        message = bid + bMessage;*/
        //UserAndBulletinService userAndBulletinService = new UserAndBulletinService();
        //userAndBulletinService.insertUserAndBulletin(uid,bid);     //写入数据库
        Integer uid = Integer.parseInt(message);
        System.out.println("向用户 " + uid + " 发送公告");
        Session target = userMap.get(uid);
        //该用户在线
        if (target != null){
            System.out.println("该用户在线");
            target.getBasicRemote().sendText("\n" + message);
        }
        else {
            System.out.println("该用户离线");
            //存储在redis
           //stringRedisTemplate.opsForValue().set(message,"有公告消息");
            //System.out.println("redis写入成功");
        }

    }

    @OnError
    public void onError(Throwable error){
        System.out.println("onError(),message=" + error.getMessage());
    }
}
