package com.web;

import java.io.IOException;
import java.lang.reflect.Type;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.ComplaintAPIservice;

@WebServlet("/ViewUnapprovedComplaints")
public class ViewUnapprovedComplaintsAdminServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Long consumerId=(Long)session.getAttribute("consumerId");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		if(isAuthenticated!=null && isAuthenticated) {
			//Map<String, Long> customerIdMap = new HashMap<>();
//			customerIdMap.put("consumerId", consumerId);
//			ObjectMapper objectMapper = new ObjectMapper();
//			String jsonPayload = objectMapper.writeValueAsString(customerIdMap);

			String jsonResponse = ComplaintAPIservice.getUnapprovedComplaintlist();
			System.out.println(jsonResponse);
			// The code below will convert the string to array list
			if (!jsonResponse.isEmpty()) {
				// System.out.print
				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Complaint>>() {
				}.getType();
				List<Complaint> complaintList = gson.fromJson(jsonResponse, listType);

				request.setAttribute("complaintList", complaintList);
				System.out.println(">>INSIDE ViewUnapprovedComplaintsAdminServlet: jsonResponse" + jsonResponse);
				// RequestDispatcher rd=request.getRequestDispatcher("viewAllComplaints.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("adminComplaintStatus.jsp");
				rd.forward(request, response);
			} else {
				System.out.println(">>INSIDE ViewUnapprovedComplaintsAdminServlet: Complaint was not there.");
				request.setAttribute("errorMessage", "Invalid Consumer ID / Complaint does not exists!");
				RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailureAdmin.jsp");
				rd.forward(request, response);
			}
		} else {
			// Go to the login page if authentication is false
			RequestDispatcher rd = request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request, response);
		}
		}
}