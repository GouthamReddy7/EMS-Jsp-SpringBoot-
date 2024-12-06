package com.web;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.BillAPIservice;
import com.service.ComplaintAPIservice;
import com.service.CustomerAPIservice;

@WebServlet("/DeleteCustomerServlet")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fetch all customers to display in the table
		System.out.println("Do Get method of delete customer servlet");
		String json = CustomerAPIservice.getCustomerlist();
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<Customer>>() {
		}.getType();
		List<Customer> customerList = gson.fromJson(json, listType);
		// Set the customer list as a request attribute
		request.setAttribute("customerList", customerList);
		// Forward to deleteCustomer.jsp
		RequestDispatcher rd = request.getRequestDispatcher("deleteCustomer.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// First checking whether the current customer has any pending complaints or
		// bills
		Long consumerId = Long.parseLong(request.getParameter("consumerId"));
		Map<String, Long> consumerIdMap = new HashMap<>();
		consumerIdMap.put("consumerId", consumerId);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writeValueAsString(consumerIdMap);
		// code will provide the jsonResponse of unpaid bill list
		String jsonResponseForBills = BillAPIservice.getUnpaidBillsCustomerList(jsonPayload);
		String jsonResponseForComplaints = ComplaintAPIservice.getUnapprovedComplaintlist(jsonPayload);
		if (jsonResponseForBills.isEmpty() && jsonResponseForComplaints.isEmpty()) {
			// No pending bills
			// Deleting Customer
			System.out.println(">>INDSIDE CustomerDeleteServlet: No pending bills or complaints were there.");
			// Handle deletion of selected customer
			System.out.println("DO post of delete customer servlet");
			consumerId = Long.parseLong(request.getParameter("consumerId"));
			System.out.println("Consumer ID to delete: " + consumerId);
			objectMapper = new ObjectMapper();
			jsonPayload = objectMapper.writeValueAsString(consumerIdMap);
			int responseCode = CustomerAPIservice.deleteCustomer(jsonPayload);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				System.out.println("Customer deleted successfully");
				request.setAttribute("message", "Customer deleted successfully");

			} else {
				System.out.println("Customer not found");
				request.setAttribute("message", "Customer not found in the database");
			}
			// Fetch updated customer list
			String json = CustomerAPIservice.getCustomerlist();
			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<Customer>>() {
			}.getType();
			List<Customer> customerList = gson.fromJson(json, listType);
			// Set the updated customer list as a request attribute
			request.setAttribute("customerList", customerList);
			// Forward back to deleteCustomer.jsp
			RequestDispatcher rd = request.getRequestDispatcher("deleteCustomer.jsp");
			rd.forward(request, response);
		} else {
			// pending bills
			System.out.println(">>INDSIDE CustomerDeleteServlet: Pending bills or complaints were there.");
			request.setAttribute("errorMessage", "Cant delete customer, customer has pending bills/complaints !");

			RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailureAdmin.jsp");

			rd.forward(request, response);
			
					
		}
	}

}