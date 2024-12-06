package com.bean;
public class Customer {

	private int billNumber;
	private Long consumerId; 		//Primary key and 13 digits
	private String title;
	private String customerName; 	
	private String email; 
	private String password; 
	private String userId;
	private String countryCode; 
	private String mobileNo;
	private String passwordHint;
	public Customer(int billNumber, Long consumerId, String title, String customerName, String email, String password,
			String userId, String countryCode, String mobileNo, String passwordHint) {
		super();
		this.billNumber = billNumber;
		this.consumerId = consumerId;
		this.title = title;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.countryCode = countryCode;
		this.mobileNo = mobileNo;
		this.passwordHint = passwordHint;
	}
	public int getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}
	public Long getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	
	
}
