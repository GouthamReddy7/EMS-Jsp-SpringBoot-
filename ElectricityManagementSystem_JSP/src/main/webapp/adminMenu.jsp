<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
@import
	url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

body {
	font-family: 'Montserrat', sans-serif;
}

.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 30px;
	background-color: #007bff;
	color: white;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.header h2 {
	font-size: 28px;
	margin: 0;
}

.header h2:hover {
	transform: translateY(-2px);
	transition: transform 0.3s ease;
}

.logout-btn {
	background-color: #dc3545;
	color: white;
	border: none;
	padding: 8px 20px;
	border-radius: 30px;
	cursor: pointer;
	font-size: 16px;
	margin-left: 20px;
	transition: background-color 0.3s ease;
}

.logout-btn:hover {
	background-color: #c82333;
}

/* Navbar */
/* Navbar */
.menu-bar {
    display: flex;
    padding: 10px 0;
    background: linear-gradient(to right, #4CAF50, #008CBA); /* Gradient from green to blue */
    padding: 5px 30px;
    margin-bottom: 20px;
    border-radius: 0 0 10px 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    border: 2px solid #fff;
}

.menu-bar a {
    color: white; /* White text for contrast */
    text-decoration: none;
    padding: 10px 20px;
    font-weight: 600;
    font-size: 16px;
    transition: background-color 0.3s, transform 0.3s;
}

.menu-bar a:hover {
    background-color: rgba(255, 255, 255, 0.2); /* Light overlay on hover */
    border-radius: 5px;
    transform: scale(1.05); /* Subtle zoom-in effect */
}

</style>
</head>
<body>
	<%
	// Check if the user is authenticated

	String customerName = (String) session.getAttribute("customerName");

	String username = (String) session.getAttribute("username");
	Long consumerId=(Long)session.getAttribute("consumerId");

	Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

	if (isAuthenticated == null || !isAuthenticated) {

		// Redirect to login page if not authenticated

		request.getRequestDispatcher("loginHome.jsp").forward(request, response);

	}
	%>
	<div class="header">
		<h2>Quick EBill</h2>
		<div>
			<b>Welcome Admin</b>
			<button onclick="window.location.href = 'loginHome.jsp';"
				class="logout-btn">LOGOUT</button>
		</div>
	</div>

	<div class="menu-bar">
		<a href="adminHome.jsp">HOME</a> 
		<a href="adminAddBill.jsp">ADD BILL</a> 
		<a href="ViewUnapprovedComplaints?consumerId=<%=consumerId%>" method="get">APPROVE COMPLAINTS</a>
		<!-- <a href="approveBills.jsp">APPROVE BILLS</a> -->
		<a href="ViewUnpaidStatusBillsAdminServlet" method="get">APPROVE BILLS</a>
	</div>
</body>
</html>
