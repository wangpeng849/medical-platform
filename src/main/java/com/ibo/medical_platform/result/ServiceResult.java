package com.ibo.medical_platform.result;

import com.ibo.medical_platform.enums.ResultEnum;
import lombok.Data;

import javax.annotation.security.DenyAll;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 20:22
 */
@Data
public class ServiceResult<T> extends CommonResult {
    private T data;
    public ServiceResult(ResultEnum resultEnum, T data){
        super(resultEnum.getCode(),resultEnum.getDescripe());
        this.data = data;
    }
}
