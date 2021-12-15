package org.sang.controller;

import org.sang.model.Bulletin;
import org.sang.model.User;
import org.sang.service.RedisService;
import org.sang.service.UserAndBulletinService;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SendToUserTestController {

    @Autowired
    UserService userService;

    @Autowired
    UserAndBulletinService userAndBulletinService;

    @Autowired
    RedisService redisService;

    private Integer bid;

    @GetMapping("/admin/userListBid/{id}")
    public String updatePage(Model model, @PathVariable int id){
        bid = id;
        return "redirect:/admin/userList";
    }

    @RequestMapping("admin/userList")
    public String allUsers(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users",userList);
        return "userList";
    }

    @RequestMapping("admin/findUsers")
    public String findUsers(Model model, String userId){
        Integer uid = Integer.parseInt(userId);
        System.out.println(uid);
        List<User> userList = userService.getUsersLikeId(uid);
        model.addAttribute("users",userList);
        return "userList";
    }

   /* @RequestMapping("/order/createOrder")
    @ResponseBody
    public int createOrder(@RequestBody List<Integer> uidList){
        List<Map<String,Object>> list = new ArrayList<>();
        System.out.println(uidList.size());
        for (Integer id : uidList)
            System.out.println(id);
        if (list != null && list.size() > 0){
            return 1;
        }
        return 0;
    }*/

    @RequestMapping("/admin/sendToUsers")
    @ResponseBody
    public int sendToUsers(@RequestBody List<Integer> uidList){
        for (Integer uid : uidList){
            if (uid > 0){
                userAndBulletinService.insertUserAndBulletin(uid,bid);
                int state = userService.getStateById(uid);
                if (state == 0){
                    redisService.set(uid);
                }
            }

        }
        return uidList.size();
    }
}
