package org.sang.service;


import org.sang.mapper.UserMapper;
import org.sang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User login(User user){
        return userMapper.login(user);
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
    public User getUserById(int id){
        return userMapper.getUserById(id);
    }
    public User getUserByName(String name){
        return userMapper.getUserByName(name);
    }
    public List<User> getAllUsers(){
        return userMapper.getAllUsers();
    }
    public List<User> getUsers(int offset){
        return userMapper.getUsers(offset);
    }
    public int getUserNumber(){
        return userMapper.getUserNumber();
    }
    public int getIdByName(String name){
        return userMapper.getIdByName(name);
    }
}
