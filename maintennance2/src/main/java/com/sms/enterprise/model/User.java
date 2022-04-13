package com.sms.enterprise.model;

import java.io.Serializable;

/**
 * Created by Yasin Mert on 25.02.2017.
 */

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User{

	@Id
	@GeneratedValue  (strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="unitno")
	@Length(min=2,max=14,message="*your unitno no must be atleast 11 nos!")
	@NotEmpty(message = "*Please provide a Unit No! This field can not be empty!")
	private String unitno;
	
	@Column(name="phoneno")
	@Length(min=11,max=14,message="*your phone no must be atleast 11 nos!")
	@NotEmpty(message = "*Please provide a Phone or Cell No ! This field can not be empty!")
	private String phoneno;
	
	@Column(name="cnic")
	@Length(min=13,max=15,message="*Your cnic no must be atleast 13 numbers!")
	@Pattern(regexp = "^[0-9]{5}-[0-9]{7}-[0-9]{1}$", message="CNIC No must follow XXXXX-XXXXXXX-X format!")
	@NotEmpty(message = "*Please provide a CNIC! This field can not be empty!")
	private String cnic;
	
	@Column(name = "email")
	@Length(min=13,max=50,message="*Please enter a valid email adress!")
	@Email(message = "*Please enter a valid email adress!")
	@NotEmpty(message = "*Please provide an email! This field can not be empty!")
	private String email;
	
	@Column(name = "password")
	@Length(min = 3, message = "*Your password can not be less than 3 characters!")
	@NotEmpty(message = "*Please provide your password! This field can not be empty!")
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name! This field can not be empty!")
	private String name;
	
	@Column(name = "active")
	private int active;
	
	@Column(name="livingresidence")
	@NotEmpty(message = "*Please provide No of people! This field can not be empty!")
	@Length(min = 1,max=15, message = "*Your residence_no can not be less than 1 characters!")
	private String livingresidence;
	
	@Column(name="gender")
	@NotEmpty(message = "*Please provide gender! This field can not be empty!")
	private String gender;
	
	
	@Column(name="residence")
	@NotEmpty(message = "*Please select any one of them ! This field can not be empty!")
	private String residence;
	
	@ManyToOne
	private Role role;
	
	@OneToMany(mappedBy = "user")
	private Set<UserTask> userTask = new HashSet<UserTask>();
	
	@OneToMany(mappedBy = "user")
	private Set<Order> order = new HashSet<Order>();

	public User() {
		
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public Set<UserTask> getUserTask() {
		return userTask;
	}

	public void setUserTask(Set<UserTask> userTask) {
		this.userTask = userTask;
	}
	
	

	public Set<Order> getOrder() {
		return order;
	}



	public void setOrder(Set<Order> order) {
		this.order = order;
	}



	public String getUnitno() {
		return unitno;
	}



	public void setUnitno(String unitno) {
		this.unitno = unitno;
	}



	public String getPhoneno() {
		return phoneno;
	}



	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}



	public String getCnic() {
		return cnic;
	}



	public void setCnic(String cnic) {
		this.cnic = cnic;
	}



	public String getLivingresidence() {
		return livingresidence;
	}



	public void setLivingresidence(String livingresidence) {
		this.livingresidence = livingresidence;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getResidence() {
		return residence;
	}



	public void setResidence(String residence) {
		this.residence = residence;
	}
	
	
	
	
	
	

}
