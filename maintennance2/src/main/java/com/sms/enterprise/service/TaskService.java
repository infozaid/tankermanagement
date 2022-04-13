package com.sms.enterprise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.enterprise.dao.RoleDao;
import com.sms.enterprise.dao.TaskDao;
import com.sms.enterprise.model.Task;




@Service
@Transactional
public class TaskService {

//	private final TaskRepository taskRepository;
	
	@Autowired
	TaskDao taskDao;

	/*
	 * public TaskService(TaskRepository taskRepository) { this.taskRepository =
	 * taskRepository; }
	 */
	
	public TaskService(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	public List<Task> findAll(){
		return taskDao.findAll();
	}
	
	public Task findTask(int id){
		return taskDao.findTask(id);
	}
	
	public void save(Task task){
		taskDao.save(task);
	}
	
	public void delete(int id){
		taskDao.delete(id);

	}
}
