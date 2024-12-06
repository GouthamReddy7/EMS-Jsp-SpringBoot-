package com.web;

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

import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.ComplaintAPIservice;
import com.service.CustomerAPIservice;

@WebServlet("/ComplaintViewAllServlet")
public class ViewComplaintsByConsumerIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);// fetching the current session
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");
		if (isAuthenticated != null && isAuthenticated) {
			// Long consumerId=1234567891011L;
			String consumerIdStr = request.getParameter("consumerId");
			if (consumerIdStr == null || consumerIdStr.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/ComplaintViewServlet");
				rd.forward(request, response);
				return;
			}
			System.out.println(consumerIdStr);
			Long consumerId = Long.parseLong(consumerIdStr);
			Map<String, Long> customerIdMap = new HashMap<>();
			customerIdMap.put("consumerId", consumerId);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonPayload = objectMapper.writeValueAsString(customerIdMap);

			String jsonResponse = ComplaintAPIservice.getComplaintlist(jsonPayload);
			System.out.println(jsonResponse);
			// The code below will convert the string to array list
			if (!jsonResponse.isEmpty()) {
				// System.out.print
				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Complaint>>() {
				}.getType();
				List<Complaint> complaintList = gson.fromJson(jsonResponse, listType);

				request.setAttribute("complaintList", complaintList);
				System.out.println(">>INSIDE  ViewComplaintsBySessionConsumerIdServle: jsonResponse" + jsonResponse);
				// RequestDispatcher rd=request.getRequestDispatcher("viewAllComplaints.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("viewAllComplaintTable.jsp");
				rd.forward(request, response);
			} else {
				System.out.println(">>INSIDE  ViewComplaintsBySessionConsumerIdServle: Complaint was not there.");
				request.setAttribute("errorMessage", "Invalid Consumer ID / Complaint does not exists!");
				RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
				rd.forward(request, response);
			}
		} else {
			// Go to the login page if authentication is false
			RequestDispatcher rd = request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request, response);
		}
	}
}
