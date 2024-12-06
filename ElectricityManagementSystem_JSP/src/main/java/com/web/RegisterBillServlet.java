//package com.web;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.util.Random;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.bean.Bill;
//import com.bean.Complaint;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.service.BillAPIservice;
//import com.service.ComplaintAPIservice;
//@WebServlet("/registerBillServlet")
//public class RegisterBillServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        	HttpSession session=request.getSession(false);//fetching the current session
//    		String email=(String)session.getAttribute("email");
//    		String password=(String)session.getAttribute("password");
//    		
//    		
//    		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
//        	if(isAuthenticated!=null && isAuthenticated) {
//        		try {
//        			// Generate unique Bill ID
//                	Random random=new Random();
//                    // Extract and validate parameters
//                    String consumerIdParam = request.getParameter("consumerId");
//                    String dueAmountParam = request.getParameter("dueAmount");
//                    //actual values for bill
//                    int billId=10000+random.nextInt(90000);
//                    String month = request.getParameter("month");
//                    Long consumerId = Long.parseLong(consumerIdParam);
//                    double dueAmount = Double.parseDouble(dueAmountParam);
//                    String status = "unpaid";
//                    String customerPaid="unpaid";
//                    System.out.println(">> INSIDE RegisterBillServlet: AUTO GENERATED Bill ID:"+billId);
//                    // Create Bill object
//                    Bill bill = new Bill(billId, consumerId, dueAmount, month, status,customerPaid);
//
//                    // Convert Bill object to JSON
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    String jsonPayload = objectMapper.writeValueAsString(bill);
//                    //System.out.println("JSON Payload: " + jsonPayload);
//
//                    // Call Bill API service
//                    int responseCode = BillAPIservice.regBill(jsonPayload);
//                   
//                    // Forward to appropriate JSP
//                    RequestDispatcher rd;
//                    if (responseCode == HttpURLConnection.HTTP_OK) {
//                        response.getWriter().println("Bill registered successfully.");
//                        System.out.println(">>INSIDE RegisterBill: bill got register");
//                        
//                        
//                        rd = request.getRequestDispatcher("acknowledgeBill.jsp");
//                        System.out.println(">>Inside RegisterBillServlet: Bill got register.");
//                    } else {
//                        //rd = request.getRequestDispatcher("failure.jsp");
//                        System.out.println(">>INSIDE RegisterBillServlet: bill got not register");
//                        request.setAttribute("errorMessage", "Failed to register bill. Response code: " + responseCode);
//        				rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
//        				rd.forward(request, response);
//                    }
//                    if (rd != null) {
//                        rd.forward(request, response);
//                    }
//        		
//            } catch (Exception e) {
//                e.printStackTrace();
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal error occurred.");
//                
//            }
//        	}else {
//        		//Go to the login page if authentication is false
//    			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
//    			rd.forward(request,response);
//        	}
//    }
//}
package com.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Bill;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.BillAPIservice;

@WebServlet("/registerBillServlet")
public class RegisterBillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Fetching the current session
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

        if (isAuthenticated != null && isAuthenticated) {
            try {
                // Generate unique Bill ID
                Random random = new Random();
                int billId = 10000 + random.nextInt(90000);

                // Extract and validate parameters
                String consumerIdParam = request.getParameter("consumerId");
                String dueAmountParam = request.getParameter("dueAmount");
                String month = request.getParameter("month");
                String contactPerson = request.getParameter("contactPerson"); // Customer name

                Long consumerId = Long.parseLong(consumerIdParam);
                double dueAmount = Double.parseDouble(dueAmountParam);
                String status = "unpaid";
                String customerPaid = "unpaid";

                System.out.println(">> INSIDE RegisterBillServlet: AUTO GENERATED Bill ID:" + billId);

                // Create Bill object
                Bill bill = new Bill(billId, consumerId, dueAmount, month, status, customerPaid);

                // Convert Bill object to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonPayload = objectMapper.writeValueAsString(bill);

                // Call Bill API service
                int responseCode = BillAPIservice.regBill(jsonPayload);

                // Forward to appropriate JSP
                RequestDispatcher rd;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Set attributes for acknowledgment
                    request.setAttribute("consumerId", consumerId);
                    request.setAttribute("dueAmount", dueAmount);
                    request.setAttribute("month", month);

                    System.out.println(">>Inside RegisterBillServlet: Bill registered successfully.");
                    rd = request.getRequestDispatcher("acknowledgeBill.jsp");
                } else {
                	// Handle failure
                	if(email.equals("admin@tcs.com")) {
                    	System.out.println(">>INSIDE RegisterBillServlet: Bill registration failed.");
                        request.setAttribute("errorMessage", "Failed to register bill. Response code: " + responseCode);
                        rd = request.getRequestDispatcher("Failure Pages/displayFailureAdmin.jsp");
                    }else {
                    	System.out.println(">>INSIDE RegisterBillServlet: Bill registration failed.");
                        request.setAttribute("errorMessage", "Failed to register bill. Response code: " + responseCode);
                        rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
                    }
                	
                    
                }
                if (rd != null) {
                    rd.forward(request, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal error occurred.");
            }
        } else {
            // Go to the login page if authentication is false
            RequestDispatcher rd = request.getRequestDispatcher("loginHome.jsp");
            rd.forward(request, response);
        }
    }
}
