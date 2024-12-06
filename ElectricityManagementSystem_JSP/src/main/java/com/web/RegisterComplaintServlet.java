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
import com.bean.Complaint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ComplaintAPIservice;

@WebServlet("/ComplaintServlet")

public class RegisterComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  //generating 5 digit for complaint ID
		HttpSession session=request.getSession(false);//fetching the current session
		String email=(String)session.getAttribute("email");
		String password=(String)session.getAttribute("password");
		Boolean isAuthenticated=(Boolean)session.getAttribute("isAuthenticated");
		//if authentication is true then do the work
		if(isAuthenticated!=null && isAuthenticated) {
			Random random=new Random();
			int fiveDigitNumber=10000+random.nextInt(90000);
			
			  String complaintType= request.getParameter("complaintType");
			  String category= request.getParameter("category");
//			  String category="categoryVV";
			  String landmark= request.getParameter("landmark");
//			  String landmark="landmarkVV";
			  String customerName= request.getParameter("contactPerson");
			  String problem= request.getParameter("problemDescription");
			  long consumerId= Long.parseLong(request.getParameter("consumerNumber"));
			  String address= request.getParameter("address");
			  String mobileNumber= request.getParameter("mobileNumber");
			  String complaintId=customerName.substring(0,5)+String.valueOf(fiveDigitNumber);
			  String status="unsolved"; //default
			  String complaintFeedback="No feedback";
			  
			  Complaint complaint=new Complaint(complaintId,complaintType,category,landmark,customerName,
				problem,consumerId,address,mobileNumber,status,complaintFeedback);
			  
			  ObjectMapper objectMapper = new ObjectMapper();
			  String jsonPayload = objectMapper.writeValueAsString(complaint);
			  
			  System.out.println(jsonPayload);
			  
			  int responseCode = ComplaintAPIservice.regComplaint(jsonPayload);
			  RequestDispatcher rd;
			  
			  if (responseCode == HttpURLConnection.HTTP_OK) {
					// Forward to success JSP
				  	response.getWriter().println("Complaint registered successfully.");
					rd = null;
					rd = request.getRequestDispatcher("acknowledgeComplaint.jsp");
					request.setAttribute("contactPerson", customerName);
					request.setAttribute("complaintType", complaintType);
					request.setAttribute("problemDescription", problem);
					request.setAttribute("complaintId", complaintId);
					System.out.println(">>INSIDE RegisterComplaintServlet: Complaint got register.");
//			   request.setAttribute("consumerId",consumerId ); 
					rd.forward(request, response);

				} else {
					// Forward to failure JSP
					System.out.println(">>INSIDE RegisterComplaintServlet: Complaint not got register.");			
					request.setAttribute("errorMessage", "Invalid Consumer Number / Consumer Number is not a primary key in Customer!");
					rd = request.getRequestDispatcher("Failure Pages/displayFailure.jsp");
					rd.forward(request, response);
				}
			}else {
			//Go to the login page if authentication is false
			RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
			rd.forward(request,response);
		}	
	}
}