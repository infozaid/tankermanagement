package com.sms.enterprise.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sms.enterprise.beans.UserBean;
import com.sms.enterprise.model.Role;
import com.sms.enterprise.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	RoleDao roleDao;
	@Override
	public List<User> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList=session.createQuery("from User").list();
		return userList;
	}

	@Override
	public User findUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user=(User) session.get(User.class, id);
		return user;
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user=(User) session.load(User.class, new Integer(id));
		if(null !=user) {
			session.delete(user);
		}
		
	}

	@Override
	public void save(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	@Override
	public void updateuser(UserBean user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.evict(user);
		session.update(user);
		
	}

	@Override
	public User findUSerByEmail(String email) {
		User userDetails = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		List<User> entityList = criteria.list();
		if(!entityList.isEmpty()) {
			userDetails = entityList.get(0);
		}
		return userDetails;
	}
	
	@Override
	public User findUserByUnitNo(String unitno) {
		User user = null;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("unitno", unitno));
		List<User> entityList = criteria.list();
		if(!entityList.isEmpty()) {
			user = entityList.get(0);
		}
		return user;
	}

	@Override
	public void saveUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole=roleDao.findByRoleUser("USER");
		user.setRole(userRole);
		session.save(user);
	}

	@Override
	public List<User> findUserByRole(List<Role> role) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User u where u.role=:role");
		query.setParameter("role", role);
		List list=query.list();
		return list;
	}

	
}
