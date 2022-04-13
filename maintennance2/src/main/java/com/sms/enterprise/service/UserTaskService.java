package com.sms.enterprise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.enterprise.dao.UserTaskDao;
import com.sms.enterprise.model.Task;
import com.sms.enterprise.model.User;
import com.sms.enterprise.model.UserTask;


@Service
@Transactional
public class UserTaskService {

	@Autowired
	UserTaskDao userTaskDao;

	public UserTaskService(UserTaskDao userTaskDao) {
		this.userTaskDao = userTaskDao;
	}
	
	public List<UserTask> findAll(){
		return userTaskDao.findAll();
	}
	
	public UserTask findUserTask(int id){
		return userTaskDao.findUserTask(id);
	}
	
	public void save(UserTask user_task){
		userTaskDao.save(user_task);
	}
	public void updateUserTask(UserTask userTask) {
		userTaskDao.updateUserTask(userTask);
	}
	
	public void delete(int id){
		userTaskDao.delete(id);

	}

	public List<UserTask> findByTask(int task) {
		return userTaskDao.findByTask(task);
	}
	public List<UserTask> findByUser(User user) {
		return userTaskDao.findByUser(user);
	}
}
