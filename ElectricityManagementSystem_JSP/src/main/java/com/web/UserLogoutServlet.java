package com.web;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/userLogoutServlet")
public class UserLogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//Getting the session from the JSP page
		HttpSession session=request.getSession(false);//Get the current session
		if(session!=null) {
			session.invalidate();//Invalidate the session
			//RequestDispatcher rd=request.getRequestDispatcher("failure.jsp");
			//rd.forward(request, response);
			
		}
		RequestDispatcher rd=request.getRequestDispatcher("loginHome.jsp");
		rd.forward(request, response);
	}
	
	
}
