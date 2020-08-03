package com.atguigu.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc //这个注解可以全面接管Spring MVC
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //浏览器发送/atguigu请求来到success

        //这个配置直接去templates文件夹下找success.html，而不走controller文件夹下的逻辑文件
        registry.addViewController("/atguigu").setViewName("success");
    }
}
