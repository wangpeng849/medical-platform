package com.ibo.medical_platform.common.result;

import com.ibo.medical_platform.common.enums.ResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 20:22
 */
@Data
public class ServiceResult<T> extends CommonResult {
    private Integer code;
    private String errorMsg;
    private T data;
    public ServiceResult(ResultEnum resultEnum, T data){
        //super();
        this.code = resultEnum.getCode();
        this.errorMsg = resultEnum.getDescripe();
        this.data = data;
    }
}
