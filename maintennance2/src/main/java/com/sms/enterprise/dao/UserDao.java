package com.sms.enterprise.dao;

import java.util.List;

import com.sms.enterprise.beans.UserBean;
import com.sms.enterprise.model.Role;
import com.sms.enterprise.model.User;

public interface UserDao {
	
	public List<User> findAll();
	public User findUser(int id);
	public void delete(int id);
	public void save(User user);
	public void updateuser(UserBean userbean);
	public User findUSerByEmail(String email);
	public void saveUser(User user);
	public List<User> findUserByRole(List<Role> role);
	public User findUserByUnitNo(String unitno);

}
