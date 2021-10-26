package org.sang.controller;

import org.sang.model.User;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/userOps")
    public String userOps(@RequestBody User user){
        System.out.println(user);
        User login_user = userService.login(user);
        System.out.println(login_user);
        if(login_user!=null)
            System.out.println("登录成功！欢迎你---"+login_user.getUsername());
        User add_user = new User();
        add_user.setUsername("Bob");
        add_user.setPassword("123");
        add_user.setEmail("65231489@gmail");
        add_user.setMobile("18965327894");
        int add_test = userService.addUser(add_user);
        if (add_test > 0)
            System.out.println("添加用户---"+add_user.getUsername()+" 成功！");
        String delete_name = "Bob";
        int id = userService.getIdByName(delete_name);
        int delete_test = userService.deleteUserById(id);
        if (delete_test > 0)
            System.out.println("删除用户："+delete_name+"成功！其用户id:"+id);
        int select_id = 3;
        User select_id_user = userService.getUserById(select_id);
        System.out.println("getUserById--->"+select_id_user);
        String select_name = "miaomiao";
        User select_name_user = userService.getUserByName(select_name);
        System.out.println("getUserByName--->"+select_name_user);
        List<User> all_users = userService.getAllUsers();
        System.out.println("getAllUsers--->"+all_users);
        int count = userService.getUserNumber();
        System.out.println("分页查询：");
        int page = 1;
        for (int i = 0; i < count; i+=3){
            List<User> users = userService.getUsers(i);
            System.out.println("第"+page+"页：");
            System.out.println(users);
            page++;
        }

        return "Mybatis user";
    }
}
