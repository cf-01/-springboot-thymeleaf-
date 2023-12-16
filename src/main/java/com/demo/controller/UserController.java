package com.demo.controller;


import com.demo.pojo.User;
import com.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/checkUsername")
    @ResponseBody
    public Map<String, Object> getUsername(@RequestParam("username") String username) {
        return userService.getUsername(username);
    }

    @PostMapping("/login")
    public String login(User user, Model model, HttpServletRequest servletRequest){
        User user1 = userService.getUser(user);
        if (user1 != null) {
            servletRequest.getSession().setAttribute("user",user1);
            model.addAttribute("user",user1);
            model.addAttribute("msg","登录成功！");
            return "redirect:/product/page/1";
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "index";
    }

    @PostMapping("/register")
    public String register(User user){
        int i = userService.addUser(user);
        if (i>0) {
            return "index";
        }else {
            return "redirect:/register";
        }
    }


}
