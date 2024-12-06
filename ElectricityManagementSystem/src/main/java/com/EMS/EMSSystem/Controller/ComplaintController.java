package com.EMS.EMSSystem.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EMS.EMSSystem.Service.ComplaintService;
import com.EMS.EMSSystem.Service.CustomerService;
import com.EMS.EMSSystem.bean.Complaint;
import com.EMS.EMSSystem.bean.Customer;
import com.google.gson.Gson;

@RestController
public class ComplaintController {
	@PostMapping
	@RequestMapping("/complaintRegistration")

	public ResponseEntity<String> registerComplaint(@RequestBody Complaint complaint) {
		System.out.println("Received Customer Name: " + complaint.getCustomerName());
		System.out.println("Complaint Type: " + complaint.getComplaintType());
		System.out.println("Problem: " + complaint.getProblem());
		ComplaintService cs = new ComplaintService();
		boolean c = cs.regComplaint(complaint);
		if(c)return ResponseEntity.ok("Complaint registered successfully.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Complaint registration failed.");
	}
	@RequestMapping("/viewAllComplaint")
	@PostMapping
	public String viewAllComplaint (@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		ComplaintService cs = new ComplaintService();
		List<Complaint> c = cs.viewAllComplaint(customer);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@RequestMapping("/viewComplaint")
	@PostMapping
	public String viewComplaint (@RequestBody Complaint complaint) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		ComplaintService cs = new ComplaintService();
		Complaint c = cs.viewComplaint(complaint);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@RequestMapping("/updateComplaint")
	@PostMapping
	public ResponseEntity<String> updateComplaint (@RequestBody Complaint complaint) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		ComplaintService cs = new ComplaintService();
		boolean c = cs.updateComplaint(complaint);
		if(c)return ResponseEntity.ok("Complaint updated successfully.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Complaint status failed to update.");
	}
	@RequestMapping("/viewAllUnapprovedComplaints")
	@PostMapping
	public String viewAllUnapprovedComplaints () //i change customer to complaint
	{
		// System.out.println("Received Customer: " + customer.getCustomerName());
		ComplaintService cs = new ComplaintService();
		List<Complaint> c = cs.viewAllUnapprovedComplaints();
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@RequestMapping("/viewAllUnapprovedComplaintsForCustomer")
	@PostMapping
	public String viewAllUnapprovedComplaintsForCustomer (@RequestBody Customer customer) //i change customer to complaint
	{
		// System.out.println("Received Customer: " + customer.getCustomerName());
		ComplaintService cs = new ComplaintService();
		List<Complaint> c = cs.viewAllUnapprovedComplaintsForCustomer(customer);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@RequestMapping("/viewAllApprovedComplaints")
	@PostMapping
	public String viewAllApprovedComplaints (@RequestBody Complaint complaint) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		ComplaintService cs = new ComplaintService();
		List<Complaint> c = cs.viewAllApprovedComplaints(complaint);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@RequestMapping("/updateComplaintFeedback")
	@PostMapping
	public ResponseEntity<String> updateComplaintFeedback (@RequestBody Complaint complaint) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		ComplaintService cs = new ComplaintService();
		boolean c = cs.updateComplaintFeedback(complaint);
		if(c)return ResponseEntity.ok("Complaint feedback updated successfully.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Complaint feedback failed to update.");
	}
	
}