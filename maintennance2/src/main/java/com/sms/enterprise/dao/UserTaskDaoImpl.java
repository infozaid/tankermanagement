package com.sms.enterprise.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.sms.enterprise.model.Task;
import com.sms.enterprise.model.User;
import com.sms.enterprise.model.UserTask;

@Repository
public class UserTaskDaoImpl implements UserTaskDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	TaskDao taskDao;
	
	
	
	@Override
	public List<UserTask> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserTask> userTaskList=session.createQuery("from  UserTask").list();
		return userTaskList;
	}

	@Override
	public UserTask findUserTask(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserTask user_task=(UserTask) session.get(UserTask.class, id);
		return user_task;
	}

	@Override
	public void save(UserTask user_task) {
		Session session = this.sessionFactory.getCurrentSession();

		int taskId = taskDao.findMaxtaskId(user_task.getTask().getId());
		
		user_task.setId(taskId);
		

		session.save(user_task);
	}
	public void updateUserTask(UserTask userTask) {
		Session session=this.sessionFactory.getCurrentSession();
		session.update(userTask);
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserTask user_task=(UserTask) session.load(UserTask.class, new Integer(id));
		if(null!=user_task) {
			session.delete(user_task);
		}
	}

	@Override
	public List<UserTask> findByTask(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserTask> userTaskList=(List<UserTask>) session.get(UserTask.class, id);
		return userTaskList;
	}
	
	

	@Override
	public List<UserTask> findByUser(User user) {
		int id=0;
		Session session = this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from UserTask u where u.user=:user");
		query.setParameter("user", user);
		List list=query.list();
		return list;
	}

	

}
