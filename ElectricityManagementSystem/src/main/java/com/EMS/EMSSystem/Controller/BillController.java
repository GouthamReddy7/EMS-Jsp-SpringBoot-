package com.EMS.EMSSystem.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EMS.EMSSystem.Service.BillService;
import com.EMS.EMSSystem.Service.ComplaintService;
import com.EMS.EMSSystem.Service.CustomerService;
import com.EMS.EMSSystem.bean.Bill;
import com.EMS.EMSSystem.bean.Complaint;
import com.EMS.EMSSystem.bean.Customer;
import com.google.gson.Gson;

@RestController
public class BillController {
	@PostMapping
	@RequestMapping("/billRegistration")

	public ResponseEntity<String> registerBill(@RequestBody Bill bill) {
//		System.out.println("Received Customer Name: " + complaint.getCustomerName());
//		System.out.println("Complaint Type: " + complaint.getComplaintType());
//		System.out.println("Problem: " + complaint.getProblem());
		BillService bs=new BillService();
		boolean b=bs.regBill(bill);
		if(b) {
			return ResponseEntity.ok("Bill registered successfully.");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bill registration failed.");
		}
		
	}
	@RequestMapping("/viewAllBill")
	@PostMapping
	public String viewAllBillList (@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		BillService cs = new BillService();
		List<Bill> c = cs.viewAllBill(customer);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@RequestMapping("/viewBill")
	@PostMapping
	public String viewBill (@RequestBody Bill bill) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		BillService cs = new BillService();
		Bill c = cs.viewBill(bill);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@PostMapping
	@RequestMapping("/unapprovedBillList")
	public String getUnapprovedBillList() {
		BillService cs = new BillService();
		List<Bill> billList = cs.getUnapprovedBillList();
		if (billList != null) {
			Gson gson = new Gson();
			return gson.toJson(billList);
		}
		return null;
	}
	@PostMapping
	@RequestMapping("/unpaidBillList")
	public String getUnpaidCustomerList() {
		BillService cs = new BillService();
		List<Bill> billList = cs.getUnpaidBillList();
		if (billList != null) {
			Gson gson = new Gson();
			return gson.toJson(billList);
		}
		return null;
	}
	@RequestMapping("/updateCustomerPaidStatus")
	@PostMapping
	public ResponseEntity<String> updateCustomerPaidStatus (@RequestBody Bill bill) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		BillService cs = new BillService();
		boolean c = cs.updateCustomerPaidStatus(bill);
		if(c) {
			return ResponseEntity.ok("Customer Paid status updated successfully.");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Customer Paid status not updated.");
	}
	@RequestMapping("/updatePaymentApprovedStatus")
	@PostMapping
	public ResponseEntity<String> updatePaymentApprovedStatus (@RequestBody Bill bill) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		BillService cs = new BillService();
		boolean c = cs.updatePaymentApprovedStatus(bill);
		if(c) {
			return ResponseEntity.ok("Payment approved status updated successfully.");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment approved status not updated.");
	}
	@PostMapping
	@RequestMapping("/unpaidBillsCustomerList")
	public String getUnpaidBillsCustomerList(@RequestBody Bill bill) {
		BillService cs = new BillService();
		List<Bill> billList = cs.getUnpaidBillsCustomerList(bill);
		if (billList != null) {
			Gson gson = new Gson();
			return gson.toJson(billList);
		}
		return null;
	}
}
