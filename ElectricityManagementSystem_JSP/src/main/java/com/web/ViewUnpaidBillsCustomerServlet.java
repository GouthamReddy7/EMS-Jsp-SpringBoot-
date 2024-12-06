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

import com.bean.Bill;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.BillAPIservice;

@WebServlet("/ViewUnpaidBillsCustomerServlet")
public class ViewUnpaidBillsCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);// fetching the current session
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");
		if(isAuthenticated!=null && isAuthenticated) {
			//Long consumerId=1234567891011L;
					Long consumerId=(Long)session.getAttribute("consumerId");
					Map<String,Long> customerIdMap=new HashMap<>();
					customerIdMap.put("consumerId",consumerId);
					ObjectMapper objectMapper = new ObjectMapper();
					String jsonPayload = objectMapper.writeValueAsString(customerIdMap);
						
					String jsonResponse=BillAPIservice.getUnpaidBillsCustomerList(jsonPayload);
					System.out.println(jsonResponse);
						//The code below will convert the string to array list
					if(!jsonResponse.isEmpty()) {
						//System.out.print
						Gson gson=new Gson();
						Type listType=new TypeToken<ArrayList<Bill>>() {}.getType();
						List<Bill> billList=gson.fromJson(jsonResponse, listType);
						System.out.println(">>INSIDE ViewUnpaidBillsCustomerServlett: jsonResponse"+jsonResponse);
						request.setAttribute("billList", billList);
						
						//RequestDispatcher rd=request.getRequestDispatcher("viewAllComplaints.jsp");
						RequestDispatcher rd=request.getRequestDispatcher("viewCustomerUnpaidBill.jsp");
						rd.forward(request, response);
					}
					else {
						System.out.println(">>INSIDE ViewUnpaidBillsCustomerServlet: not bills was there");
						request.setAttribute("errorMessage", "Invalid Consumer Number / Bill does not exists!");
						RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
						rd.forward(request, response);
					}
		}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
	}

}