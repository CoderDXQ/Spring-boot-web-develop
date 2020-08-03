package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

   // @RequestMapping(value = "/user/Login",method = RequestMethod.POST)
//    @DeleteMapping
//    @GetMapping
//    @PutMapping
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);//定义并设置属性，以后用属性进行判断
            return "redirect:/main.html";//重定向
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
