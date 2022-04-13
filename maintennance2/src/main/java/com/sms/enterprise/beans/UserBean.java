package com.sms.enterprise.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sms.enterprise.model.Order;
import com.sms.enterprise.model.Role;
import com.sms.enterprise.model.UserTask;

public class UserBean {

	
	private int id;
	
	
	private String unitno;
	

	private String phoneno;
	
	
	private String cnic;
	
	
	private String email;
	
	
	private String password;
	
	
	private String name;
	
	
	private int active;
	
	
	private String livingresidence;
	
	
	private String gender;
	
	
	
	private String residence;
	
	

	public UserBean() {
		
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
