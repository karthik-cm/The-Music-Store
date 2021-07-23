package com.musicstore.vo;

public class UserVO {
	private int userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private int mobileNo;
	private String emailId;
	private String userName;
	private String password;
	private String isActiveFlag;
	private String role;
	private String lastVisitedPageUrl;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsActiveFlag() {
		return isActiveFlag;
	}
	public void setIsActiveFlag(String isActiveFlag) {
		this.isActiveFlag = isActiveFlag;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLastVisitedPageUrl() {
		return lastVisitedPageUrl;
	}
	public void setLastVisitedPageUrl(String lastVisitedPageUrl) {
		this.lastVisitedPageUrl = lastVisitedPageUrl;
	}
	
}
