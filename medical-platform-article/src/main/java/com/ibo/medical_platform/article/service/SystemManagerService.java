package com.ibo.medical_platform.article.service;

import com.ibo.medical_platform.common.result.ServiceResult;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 19:46
 */

public interface SystemManagerService {
    ServiceResult editPassword(String username, String password);
}
