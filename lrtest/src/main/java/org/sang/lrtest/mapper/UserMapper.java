package org.sang.lrtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.lrtest.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    int addUser(User user);
    int deleteUserById(int id);
    int updateUserById(User user);
    String getPasswordByName(String username);
    int getIdByName(String username);
    User getUserById(int id);
    List<User> getAllUsers();
}
