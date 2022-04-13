package com.sms.enterprise.beans;

import java.util.List;



public class UserDetail {

	// Attributes
	////////////////
	private String usrName;
	private String usrNatId;
	private String usrDob;
	private String usrEmail;
	private String userContact;
	

	// Helper Properties
	private String imagePath;

	// Properties
	///////////////
	/**
	 * @return the usrName
	 */
	public String getUsrName() {
		return usrName;
	}

	/**
	 * @param usrName the usrName to set
	 */
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	/**
	 * @return the usrNatId
	 */
	public String getUsrNatId() {
		return usrNatId;
	}

	/**
	 * @param usrNatId the usrNatId to set
	 */
	public void setUsrNatId(String usrNatId) {
		this.usrNatId = usrNatId;
	}

	/**
	 * @return the usrDob
	 */
	public String getUsrDob() {
		return usrDob;
	}

	/**
	 * @param usrDob the usrDob to set
	 */
	public void setUsrDob(String usrDob) {
		this.usrDob = usrDob;
	}

	/**
	 * @return the usrEmail
	 */
	public String getUsrEmail() {
		return usrEmail;
	}

	/**
	 * @param usrEmail the usrEmail to set
	 */
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	/**
	 * @return the userContact
	 */
	public String getUserContact() {
		return userContact;
	}

	/**
	 * @param userContact the userContact to set
	 */
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	/**
	 * @return the address
	 */
	

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
