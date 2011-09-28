package com.rixon.lms.dao.mapper;

import com.rixon.lms.dao.resultset.RoleRS;
import com.rixon.lms.domain.Role;
import com.rixon.lms.domain.Role.RoleBuilder;

public class RoleMapper {
	
	public static Role mapToRole(RoleRS roleRS) {
		RoleBuilder roleBuilder = new RoleBuilder();
		roleBuilder.setId(roleRS.getId());
		roleBuilder.setRole(roleRS.getRole());
		roleBuilder.setDescription(roleRS.getDescription());
		return roleBuilder.createRole();
	}
}
