package com.ibo.medical_platform.article.controller;

import com.ibo.medical_platform.article.service.SystemManagerService;
import com.ibo.medical_platform.common.result.ServiceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author farling
 * @Date 2019/12/3
 *
 * 系统管理模块
 */

@RestController
@RequestMapping("/sys")
@Api(value = "系统管理权限测试接口",tags = {"系统管理权限测试接口"})
public class SystemController {


    @Autowired
    SystemManagerService systemManagerService;

    @ApiOperation(value="修改用户",tags={"sys接口"},notes="wangp")
    @GetMapping("/modifyUser")
    public String modifyUser(){
        return "success!";
    }

    @ApiOperation(value="修改密码",tags={"sys接口"},notes="wangp")
    @GetMapping("/editPassword")
    public ServiceResult editPassword(@ApiParam(name="username",value="用户名",required = true) @RequestParam("username") String username,
                                      @ApiParam(name="password",value="密码",required = true) @RequestParam("password") String password){
        String brcyptPwd = new BCryptPasswordEncoder().encode(password);
        return systemManagerService.editPassword(username,brcyptPwd);
    }
}
