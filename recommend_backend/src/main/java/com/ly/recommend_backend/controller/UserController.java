package com.ly.recommend_backend.controller;

import com.ly.recommend_backend.entity.UserP;
import com.ly.recommend_backend.service.UserServiceP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceP userService;

    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap login(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelMap model = new ModelMap();
        // 查询用户数据
        UserP user = new UserP(username, password);
        ModelMap query = userService.login(user);
        if((boolean) query.get("result")) {
            model.addAttribute("success", true);
            model.addAttribute("user", query.get("user"));
        } else {
            model.addAttribute("success", false);
            model.addAttribute("msg", query.get("msg"));
        }
        return model;
    }

    @RequestMapping(value = "/register", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap register(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelMap model = new ModelMap();
        // 查询用户数据
        UserP user = new UserP(username, password);
        UserP user2 = userService.findByName(username);
        if(user2 != null) {
            model.addAttribute("success", false);
            model.addAttribute("msg", "用户已存在");
        } else {
            model.addAttribute("success", true);
            user.setTimestamp(System.currentTimeMillis());
            UserP res = userService.add(user);
            model.addAttribute("user", res);
            model.addAttribute("msg", "注册成功");
        }
        return model;
    }
}
