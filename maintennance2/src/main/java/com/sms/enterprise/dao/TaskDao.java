package com.sms.enterprise.dao;

import java.util.List;

import com.sms.enterprise.model.Task;

public interface TaskDao {
	
	public List<Task> findAll();
	public Task findTask(int id);
	public void save(Task task);
	public void delete(int id);
	public  int findMaxtaskId(int id);
		
	
	
}
