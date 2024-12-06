package com.web;

import java.io.IOException;
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
import com.bean.Complaint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.service.BillAPIservice;
import com.service.ComplaintAPIservice;

@WebServlet("/viewBillServlet")
public class ViewBillByBillIdServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		if(isAuthenticated!=null && isAuthenticated) {
			String billId = request.getParameter("billNumber");

			Map<String,String> billIdMap=new HashMap<>();
			billIdMap.put("billId",billId);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonPayload = objectMapper.writeValueAsString(billIdMap);
				
			String jsonResponse=BillAPIservice.getBill(jsonPayload);
			System.out.println(jsonResponse);
			
			if(!jsonResponse.isEmpty()) {
				//System.out.println("login successful");
				Gson gson=new Gson();
				Bill bill=gson.fromJson(jsonResponse, Bill.class);
				request.setAttribute("bill", bill);
				if(email.equals("admin@tcs.com")) {
					System.out.println(">>INSIDE ViewBillsByBillIDServlet: jsonResponse"+jsonResponse);
					RequestDispatcher rd=request.getRequestDispatcher("viewBillTable.jsp");
					rd.forward(request, response);
				}else {
					System.out.println(">>INSIDE ViewBillsByBillIDServlet: jsonResponse"+jsonResponse);
					RequestDispatcher rd=request.getRequestDispatcher("viewBillTableCustomerSide.jsp");
					rd.forward(request, response);
				}
				
			}
			else {
				if(email.equals("admin@tcs.com")) {
					System.out.println(">>INSIDE ViewBillsByBillIDServlet: NO Bill was there.");
					request.setAttribute("errorMessage", "Invalid Bill ID / Bill does not exists!");
					RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailureAdmin.jsp");
					rd.forward(request, response);
				}else {
					System.out.println(">>INSIDE ViewBillsByBillIDServlet: NO Bill was there.");
					request.setAttribute("errorMessage", "Invalid Bill ID / Bill does not exists!");
					RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
					rd.forward(request, response);
				}
			}
		}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
		
	}

}
