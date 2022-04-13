package com.sms.enterprise.dao;

import java.util.List;

import com.sms.enterprise.model.Role;

public interface RoleDao {

	public List<Role> findAll();
	// for update role 
	public Role updateRole(int id);
	public void save(Role role);
	public void deleteRole(int id);
	public List<Role> findRole(String role);
	public Role findByRoleUser(String role);
	
}
