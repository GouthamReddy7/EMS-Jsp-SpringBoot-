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

import com.bean.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ComplaintAPIservice;
import com.service.CustomerAPIservice;
@WebServlet("/UpdateFeedbackCustomerServlet")
public class UpdateFeedbackCustomerServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		if(isAuthenticated!=null && isAuthenticated) {
			String complaintFeedback = request.getParameter("complaintFeedback");
			Map<String,String> complaintIdAndStatusMap=new HashMap<>();
			complaintIdAndStatusMap.put("complaintFeedback",complaintFeedback);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonPayload = objectMapper.writeValueAsString(complaintIdAndStatusMap);
				
			int responseCode=ComplaintAPIservice.updateComplaintFeedback(jsonPayload);
			RequestDispatcher rd;
			//System.out.println(jsonResponse);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Forward to success JSP
				response.getWriter().println("Complaint updated successfully.");
				rd = null;
				rd = request.getRequestDispatcher("success.jsp");
				System.out.println(">>INSIDE UpdateFeedbackCustomerServlet: Complaints Table got updated.");
				rd.forward(request, response);

			} else {
				// Forward to failure JSP
				rd = request.getRequestDispatcher("failure.jsp");
				System.out.println(">>INSIDE UpdateFeedbackCustomerServlet: Complaints Table not got updated.");
				request.setAttribute("errorMessage", "Failed to update complaint. Response code: " + responseCode);
				rd.forward(request, response);
			}
		}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
	}
}
