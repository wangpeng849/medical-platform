package com.ibo.medical_platform.controller.system;

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
public class SystemController {

    @RequestMapping("/modifyUser")
    public String modifyUser(){
        return "success!";
    }
}
