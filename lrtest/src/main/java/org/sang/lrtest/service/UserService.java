package org.sang.lrtest.service;

import org.sang.lrtest.mapper.UserMapper;
import org.sang.lrtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public boolean login(String username,String password){
        String pwd = userMapper.getPasswordByName(username);
        if(password.equals(pwd))
            return true;
        return false;
    }

    public int addUser(User user){
        return userMapper.addUser(user);
    }
    public int deleteUserById(Integer id){
        return userMapper.deleteUserById(id);
    }
    public int updateUserById(User user){
        return userMapper.updateUserById(user);
    }
    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }
    public int getIdByName(String username){
        return userMapper.getIdByName(username);
    }
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }
}
