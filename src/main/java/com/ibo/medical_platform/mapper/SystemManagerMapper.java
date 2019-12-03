package com.ibo.medical_platform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.parameters.P;

/**
 * @author farling-wangp
 * @version 1.0
 * @date 2019/12/3 19:49
 */
@Mapper
public interface SystemManagerMapper {

    @Update("update sys_user set password = #{password} where username=#{username}")
    int editPassword(@Param("username") String username, @Param("password") String password);
}
