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

import com.bean.Bill;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.service.BillAPIservice;
import com.service.ComplaintAPIservice;
import com.service.CustomerAPIservice;

@WebServlet("/ChangeUserPasswordServlet")
public class ChangeUserPasswordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		//if authentication is true then do the work
		if(isAuthenticated!=null && isAuthenticated) {
			//Rest of the code will be written here.
			String oldPassword=request.getParameter("oldPassword");
			String newPassword=request.getParameter("newPassword");
			if(oldPassword.equals(password)) {
				Map<String,String> emailAndPassMap=new HashMap<>();
				emailAndPassMap.put("email",email);
				emailAndPassMap.put("password", newPassword);
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonPayload = objectMapper.writeValueAsString(emailAndPassMap);
				int responseCode=CustomerAPIservice.changeUserPassword(jsonPayload);
				RequestDispatcher rd;
				if (responseCode == HttpURLConnection.HTTP_OK) {
					// Forward to success JSP
					response.getWriter().println("Customer updated successfully.");
					rd = null;
					rd = request.getRequestDispatcher("acknowledge.jsp");
					System.out.println(">>INSIDE ChangeUserPasswordServlet: Customer Table got updated.");
					rd.forward(request, response);

				} else {
					// Forward to failure JSP
					rd = request.getRequestDispatcher("failure.jsp");
					System.out.println(">>INSIDE ChangeUserPasswordServlet: Customer Table not got updated.");
					request.setAttribute("errorMessage", "Failed to update Customer. Response code: " + responseCode);
					rd.forward(request, response);
				}
			}else {
				System.out.println(">>INSIDE ChangeUserPasswordServlet: Old password is wrong");
				RequestDispatcher rdDispatcher=request.getRequestDispatcher("failure.jsp");
				rdDispatcher.forward(request,response);
			}
				
		}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
		
	}
}
