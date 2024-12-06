package com.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Customer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.CustomerAPIservice;

@WebServlet("/CustomerDetailsServlet")
public class ViewCustomersAdminDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);// fetching the current session
		String email = (String) session.getAttribute("email");
		String password = (String) session.getAttribute("password");
		Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");
		if (isAuthenticated != null && isAuthenticated) {
			response.setContentType("application/json");
			String jsonresponse = CustomerAPIservice.getCustomerlist();
			System.out.print(">>ViewCustomersAdminServlet: jsonResponse" + jsonresponse);
			if (!jsonresponse.isEmpty()) {
				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Customer>>() {
				}.getType();
				List<Customer> customerList = gson.fromJson(jsonresponse, listType);
				request.setAttribute("customerList", customerList);
				RequestDispatcher rd = request.getRequestDispatcher("adminManageCustomer.jsp");
				System.out.println(">>INSIDE ViewCustomersAdminServlet: jsonResponse" + jsonresponse);
				rd.forward(request, response);
			} else {
				System.out.println(">>INSIDE ViewCustomersAdminServlet: jsonResponse is empty");
				request.setAttribute("errorMessage", "Invalid Customer / Customer does not exists!");
				RequestDispatcher rd = request.getRequestDispatcher("Failure Pages/displayFailureAdmin.jsp");
				rd.forward(request, response);
			}
		} else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}
	
	}
}