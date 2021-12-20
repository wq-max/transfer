package org.sang.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.sang.model.User;

import java.util.List;

@Mapper
public interface UserMapper {


    String selectPwdById(Integer id);       //通过Id查询密码

    String selectNameById(Integer id);      //通过Id查询用户名

    List<Integer> selectAllUserId();       //查询所有用户Id

    Integer selectStateById(Integer id);    //查询用户的登录状态

    Integer updateStateById(Integer id, Integer state);    //更新用户的登录状态

    List<User> selectAllUsers();      //查询所有用户

    List<User> selectUsersLikeId(Integer id);  //查询类似id的用户
}