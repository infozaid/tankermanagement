package com.sms.enterprise.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sms.enterprise.model.Role;
import com.sms.enterprise.model.User;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Role> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roleList=session.createQuery("from Role").list();
		return roleList;
	}

	@Override
	public Role updateRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Role role= (Role) session.get(Role.class, id);
		return role;
	}

	@Override
	public void save(Role role) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(role);
	}

	@Override
	public void deleteRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Role p=(Role) session.load(Role.class, new Integer(id));
		
		if(null !=p) {
			session.delete(p);
		}
		
	}

	@Override
	public List<Role> findRole(String role) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Role where role=:role");
		query.setParameter("role", role);
		List<Role> roleList=query.list();
		return roleList;
	}

	@Override
	public Role findByRoleUser(String role) {
		Session session = this.sessionFactory.getCurrentSession();
		Role roleDetails = null;
		Criteria criteria = sessionFactory.openSession().createCriteria(Role.class);
		criteria.add(Restrictions.eq("role", role));
		List<Role> entityList = criteria.list();
		if(!entityList.isEmpty()) {
			roleDetails = entityList.get(0);
		}
		return roleDetails;
		
	}
	
	

}
