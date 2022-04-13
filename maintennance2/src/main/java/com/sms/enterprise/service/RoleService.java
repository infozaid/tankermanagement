package com.sms.enterprise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.enterprise.dao.RoleDao;
import com.sms.enterprise.model.Role;


@Service
//("roleService")
@Transactional
public class RoleService {

	
	/*
	 * @Autowired private RoleRepository roleRepository;
	 */
	
	@Autowired
	RoleDao roleDao;
	
	/*
	 * public RoleService(RoleRepository roleRepository) { this.roleRepository =
	 * roleRepository; }
	 */
	
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public List<Role> findAll(){
		return roleDao.findAll();
	}
	
	public Role findRole(int id){
		return roleDao.updateRole(id);
	}
	
	public void save(Role role){
		roleDao.save(role);
	}
	
	public void delete(int id){
		roleDao.deleteRole(id);

	}

	public List<Role> findRole(String role) {
		return roleDao.findRole(role);
	}

}
