<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Quick EBill</title>

<link rel="stylesheet" href="css/menu.css">

</head>

<body>

	<%

 // Check session attributes for user authentication
	String consumerName= (String) session.getAttribute("consumerName");
	String username = (String) session.getAttribute("username");
	Long consumerId=(Long)session.getAttribute("consumerId");

	Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");
	if (isAuthenticated == null || !isAuthenticated) {
	
// Redirect to login page if not authenticated
	
	request.getRequestDispatcher("login.jsp").forward(request, response);

  }

%>

	<div class="header">

		<a href="home.jsp"><h2>Quick EBill</h2></a>

		<div>

			<b>Welcome <%=consumerName%></b>

			<form action="userLogoutServlet" method="GET">

				<button class="logout-btn">LOGOUT</button>

			</form>
		</div>

	</div>

	<div class="menu-bar">

		<a href="home.jsp">HOME</a> 
		<a href="registerComplaint.jsp">REGISTER COMPLAINT</a> 
		<a href="ViewUnpaidBillsCustomerServlet" method="get">PAY BILL</a>
		<a href="ViewComplaintsBySessionConsumerIdServlet?consumerId=<%=consumerId%>" method="get" >VIEW ALL COMPLAINTS</a> 
		<a href="viewAllBillServlet?consumerId=<%=consumerId%>" method="get" >VIEW ALL BILL</a>
		
		
	</div>

</body>

</html>