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
public class CustomerController {
	@RequestMapping("/")
	public String hello() {
		return "welcome to home ";
	}

	@PostMapping
	@RequestMapping("/customerLogin")
	public String loginCustomer(@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		System.out.println("Email: " + customer.getEmail());
		System.out.println("Password: " + customer.getPassword());
		CustomerService cs = new CustomerService();
		Customer c = cs.loginCustomer(customer);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}

	@PostMapping
	@RequestMapping("/customerRegistration")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
		System.out.println("Received Customer: " + customer.getCustomerName());
		System.out.println("Email: " + customer.getEmail());
		System.out.println("Password: " + customer.getPassword());
		CustomerService cs = new CustomerService();
		boolean c = cs.regCustomer(customer);
		if(c) return ResponseEntity.ok("Customer registered successfully.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Customer registration failed.");
	}
	@PostMapping
	@RequestMapping("/customerList")
	public String getCustomers() {
		CustomerService cs = new CustomerService();
		List<Customer> customerList = cs.getCustomers();
		if (customerList != null) {
			Gson gson = new Gson();
			return gson.toJson(customerList);
		}
		return null;
	}
	@RequestMapping("/changeUserPassword")
	@PostMapping
	public ResponseEntity<String> changeUserPassword (@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		CustomerService cs = new CustomerService();
		boolean c = cs.changeUserPassword(customer);
		if(c)return ResponseEntity.ok("User password updated successfully.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User password failed to update.");
	}
	@RequestMapping("/viewCustomer")
	@PostMapping
	public String viewCustomer (@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		CustomerService cs = new CustomerService();
		Customer c = cs.viewCustomer(customer);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@RequestMapping("/updateNewPassword")
	@PostMapping
	public ResponseEntity<String> updateNewPassword (@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		CustomerService cs = new CustomerService();
		boolean c = cs.updateNewPassword(customer);
		if(c)return ResponseEntity.ok("New Password updated successfully.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("New Password failed to update.");
	}
	@RequestMapping("/viewCustomerWithEmailAndHint")
	@PostMapping
	public String viewCustomerWithEmailAndHint (@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		CustomerService cs = new CustomerService();
		Customer c = cs.viewCustomerWithEmailAndHint(customer);
		if (c != null) {
			Gson gson = new Gson();
			return gson.toJson(c);
		}
		return null;
	}
	@PostMapping
	@RequestMapping("/deleteCustomer")
	public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
		// System.out.println("Received Customer: " + customer.getCustomerName());
		System.out.println("Consumer ID: " + customer.getConsumerId());
		CustomerService cs = new CustomerService();
		boolean c=cs.deleteCustomer(customer.getConsumerId());
		return ResponseEntity.ok("Customer deleted successfully.");
	}
}