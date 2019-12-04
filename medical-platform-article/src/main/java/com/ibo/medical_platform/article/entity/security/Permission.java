package com.ibo.medical_platform.article.entity.security;

import lombok.Data;

/**
 * 用户权限
 */
@Data
public class Permission {
	private Integer id;
	// 权限名称
	private String permissionName;
//	// 权限标识
//	private String permissionTag;
	// 请求url
	private String permissionUrl;
}