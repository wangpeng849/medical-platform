package com.ibo.medical_platform.article.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value="用户权限控制测试",tags = {"用户权限testController"})
@RestController
public class UserController {

    @ApiOperation(value="Get公共接口",tags={"Get公共接口tag"},notes="wangp")
    @GetMapping("/public/user")
    public String testApi(){
        return "success";
    }

    @ApiOperation(value="Post公共接口",tags={"Post公共接口tag"},notes="wangp")
    @PostMapping("/public/user")
    public String testApi2(){
        return "success";
    }

    @ApiOperation(value="非public接口",tags={"非public接口"},notes="wangp")
    @GetMapping("/user")
    public String testApi3(){
        return "success";
    }

    @ApiOperation(value="登录成功返回接口",tags={"非public接口"},notes="wangp")
    @GetMapping("/login/suc")
    public String testApi4(){
        return "login success";
    }

    @ApiOperation(value="权限控制接口",tags={"非public接口"},notes="wangp")
    @GetMapping("/vipUser/user")
    public String testApi5(){
        return "vip user success";
    }

    @ApiOperation(value="退出接口",notes="wangp")
    @GetMapping("/logoutSuccess")
    public String testApi6(){
        return " user logout success";
    }

    @ApiOperation(value="登录失败接口",notes="wangp")
    @PostMapping("/failed")
    public String testApi7(){
        return " user login failed";
    }
}
