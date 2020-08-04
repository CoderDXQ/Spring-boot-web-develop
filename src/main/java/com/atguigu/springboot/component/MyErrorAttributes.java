package com.atguigu.springboot.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;

//给容器中加入我们自己定义的ErrorAttributes
//Spring Boot2.0以上直接使用WebRequest
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

   //返回值的map就是页面和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,ErrorAttributeOptions options) {
        Map<String,Object> map = super.getErrorAttributes(webRequest,options);
        //自定义数据
        map.put("company","atguigu");

        //我们的异常处理器携带的数据 这里要加一个强制类型转换
        Map<String,Object> ext = (Map<String,Object>) webRequest.getAttribute("ext",0);
        map.put("ext",ext);

        return map;
    }
}
