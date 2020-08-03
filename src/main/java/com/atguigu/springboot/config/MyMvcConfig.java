package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableWebMvc //这个注解可以全面接管Spring MVC，然后就不能自动访问静态资源下的index.html
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //浏览器发送/atguigu请求来到success

        //这个配置直接去templates文件夹下找success.html，而不走controller文件夹下的逻辑文件
        registry.addViewController("/atguigu").setViewName("success");
    }

    //@Bean之后所有的WebMvcConfigurerAdapter都会一起起作用
    @Bean//将组件注册在拦截器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               // super.addInterceptors(registry);
                // 静态资源；*.css *.js
                //Spring Boot已经做好了静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login");

            }
        };//匿名内部类必须有分号
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }



}
