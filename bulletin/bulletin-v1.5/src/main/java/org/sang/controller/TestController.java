package org.sang.controller;

import org.sang.model.Bulletin;
import org.sang.model.UserAndBulletin;
import org.sang.service.BulletinService;
import org.sang.service.UserAndBulletinService;
import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    UserService userService;

    @Autowired
    BulletinService bulletinService;

    @Autowired
    UserAndBulletinService userAndBulletinService;

    @GetMapping("/test")
    public void test(){
        Integer uid = 21172111;
        String pwd = "123";
        if (userService.login(uid,pwd)){
            String name = userService.getNameById(uid);
            System.out.println(name + "登录成功！");
        }
        List<UserAndBulletin> ubList = userAndBulletinService.selectBulletinByUserId(uid);
        printMyBulletins(ubList);


        System.out.println("添加一个公告，并推送到给用户");
        Integer bid = 4;
        String bMessage = "接到最新消息元旦放假三天";
        Bulletin bulletin = new Bulletin();
        bulletin.setId(bid);
        bulletin.setMessage(bMessage);
        bulletinService.insertBulletin(bulletin);
        userAndBulletinService.insertUserAndBulletin(uid, bid);

        ubList = userAndBulletinService.selectBulletinByUserId(uid);
        printMyBulletins(ubList);

        System.out.println("修改一个公告");
        bulletin.setMessage("公告修改：元旦不放啦");
        bulletinService.updateBulletinByPrimaryKey(bulletin);

        ubList = userAndBulletinService.selectBulletinByUserId(uid);
        printMyBulletins(ubList);

        System.out.println("删除一个公告");
        bulletinService.deleteBulletinByPrimaryKey(bid);

        ubList = userAndBulletinService.selectBulletinByUserId(uid);
        printMyBulletins(ubList);

        System.out.println("查询一个公告");
        Bulletin bulletin1 = bulletinService.getBulletinByPrimaryKey(1);
        System.out.println("公告 " + bulletin1.getId() + ": " + bulletin1.getMessage());


        System.out.println("查询所有公告");
        List<Bulletin> bulletins = bulletinService.getAllBulletins();
        for (Bulletin bulletin2 : bulletins){
            System.out.println("公告 " + bulletin2.getId() + ": " + bulletin2.getMessage());
        }







        userService.exit(uid);
    }

    public void printMyBulletins(List<UserAndBulletin> ubList){
        System.out.println("查看我的公告");
        for (UserAndBulletin ub : ubList){
            Bulletin bulletin = bulletinService.getBulletinByPrimaryKey(ub.getId());
            Integer bid = bulletin.getId();
            String message = bulletin.getMessage();
            if (ub.getState() == 1)
                System.out.println("已读公告 "+bid+"：" + message);
            else{
                System.out.println("未读公告 "+bid+"：" + message);
            }
        }
    }
}
