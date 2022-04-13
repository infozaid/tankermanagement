package com.sms.enterprise.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String order_name;
	
	@Column
	private String unitno;
	
	@Column
	private String address;
	
	@Column
	private String phoneno;
	
	@Column(name="username")
	private String name;
	
	@Column
	private String message;
	
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Temporal(TemporalType.DATE)
	@Column(name="date_created")
	private String dateCreated;
	private boolean finished;
	
	private boolean pendingrequest;
	
	private int gallons;
	
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "id")
	private User user;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getOrder_name() {
		return order_name;
	}



	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}



	public String getUnitno() {
		return unitno;
	}



	public void setUnitno(String unitno) {
		this.unitno = unitno;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}




	public String getPhoneno() {
		return phoneno;
	}



	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}



	public String getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(String string) {
		this.dateCreated = string;
	}



	public boolean isFinished() {
		return finished;
	}



	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	
    public boolean isPendingrequest() {
		return pendingrequest;
	}



	public void setPendingrequest(boolean pendingrequest) {
		this.pendingrequest = pendingrequest;
	}
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getGallons() {
		return gallons;
	}



	public void setGallons(int gallons) {
		this.gallons = gallons;
	}
	
	
	
	
	

}
