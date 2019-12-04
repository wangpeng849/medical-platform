package com.ibo.medical_platform.article.service;

import com.ibo.medical_platform.article.entity.security.Permission;
import com.ibo.medical_platform.article.entity.security.Role;
import com.ibo.medical_platform.article.entity.security.User;
import com.ibo.medical_platform.article.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	// 查询用户信息
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 根据用户查询用户信息
		User user = userMapper.findByUsername(username);
		if(user==null) {
			//抛出错误，用户不存在
			throw new UsernameNotFoundException("用户名 "+username+"不存在");
		}
		// 根据用户查询用户对应权限
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Permission> listPermission = userMapper.findPermissionByUsername(username);
		for (Permission permission : listPermission) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
		}
		List<Role> listRole = userMapper.findRoleByUsername(username);
		for (Role role : listRole) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		// 设置用户权限
		user.setAuthorities(authorities);
		return user;
	}
}
