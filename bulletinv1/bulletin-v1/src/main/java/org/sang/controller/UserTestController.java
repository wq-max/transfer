package org.sang.controller;

import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class UserTestController {
    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index(){
        System.out.println("go to login");
        return "index";
    }

    @PostMapping(value = "/loginIn")
    public String login(String userid, String password, Map<String, Object> map){
        System.out.println(userid+"    "+password);
        Integer uid = Integer.parseInt(userid);
        boolean flag = userService.login(uid,password);
        if(flag){
            String username = userService.getNameById(uid);
            map.put("username",username);
            map.put("userId",uid);
            return "userTest";
        }else {
            return "error";
        }
    }

}
