package com.bean;
//This is for BILL TABLE
public class Bill {

	private int billId;  			//primary key //auto generated
	private Long consumerId;       	//foreign key
	private double dueAmount;		// this will be set by admin
	private String month;
	private String paymentApprovedStatus;			//This is for admin approval for payment
	private String paidStatus;	//This is for customer payment section
	public Bill(int billId, Long consumerId, double dueAmount, String month, String paymentApprovedStatus,
			String paidStatus) {
		super();
		this.billId = billId;
		this.consumerId = consumerId;
		this.dueAmount = dueAmount;
		this.month = month;
		this.paymentApprovedStatus = paymentApprovedStatus;
		this.paidStatus = paidStatus;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public Long getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}
	public double getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPaymentApprovedStatus() {
		return paymentApprovedStatus;
	}
	public void setPaymentApprovedStatus(String paymentApprovedStatus) {
		this.paymentApprovedStatus = paymentApprovedStatus;
	}
	public String getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}
	
	
}