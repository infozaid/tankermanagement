package com.sms.enterprise.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sms.enterprise.model.Task;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public List<Task> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Task> taskList=session.createQuery("from task").list();
		return taskList;
	}

	@Override
	public Task findTask(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Task task=(Task) session.get(Task.class, id);
		return task;
	}

	@Override
	public void save(Task task) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(task);
		
	}

	@Override
	public void delete(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Task task=(Task) session.load(Task.class, new Integer(id));
		if(null !=task) {
			session.delete(task);
		}
		
	}
	
	@Override
	public int findMaxtaskId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT MAX(task_id+1) from UserTask where task.id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int results = (int) query.uniqueResult();
		return results;
	}

}
