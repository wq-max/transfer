package org.sang.controller;

import org.sang.model.Bulletin;
import org.sang.model.UserAndBulletin;
import org.sang.service.BulletinService;
import org.sang.service.RedisService;
import org.sang.service.UserAndBulletinService;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserTestController {
    @Autowired
    UserService userService;

    @Autowired
    UserAndBulletinService userAndBulletinService;

    @Autowired
    BulletinService bulletinService;

    @Autowired
    RedisService redisService;

    private Integer userId;

    @GetMapping("/userindex")
    public String index(){
        System.out.println("go to login");
        return "index";
    }

    @PostMapping(value = "/user/loginIn")
    public String login(String userid, String password, Map<String, Object> map){
        System.out.println(userid+"    "+password);
        Integer uid = Integer.parseInt(userid);
        userId = uid;
        boolean flag = userService.login(uid,password);
        if(flag){
            String username = userService.getNameById(uid);
            map.put("username",username);
            map.put("userId",uid);
            return "redirect:/user/bulletinList";
        }else {
            return "error";
        }
    }

    @RequestMapping("/user/bulletinList")
    public String bulletinList(Model model){
        List<UserAndBulletin> userAndBulletinList = userAndBulletinService.selectBulletinByUserId(userId);
        Map<Bulletin,String> bulletinMap = new LinkedHashMap<>();
        for (UserAndBulletin userAndBulletin : userAndBulletinList){
            Integer bId = userAndBulletin.getBid();
            Bulletin bulletin = bulletinService.getBulletinByPrimaryKey(bId);
            System.out.println(bulletin.getId() + " " + bulletin.getMessage());
            if (userAndBulletin.getState() == 0){
                bulletinMap.put(bulletin,"??????");
            }
            else {
                bulletinMap.put(bulletin,"??????");
            }
        }

        model.addAttribute("bulletins",bulletinMap);
        String name = userService.getNameById(userId);
        model.addAttribute("name", name);
        model.addAttribute("userId",userId);
        System.out.println(name);
        boolean hasOffMessage = redisService.hasUid(userId);
        model.addAttribute("hasOffMessage",hasOffMessage);
        return "userBulletinList";
    }

    @RequestMapping("/user/find")
    public String find(Model model, String bulletinId){
        Integer bid = Integer.parseInt(bulletinId);
        List<UserAndBulletin> userAndBulletinList = userAndBulletinService.selectBulletinByUserIdLikeBid(userId, bid);
        Map<Bulletin,String> bulletinMap = new LinkedHashMap<>();
        for (UserAndBulletin userAndBulletin : userAndBulletinList){
            Integer bId = userAndBulletin.getBid();
            Bulletin bulletin = bulletinService.getBulletinByPrimaryKey(bId);
            if (userAndBulletin.getState() == 0){
                bulletinMap.put(bulletin,"??????");
            }
            else {
                bulletinMap.put(bulletin,"??????");
            }
        }
        model.addAttribute("bulletins",bulletinMap);
        String name = userService.getNameById(userId);
        model.addAttribute("name", name);
        model.addAttribute("userId",userId);
        System.out.println(name);
        boolean hasOffMessage = false;
        model.addAttribute("hasOffMessage",hasOffMessage);
        return "userBulletinList";
    }

    @GetMapping("/user/update/{id}")
    public String delete(@PathVariable Integer id){
        userAndBulletinService.updateUBState(userId,id,1);
        return "redirect:/user/bulletinList";
    }

}
