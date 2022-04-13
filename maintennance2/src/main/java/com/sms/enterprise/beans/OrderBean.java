package com.sms.enterprise.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.sms.enterprise.model.User;

public class OrderBean {


	
	private int id;
	
	
	private String order_name;
	
	
	private String unitno;
	
	
	private String address;
	
	
	private String phoneno;
	
	
	private String name;
	
	
	private String message;
	
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Temporal(TemporalType.DATE)
	
	@DateTimeFormat(pattern = "dd/MM/yyyy h:mm ")
	private Date dateCreated;
	private boolean finished;
	
	public String fromDate;
	public String toDate;
	
	private boolean pendingrequest;
	
	
	
	
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



	public Date getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}



	public String getFromDate() {
		return fromDate;
	}



	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}



	public String getToDate() {
		return toDate;
	}



	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	
	
	
}
