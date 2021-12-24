package org.sang.controller;

import org.sang.model.Bulletin;
import org.sang.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminTestController {

    @Autowired
    BulletinService bulletinService;

    @GetMapping("/adminindex")
    public String index(){
        System.out.println("go to login");
        return "adminIndex";
    }

    @GetMapping("/adminOut")
    public String out(){
        System.out.println("go to login add");
        return "adminIndex";
    }

    @PostMapping(value = "/admin/loginIn")
    public String login(String username, String password){
        System.out.println(username+"    "+password);
        if (username.equals("admin") && password.equals("123")){
            return "redirect:/admin/bulletinList";
        }
        else
            return "error";

    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable Integer id){
        bulletinService.deleteBulletinByPrimaryKey(id);
        return "redirect:/admin/bulletinList";
    }

    @RequestMapping("/admin/insertPage")
    public String insertPage(){
        return "insertPage";
    }

    @RequestMapping("/admin/insert")
    public String insert(Bulletin bulletin){
        bulletinService.insertBulletin(bulletin);
        return "redirect:/admin/bulletinList";
    }

    @GetMapping("/admin/updatePage/{id}")
    public String updatePage(Model model, @PathVariable int id){
        System.out.println(id);
        Bulletin bulletin = bulletinService.getBulletinByPrimaryKey(id);
        model.addAttribute("bulletin",bulletin);
        return "updatePage";
    }

    @PostMapping("/admin/update")
    public String update(Bulletin bulletin){
        System.out.println(bulletin.getId() + " " + bulletin.getMessage());
        bulletinService.updateBulletinByPrimaryKey(bulletin);
        return "redirect:/admin/bulletinList";
    }

    @RequestMapping("/admin/bulletinList")
    public String bulletinList(Model model){
        List<Bulletin> bulletinList = bulletinService.getAllBulletins();
        model.addAttribute("bulletins",bulletinList);
        return "bulletinList";
    }

    @RequestMapping("/admin/find")
    public String bulletinFind(Model model, String bulletinId){
        Integer bid = Integer.parseInt(bulletinId);
       List<Bulletin> bulletinList = bulletinService.getBulletinByLikePrimaryKey(bid);
        model.addAttribute("bulletins",bulletinList);
        return "bulletinList";
    }

}
