package org.sang.lrtest.controller;

import org.sang.lrtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index(){
        System.out.println("go to login");
        return "index_plus";
        //return "index";
    }

    @PostMapping(value = "/loginIn")
    public String login(String username,String password){
        System.out.println(username+"    "+password);
        boolean flag = userService.login(username,password);
        if(flag){
            return "success";
        }else {
            return "error";
        }
    }
}
