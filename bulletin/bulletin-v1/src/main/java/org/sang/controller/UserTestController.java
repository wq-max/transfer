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


    private static Map<Integer,Integer> userFreshCunt = new LinkedHashMap<>();

    //private Integer userId;

    //private Boolean isLogin;

    @GetMapping("/userindex")
    public String index(){
        System.out.println("go to login");
        return "index";
    }
    @GetMapping("/logout{id}")
    public String logOut(@PathVariable Integer id){
        userService.exit(id);
        System.out.println("用户：" +id + "退出时，刷新次数：" +userFreshCunt.get(id));
        userFreshCunt.put(id,0);
        return "index";
    }

    @PostMapping(value = "/user/loginIn")
    public String login(String userid, String password){
        System.out.println(userid+"    "+password);
        Integer uid = Integer.parseInt(userid);
        //userId = uid;
        boolean flag = userService.login(uid,password);
        if(flag){
            userFreshCunt.put(uid,0);
            return "redirect:/user/bulletinList/"+uid;
        }else {
            return "loginError";
        }
    }

    @RequestMapping("/user/bulletinList/{id}")
    public String bulletinList(Model model,@PathVariable Integer id){
        int count = userFreshCunt.get(id);
        System.out.println("用户：" + id + "刷新次数：" + count);
        userFreshCunt.put(id,count+1);
        System.out.println(id);
        List<UserAndBulletin> userAndBulletinList = userAndBulletinService.selectBulletinByUserId(id);
        Map<Bulletin,String> bulletinMap = new LinkedHashMap<>();
        for (UserAndBulletin userAndBulletin : userAndBulletinList){
            Integer bId = userAndBulletin.getBid();
            Bulletin bulletin = bulletinService.getBulletinByPrimaryKey(bId);
            System.out.println(bulletin.getId() + " " + bulletin.getMessage());
            if (userAndBulletin.getState() == 0){
                bulletinMap.put(bulletin,"未读");
            }
            else {
                bulletinMap.put(bulletin,"已读");
            }
        }

        model.addAttribute("bulletins",bulletinMap);
        String name = userService.getNameById(id);
        model.addAttribute("name", name);
        //model.addAttribute("userId",userId);
        model.addAttribute("userId",id);
        System.out.println(name);
        //boolean hasOffMessage = redisService.hasUid(userId);
        boolean hasNoReadMessage = userAndBulletinService.hasMessageNoRead(id);
        System.out.println("用户：" + id + "是否有未读公告：" + hasNoReadMessage);
        System.out.println("用户：" + id + "刷新次数：" + count);
        if (count > 0)
            hasNoReadMessage = false;
        System.out.println("用户：" + id + "是否有未读公告：" + hasNoReadMessage);
        model.addAttribute("hasNoReadMessage",hasNoReadMessage);
        boolean hasOffMessage = redisService.hasUid(id);
        if (count > 0)
            hasOffMessage = false;
        model.addAttribute("hasOffMessage",hasOffMessage);
        return "userBulletinList";
    }

    @RequestMapping("/user/{id}/find")
    public String find(Model model, String bulletinId,@PathVariable Integer id){
        Integer bid = Integer.parseInt(bulletinId);
        //List<UserAndBulletin> userAndBulletinList = userAndBulletinService.selectBulletinByUserIdLikeBid(userId, bid);
        List<UserAndBulletin> userAndBulletinList = userAndBulletinService.selectBulletinByUserIdLikeBid(id, bid);
        Map<Bulletin,String> bulletinMap = new LinkedHashMap<>();
        for (UserAndBulletin userAndBulletin : userAndBulletinList){
            Integer bId = userAndBulletin.getBid();
            Bulletin bulletin = bulletinService.getBulletinByPrimaryKey(bId);
            if (userAndBulletin.getState() == 0){
                bulletinMap.put(bulletin,"未读");
            }
            else {
                bulletinMap.put(bulletin,"已读");
            }
        }
        model.addAttribute("bulletins",bulletinMap);
        //String name = userService.getNameById(userId);
        String name = userService.getNameById(id);
        model.addAttribute("name", name);
        //model.addAttribute("userId",userId);
        model.addAttribute("userId",id);
        System.out.println(name);
        boolean hasNoReadMessage = false;
        model.addAttribute("hasNoReadMessage",hasNoReadMessage);
        boolean hasOffMessage = false;
        model.addAttribute("hasOffMessage",hasOffMessage);
        return "userBulletinList";
    }

    @GetMapping("/user/{uid}/update/{bid}")
    public String delete(@PathVariable Integer uid, @PathVariable Integer bid){
        userAndBulletinService.updateUBState(uid,bid,1);
        return "redirect:/user/bulletinList/"+uid;
    }

}
