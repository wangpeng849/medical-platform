package com.ibo.medical_platform.article.mapper;

import com.ibo.medical_platform.article.entity.security.Permission;
import com.ibo.medical_platform.article.entity.security.Role;
import com.ibo.medical_platform.article.entity.security.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
	// 查询用户信息
	@Select(" select * from sys_user where username = #{userName}")
	User findByUsername(@Param("userName") String userName);

	// 查询用户的权限
	@Select(" select permission.* from sys_user user inner join sys_user_role user_role " +
			" on user.id = user_role.uid inner join " +
			" sys_role_permission role_permission on user_role.rid = role_permission.rid " +
			" inner join sys_permission permission on role_permission.pid = permission.id where user.username = #{userName};")
	List<Permission> findPermissionByUsername(@Param("userName") String userName);

	// 查询用户的权限
	@Select(" select Role.* from sys_user user inner join sys_user_role user_role " +
			" on user.id = user_role.uid inner join sys_role role on user_role.uid = role.id  " +
			" where user.username = #{userName};")
	List<Role> findRoleByUsername(@Param("userName") String userName);
}
