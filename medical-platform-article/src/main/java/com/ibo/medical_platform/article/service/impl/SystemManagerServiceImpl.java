package com.ibo.medical_platform.article.service.impl;


import com.ibo.medical_platform.article.mapper.SystemManagerMapper;
import com.ibo.medical_platform.article.service.SystemManagerService;
import com.ibo.medical_platform.common.enums.ResultEnum;
import com.ibo.medical_platform.common.result.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 19:47
 */

@Service
public class SystemManagerServiceImpl implements SystemManagerService {

    @Autowired
    SystemManagerMapper systemManagerMapper;

    @Override
    public ServiceResult editPassword(String username, String password) {
        int result = systemManagerMapper.editPassword(username, password);
        return new ServiceResult(result==1? ResultEnum.SUCCESS:ResultEnum.MODITY_PASSWORD_FAIL,null);
    }
}
