package com.ibo.medical_platform.controller.system;

import com.ibo.medical_platform.result.ServiceResult;
import com.ibo.medical_platform.service.SystemManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @Author farling
 * @Date 2019/12/3
 *
 * 系统管理模块
 */
@RestController
@RequestMapping("/sys")
public class SystemController {

    @Autowired
    SystemManagerService systemManagerService;

    @GetMapping("/modifyUser")
    public String modifyUser(){
        return "success!";
    }

    @GetMapping("/editPassword")
    public ServiceResult editPassword(@RequestParam("username") String username, @RequestParam("password") String password){
        return systemManagerService.editPassword(username,password);
    }
}
