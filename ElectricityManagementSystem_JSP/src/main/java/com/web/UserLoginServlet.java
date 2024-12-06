package com.web;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.servlet.http.HttpSession;

import com.bean.Complaint;
import com.bean.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.ComplaintAPIservice;
import com.service.CustomerAPIservice;
@WebServlet("/LoginServlet")
public class UserLoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		//Below code is for both user login and ADMIN login
		Map<String,String> emailPassMap=new HashMap<>();
		emailPassMap.put("email", email);
		emailPassMap.put("password", password);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writeValueAsString(emailPassMap);
		String customerInfo=CustomerAPIservice.validateLogin(jsonPayload);
		System.out.println(">>INSIDE UserLoginSerlvet: jsonResponse"+customerInfo);
		if(!customerInfo.isEmpty()) {
			//Converting customer info to customeList
			Gson gson=new Gson();
			Customer customer=gson.fromJson(customerInfo, Customer.class);
			System.out.println(">>INSIDE UserLoginServelet: Login happpend here: Customer Information"+customerInfo);
			HttpSession session=request.getSession(); //This session will store my email and password
			session.setAttribute("email", email);
			session.setAttribute("isAuthenticated", true); //to check the authentication
			session.setAttribute("password",password);
			session.setAttribute("consumerId",customer.getConsumerId());
			session.setAttribute("consumerName",customer.getCustomerName());
			//This code is for ADMIN login to home page.
			if(email.equals("admin@tcs.com") && password.equals("Admin@123")) {
				RequestDispatcher rd=request.getRequestDispatcher("adminHome.jsp");
				rd.forward(request,response);
			}else {
				//If this person is not ADMIN then redirect to user Home Page.
				//Fetching existing user details				
			RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
			}
		}
		else {
			System.out.println(">>INSIDE UserLoginServelet: Error happpend here");
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request, response);
		}
	}
}