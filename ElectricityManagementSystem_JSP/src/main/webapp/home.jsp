<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Home - Quick EBill</title>

<link rel="stylesheet" href="css/home.css">

</head>

<body>

	<%
	// Check if the user is authenticated

	String customerName= (String) session.getAttribute("customerName");
	
	String username = (String) session.getAttribute("username");
	Long consumerId=(Long)session.getAttribute("consumerId");


	Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

	if (isAuthenticated == null || !isAuthenticated) {

		// Redirect to login page if not authenticated

		request.getRequestDispatcher("loginHome.jsp").forward(request, response);

	}
	%>

	<div id="menu-placeholder"></div>

	<div class="menu-container"></div>

	<div class="content">
		<h2>Welcome to the Electricity Management System</h2>
		<p>You can navigate to other options using the menu above.</p>
		<div class="card-container">
			<div class="card">
				<h3>Search Complaint</h3>
				<p>Search for complaint using complaint ID.</p>
				<a href="complaintStatus.jsp">Go</a>
			</div>
		<!-- <div class="card">
				<h3>Complaint Feedback</h3>
				<p>Generate feedback for complaints.</p>
				<a href="ViewApprovedComplaintsAdminServlet?consumerId=<%=consumerId%>" method="get" >Go</a> 

			</div> -->	
		</div>
		
		
	</div>
	
	<script>

function loadMenu() {

  fetch('menu.jsp')

  .then(response => response.text())

  .then(data => {

    document.getElementById('menu-placeholder').innerHTML = data;

  });

}

document.addEventListener('DOMContentLoaded', loadMenu);

</script>

<jsp:include page="footer.jsp" />

</body>

</html>