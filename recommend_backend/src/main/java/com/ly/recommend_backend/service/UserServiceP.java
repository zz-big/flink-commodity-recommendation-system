package com.ly.recommend_backend.service;


import com.ly.recommend_backend.dao.UserInterface;
import com.ly.recommend_backend.entity.UserP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import javax.annotation.Resource;

@Service
public class UserServiceP {

    @Autowired
    private UserInterface userInterface;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserP add(UserP user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserP user1 = userInterface.save(user);
        return user1;
    }

    public ModelMap login(UserP user) {
        ModelMap modelMap = new ModelMap();
        UserP user2 = userInterface.getUserByName(user.getName());
        if(user2 == null) {
            modelMap.addAttribute("result", false);
            modelMap.addAttribute("msg", "用户不存在");
            return modelMap;
        }
        if(!bCryptPasswordEncoder.matches(user.getPassword(), user2.getPassword())) {
            modelMap.addAttribute("result", false);
            modelMap.addAttribute("msg", "密码错误");
            return modelMap;
        }
        modelMap.addAttribute("result", true);
        modelMap.addAttribute("user", user2);
        return modelMap;
    }

    public UserP findByName(String name) {
        UserP user2 = userInterface.getUserByName(name);
        if(user2 == null) {
            return null;
        }
        return user2;
    }
}
