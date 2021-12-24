package org.sang.service;

import org.sang.Utils.MD5Util;
import org.sang.mapper.UserMapper;
import org.sang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    //登录验证
    public boolean login(Integer id, String pwd){
        String selectPwd = userMapper.selectPwdById(id);
        String encodePwd = MD5Util.md5(id+pwd);
        if (encodePwd.equals(selectPwd)){
            int state = userMapper.selectStateById(id);
            if (state == 0){
                userMapper.updateStateById(id,1);
                return true;
            }
        }
        return false;
    }

    public int getStateById(Integer id){
        return userMapper.selectStateById(id);
    }

    //得到用户名
    public String getNameById(Integer id){
        return userMapper.selectNameById(id);
    }

    //得到所有用户的id
    public List<Integer> getAllUserId(){
        return userMapper.selectAllUserId();
    }

    //得到所有用户
    public List<User> getAllUsers(){
        return userMapper.selectAllUsers();
    }

    //得到类似id的用户
    public List<User> getUsersLikeId(Integer id){
        return userMapper.selectUsersLikeId(id);
    }

    //退出登录
    public boolean exit(Integer id){
        if (userMapper.updateStateById(id, 0)>0)
            return true;
        return false;
    }
}
