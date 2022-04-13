package com.sms.enterprise.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sms.enterprise.beans.UserBean;
import com.sms.enterprise.dao.RoleDao;
import com.sms.enterprise.dao.UserDao;
import com.sms.enterprise.model.Role;
import com.sms.enterprise.model.User;



@Service("userService")
@Transactional
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    
	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 */

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        
        return userDao.findAll();
    }

    public User findUser(int id) {
        return userDao.findUser(id);
    }

    public void delete(int id) {
        userDao.delete(id);

    }

    public void save(User user) {
        userDao.save(user);
    }
    
    public void updateuser(UserBean user) {
        userDao.updateuser(user);
    }

    public void saveUser(User user) {
    	 userDao.saveUser(user);
    }

    public List<User> findUserbyRole(List<Role> role) {
        return userDao.findUserByRole(role);
    }

	public  User findUserByEmail(String email) {
		
		return userDao.findUSerByEmail(email);
	}
	
	public User findUserByUnitNo(String unitno) {
		return userDao.findUserByUnitNo(unitno);
	}
}
