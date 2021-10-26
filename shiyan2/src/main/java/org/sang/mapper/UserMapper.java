package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User login(User user);
    int addUser(User user);
    int deleteUserById(Integer id);
    int updateUserById(User user);
    User getUserById(int id);
    User getUserByName(String name);
    List<User> getAllUsers();
    List<User> getUsers(int offset);
    int getUserNumber();
    int getIdByName(String name);
}
