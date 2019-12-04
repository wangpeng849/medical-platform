package com.ibo.medical_platform.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 20:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private Integer code;
    private String errorMsg;
}
