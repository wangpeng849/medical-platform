package com.ibo.medical_platform.article.entity.security;

import lombok.Data;

/**
 * 用户角色
 */
@Data
public class Role {
	private Integer id;
	private String roleName;
	private String roleDesc;
}