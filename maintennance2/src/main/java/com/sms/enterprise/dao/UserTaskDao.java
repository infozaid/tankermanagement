package com.sms.enterprise.dao;

import java.util.List;

import com.sms.enterprise.model.Task;
import com.sms.enterprise.model.User;
import com.sms.enterprise.model.UserTask;

public interface UserTaskDao {
	
	public List<UserTask> findAll();
	public UserTask findUserTask(int id);
	public void save(UserTask user_task);
	public void delete(int id);
	public List<UserTask> findByTask(int task);
	public List<UserTask> findByUser(User user);
	public void updateUserTask(UserTask userTask);
	
}
