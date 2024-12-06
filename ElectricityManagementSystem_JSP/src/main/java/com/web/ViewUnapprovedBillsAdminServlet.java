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
import com.bean.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.BillAPIservice;
import com.service.CustomerAPIservice;
@WebServlet("/ViewUnpaidStatusBillsAdminServlet")

public class ViewUnapprovedBillsAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		if(isAuthenticated!=null && isAuthenticated) {
			response.setContentType("application/json");
			String jsonresponse = BillAPIservice.getUnapprovedBillList();
			System.out.print(">>ViewCustomersAdminServlet: jsonResponse" + jsonresponse);
			if (!jsonresponse.isEmpty()) {
				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Bill>>() {
				}.getType();
				List<Bill> billList = gson.fromJson(jsonresponse, listType);
				request.setAttribute("billList", billList);
				
				RequestDispatcher rd = request.getRequestDispatcher("approveBills.jsp");
				System.out.println(">>INSIDE ViewUnapprovedBillsAdminServlet: jsonResponse" + jsonresponse);
				rd.forward(request, response);
			} else {
				System.out.println(">>INSIDE ViewUnapprovedBillsAdminServlet: jsonResponse is empty");
				RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailureAdmin.jsp");
				rd.forward(request, response);
			}
		}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
		
	}
}
