package com.sms.enterprise.model;

/**
 * Created by Yasin Mert on 25.02.2017.
 */
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="user_task")
public class UserTask implements Serializable {
	
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "TASK_ID", referencedColumnName = "id")
    private Task task;
	
	@Column(name="task_desc")
	private String task_desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getTask_desc() {
		return task_desc;
	}

	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}


    
}
