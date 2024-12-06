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
@WebServlet("/ComplaintViewServlet")
public class ViewCompalintByComplaintIdServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		if(isAuthenticated!=null && isAuthenticated) {
			String complaintId = request.getParameter("complaintId");
			Map<String,String> complaintIdMap=new HashMap<>();
			complaintIdMap.put("complaintId",complaintId);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonPayload = objectMapper.writeValueAsString(complaintIdMap);
				
			String jsonResponse=ComplaintAPIservice.getComplaint(jsonPayload);
			System.out.println(jsonResponse);
			
			if(!jsonResponse.isEmpty()) {
				System.out.println("view complaint success in controller");
				
//				ObjectMapper objectMapper1=new ObjectMapper();
//				Complaint complaint=objectMapper1.readValue(jsonResponse, Complaint.class);
				Gson gson=new Gson();
				Complaint complaint=gson.fromJson(jsonResponse, Complaint.class);
				request.setAttribute("complaint", complaint);
				System.out.println(">>INSIDE ViewComplaintByComplaintIdServlet: jsonResponse"+jsonResponse);
				RequestDispatcher rd=request.getRequestDispatcher("viewComplaintTable.jsp");
				rd.forward(request, response);
			
			}
			else {
				System.out.println(">>INSIDE ViewComplaintByComplaintIdServlet: Complaint was not there.");
				
				request.setAttribute("errorMessage", "Invalid Complaint Number / Complaint does not exists!");
				RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
				rd.forward(request, response);
			}
		}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
		
		
		
		
//		String complaintId = request.getParameter("complaintId");
//		Map<String,String> complaintIdMap=new HashMap<>();
//		complaintIdMap.put("complaintId",complaintId);
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonPayload = objectMapper.writeValueAsString(complaintIdMap);
//			
//		String jsonResponse=ComplaintAPIservice.getComplaint(jsonPayload);
//		System.out.println(jsonResponse);
//		
//		if(!jsonResponse.isEmpty()) {
//			System.out.println("view complaint success in controller");
//			
////			ObjectMapper objectMapper1=new ObjectMapper();
////			Complaint complaint=objectMapper1.readValue(jsonResponse, Complaint.class);
//			Gson gson=new Gson();
//			Complaint complaint=gson.fromJson(jsonResponse, Complaint.class);
//			request.setAttribute("complaint", complaint);
//			System.out.println(">>INSIDE ViewComplaintByComplaintIdServlet: jsonResponse"+jsonResponse);
//			RequestDispatcher rd=request.getRequestDispatcher("viewComplaintTable.jsp");
//			rd.forward(request, response);
//		
//		}
//		else {
//			System.out.println(">>INSIDE ViewComplaintByComplaintIdServlet: Complaint was not there.");
//			RequestDispatcher rd=request.getRequestDispatcher("failure.jsp");
//			rd.forward(request, response);
//		}
	}

}
