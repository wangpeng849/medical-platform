package com.ibo.medical_platform.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class MyPasswordEncoder implements PasswordEncoder {

    @Override //不清楚除了在下面方法用到还有什么用处
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    //判断密码是否匹配
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(this.encode(rawPassword));
    }
}