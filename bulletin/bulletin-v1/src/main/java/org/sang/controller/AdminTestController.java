package org.sang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class AdminTestController {
    @GetMapping("/adminindex")
    public String index(){
        System.out.println("go to login");
        return "adminIndex";
    }

    @PostMapping(value = "/AdminloginIn")
    public String login(String username, String password){
        System.out.println(username+"    "+password);
        if (username.equals("admin") && password.equals("123")){
            return "adminTest";
        }
        else
            return "error";

    }
}
