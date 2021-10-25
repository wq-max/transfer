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
        /*User user1 = userService.login(user);
        if(user!=null)
            System.out.println("登陆成功！欢迎你----"+user1.getUsername());
        User user2 = new User();
        user2.setUser_id(4);
        user2.setUsername("冯宝宝");
        user2.setPassword("fbb");
        userService.addUser(user2);
        userService.deleteUserById(5);
        user2.setEmail("96451387@ndt.com");
        userService.updateUserById(user2);
        User user3 = userService.getUserById(3);
        System.out.println("getUserById--->"+user3);
        User user4 = userService.getUserByName("张楚岚");
        System.out.println("getUserByName--->"+user4);
        List<User> userList = userService.getAllUsers();
        System.out.println("getAllUsers--->"+userList);*/
        System.out.println(user);
        //User login_user = userService.login(user);
        //System.out.println(login_user);
        String password = userService.mdTest(user.getPassword(),user.getUsername());
        User md_test_user = userService.getUserByName(user.getUsername());
        if (password.equals(md_test_user.getPassword()))
            System.out.println("登录成功！欢迎你---"+md_test_user.getUsername());
        System.out.println(password);
        System.out.println(md_test_user.getPassword());
        /*if(login_user!=null)
            System.out.println("登录成功！欢迎你---"+login_user.getUsername());*/
        /*User add_user = new User();
        add_user.setUsername("Bob");
        add_user.setPassword("123");
        add_user.setEmail("65231489@gmail");
        add_user.setMobile("18965327894");
        int add_test = userService.addUser(add_user);
        if (add_test > 0)
            System.out.println("添加用户---"+add_user.getUsername()+" 成功！");
        String delete_name = "夏柳青";
        int id = userService.getIdByName(delete_name);
        int delete_test = userService.deleteUserById(id);
        if (delete_test > 0)
            System.out.println("删除用户："+delete_name+"成功！其用户id:"+id);
        int select_id = 3;
        User select_id_user = userService.getUserById(select_id);
        System.out.println("getUserById--->"+select_id_user);
        String select_name = "qiqi";
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
        }*/

        return "Mybatis user";
    }
}
