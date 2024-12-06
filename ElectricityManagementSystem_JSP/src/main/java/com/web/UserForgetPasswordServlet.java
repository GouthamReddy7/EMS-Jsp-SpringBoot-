package com.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.service.BillAPIservice;
import com.service.ComplaintAPIservice;
import com.service.CustomerAPIservice;
@WebServlet("/UserForgetPasswordServlet")
public class UserForgetPasswordServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String enterEmail=request.getParameter("email");
    	String enterPasswordHint=request.getParameter("passwordHint");
    	String newPassword=request.getParameter("password");
    	//
		Map<String,String> emailAndHintMap=new HashMap<>();
		emailAndHintMap.put("email",enterEmail);
		emailAndHintMap.put("passwordHint",enterPasswordHint);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writeValueAsString(emailAndHintMap);
		//Check the enter email and password hint is present in the database or not
		String jsonResponse=CustomerAPIservice.getCustomerWithEmailAndHint(jsonPayload);
		System.out.println(jsonResponse);
		//IF PRESENT
		if(!jsonResponse.isEmpty()) {
			//System.out.println("view customer success in controller");
			Map<String,String> emailAndPasswordMap=new HashMap<>();
			emailAndPasswordMap.put("password", newPassword);
			emailAndPasswordMap.put("email", enterEmail);
			objectMapper = new ObjectMapper();
			jsonPayload = objectMapper.writeValueAsString(emailAndPasswordMap);
			int responseCode=CustomerAPIservice.updateNewPassword(jsonPayload);
			RequestDispatcher rd;
			//System.out.println(jsonResponse);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Forward to success JSP
				response.getWriter().println("New Password updated successfully.");
				rd = null;
				rd = request.getRequestDispatcher("loginHome.jsp");
				
				System.out.println(">>INSIDE UserForgetPasswordServlet: Customer new Password got updated.");
				rd.forward(request, response);

			} else {
				// Forward to failure JSP
				System.out.println(">>INSIDE UpdateCustomerPaidStatusServlet: Customer new password not got updated.");
				request.setAttribute("errorMessage", "Failed to update bill. Response code: " + responseCode);
				rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
				rd.forward(request, response);
			}
		}
		else {
			System.out.println(">>INSIDE UserForgetPasswordServlet: Email and hint was not there.");
			
			request.setAttribute("errorMessage", "Invalid email and hint / User does not exists!");
			RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
			rd.forward(request, response);
		}
	}
	  

}
