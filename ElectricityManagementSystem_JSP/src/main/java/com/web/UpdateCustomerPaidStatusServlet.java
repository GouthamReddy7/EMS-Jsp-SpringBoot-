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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.BillAPIservice;

@WebServlet("/UpdateCustomerPaidStatusServlet")
public class UpdateCustomerPaidStatusServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		if(isAuthenticated!=null && isAuthenticated) {
			int billId = Integer.parseInt(request.getParameter("billId"));
			String month=request.getParameter("month");
			String dueAmount=request.getParameter("dueAmount");
			String consumerId=request.getParameter("consumerId");
			System.out.println(">> INSIDE UpdateCustomerPaidStausServlet:billID: <<<<<<"+billId);
			Map<String,Integer> billIdAndStatusMap=new HashMap<>();
			billIdAndStatusMap.put("billId",billId);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonPayload = objectMapper.writeValueAsString(billIdAndStatusMap);
				
			int responseCode=BillAPIservice.updateCustomerPaidStatus(jsonPayload);
			RequestDispatcher rd;
			//System.out.println(jsonResponse);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Forward to success JSP
				response.getWriter().println("Bill updated successfully.");
				rd = null;
				rd = request.getRequestDispatcher("successPaymentPage.jsp");
				request.setAttribute("billId", billId);
				request.setAttribute("consumerId",consumerId);
				request.setAttribute("month",month);
				request.setAttribute("dueAmount",dueAmount ); 
				
				System.out.println(">>INSIDE UpdateCustomerPaidStatusServlet: Customer paid status got updated.");
				rd.forward(request, response);

			} else {
				// Forward to failure JSP
				System.out.println(">>INSIDE UpdateCustomerPaidStatusServlet: Customer paid status not got updated.");
				request.setAttribute("errorMessage", "Failed to update bill. Response code: " + responseCode);
				rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
				rd.forward(request, response);
			}
		}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
		
		
		
		
		
		
//		String billId = request.getParameter("billId");
//		String status=request.getParameter("status");
//		Map<String,String> billIdAndStatusMap=new HashMap<>();
//		billIdAndStatusMap.put("billId",billId);
//		billIdAndStatusMap.put("status",status);
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonPayload = objectMapper.writeValueAsString(billIdAndStatusMap);
//			
//		int responseCode=BillAPIservice.updateBill(jsonPayload);
//		RequestDispatcher rd;
//		//System.out.println(jsonResponse);
//		if (responseCode == HttpURLConnection.HTTP_OK) {
//			// Forward to success JSP
//			response.getWriter().println("Bill updated successfully.");
//			rd = null;
//			rd = request.getRequestDispatcher("approveBills.jsp");
////			request.setAttribute("email", email);
////			request.setAttribute("CustomerName", customerName);
////			request.setAttribute("mobillnumber", mobileNo);
////	   request.setAttribute("consumerId",consumerId ); 
//			
//			System.out.println(">>INSIDE UpdateBillStatusAdminServlet: Bill Table got updated.");
//			rd.forward(request, response);
//
//		} else {
//			// Forward to failure JSP
//			rd = request.getRequestDispatcher("failure.jsp");
//			System.out.println(">>INSIDE UpdateComplaintStatusAdminServlet: Bill Table not got updated.");
//			request.setAttribute("errorMessage", "Failed to update bill. Response code: " + responseCode);
//		}
//		rd.forward(request, response);
	}
}
