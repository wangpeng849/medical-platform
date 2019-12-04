package com.ibo.medical_platform.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 20:24
 */
public enum ResultEnum {
    SUCCESS(0,"success"),
    MODITY_PASSWORD_FAIL(1,"modify password fail..")
    ;
    private Integer code;
    private String descripe;

    public Integer getCode() {
        return code;
    }

    public String getDescripe() {
        return descripe;
    }

    ResultEnum(Integer code, String descripe) {
        this.code = code;
        this.descripe = descripe;
    }
}
