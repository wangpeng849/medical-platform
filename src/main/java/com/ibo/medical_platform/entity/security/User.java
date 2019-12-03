package com.ibo.medical_platform.entity.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author farling
 * @Date 2019/12/3
 *
 *   验证用户
 */
@Data
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String realName;
    private String password;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    // 用户所有权限
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
