package com.xiaobai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MvcController {

    @RequestMapping("/testRest")
    @ResponseBody
    public String testRest() {
        return "Hello,MVC!testRest!!";
    }

    @RequestMapping("/login")//这是Spring Security配置的登录页面地址，需要SpringMVC配合完成页面跳转
    public String login() {
        return "login";
    }//有了SpringMVC配置，这里到达login.jsp

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "Hello,admin!";
    }

    @RequestMapping("/db")
    @ResponseBody
    public String db() {
        return "Hello,db!";
    }
}
