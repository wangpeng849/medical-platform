package com.ibo.medical_platform.article.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author farling
 * @Date 2019/12/3
 *
 * 用户权限控制测试controller
 */
@RestController
public class UserController {

    @GetMapping("/public/user")
    public String testApi(){
        return "success";
    }

    @PostMapping("/public/user")
    public String testApi2(){
        return "success";
    }

    @GetMapping("/user")
    public String testApi3(){
        return "success";
    }

    @GetMapping("/public/login")
    public String testApi4(){
        return "login success";
    }

    @GetMapping("/vipUser/user")
    public String testApi5(){
        return "vip user success";
    }
}
