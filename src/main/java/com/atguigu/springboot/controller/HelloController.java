package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

//    @RequestMapping{("/","/index.html")}
//    public String index(){
//        return "index";
//    }

    @ResponseBody
    @RequestMapping("/hello") //对url的地址后面的东西进行映射，映射到下面具体的某个函数
    public String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "Hello World";
    }

    @RequestMapping("/success") //对url的地址后面的东西进行映射，映射到下面具体的某个函数
    public String success(Map<String,Object> map){

        //classpath:/template/success.html
        map.put("hello","你好");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }
}
