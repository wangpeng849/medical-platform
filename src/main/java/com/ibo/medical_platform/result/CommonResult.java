package com.ibo.medical_platform.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 20:19
 */
@Data
@AllArgsConstructor
public class CommonResult {
    private Integer code;
    private String errorMsg;
}
