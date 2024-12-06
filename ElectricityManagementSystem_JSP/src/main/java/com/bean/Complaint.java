package com.bean;

public class Complaint {
	private String complaintId;   	//auto generated and primary key
	private String complaintType;
	private String category;
	private String landmark;
	private String customerName;
	private String problem;
	private long consumerId;		// foreign key and 13 digits
	private String address;
	private String mobileNumber;
	private String status;			//This is updated by admin
	private String complaintFeedback; //Customer will provide feedback and admin can view the feedback
	public Complaint(String complaintId, String complaintType, String category, String landmark, String customerName,
			String problem, long consumerId, String address, String mobileNumber, String status,
			String complaintFeedback) {
		super();
		this.complaintId = complaintId;
		this.complaintType = complaintType;
		this.category = category;
		this.landmark = landmark;
		this.customerName = customerName;
		this.problem = problem;
		this.consumerId = consumerId;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.status = status;
		this.complaintFeedback = complaintFeedback;
	}
	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public long getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(long consumerId) {
		this.consumerId = consumerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComplaintFeedback() {
		return complaintFeedback;
	}
	public void setComplaintFeedback(String complaintFeedback) {
		this.complaintFeedback = complaintFeedback;
	}
	
	
}