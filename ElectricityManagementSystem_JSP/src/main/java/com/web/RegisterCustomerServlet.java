package com.web;

import java.io.IOException;

import java.net.HttpURLConnection;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.bean.Customer;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.service.CustomerAPIservice;

@WebServlet("/CustomerServlet")

public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String consumerId = request.getParameter("consumerID");
		String billNumber = request.getParameter("billNumber");
		String countryCode = request.getParameter("mobileCode");
		String mobileNo = request.getParameter("mobileNumber");
		String title = request.getParameter("title");
		String customerName = request.getParameter("customerName");
		String email = request.getParameter("email");
		String userId = request.getParameter("userID");
		String password = request.getParameter("password");
		String passwordHint=request.getParameter("passwordHint");
		
		Customer customer = new Customer(Integer.parseInt(billNumber),Long.parseLong(consumerId),title,customerName,email, password,userId,countryCode,mobileNo,passwordHint);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writeValueAsString(customer);
		int responseCode = CustomerAPIservice.regCustomer(jsonPayload);
		RequestDispatcher rd;
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// Forward to success JSP
			response.getWriter().println("Customer registered successfully.");
			rd = null;
			rd = request.getRequestDispatcher("acknowledge.jsp");
			request.setAttribute("email", email);
			request.setAttribute("CustomerName", customerName);
			request.setAttribute("mobileNumber", mobileNo);
//	   request.setAttribute("consumerId",consumerId ); 
			 System.out.println(">>INSIDE RegisterCustomerServlet: Customer got register.");
			rd.forward(request, response);

		} else {
			// Forward to failure JSP
			rd = request.getRequestDispatcher("failure.jsp");
			System.out.println(">>INSIDE RegisterCustomerServlet: Customer not got register.");
			request.setAttribute("errorMessage", "Failed to register customer. Response code: " + responseCode);
			rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
			request.setAttribute("errorMessage", "Invalid Bill provided / Bill is not issued from admin side.");
			rd.forward(request, response);
		}
	}
}
