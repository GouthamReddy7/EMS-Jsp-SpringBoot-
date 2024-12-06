<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="ISO-8859-1">
<title>Bill Registration Successful</title>
<style>
@import
	url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Montserrat', sans-serif;
	background: #FF416C;
	background: -webkit-linear-gradient(to right, #FF4B2B, #FF416C);
	background: linear-gradient(135deg, #a8e063, #56ab2f);
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
}

.container {
	width: 60%;
	background: #ffffff;
	border-radius: 8px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
	padding: 25px 30px;
	text-align: center;
}

h2 {
	font-size: 2.2em;
	color: #3d85c6;
	margin-bottom: 15px;
}

h6 {
	font-size: 1.1em;
	color: #555;
	margin: 8px 0;
}

.info-box {
	margin: 20px 0;
	padding: 15px;
	background: #f7f9fc;
	border: 1px solid #e4e9f2;
	border-radius: 5px;
}

.info-box span {
	color: #00a676;
	font-weight: bold;
}

.btn-home {
	margin-top: 25px;
	padding: 12px 25px;
	background: #00a676;
	color: #fff;
	font-size: 1em;
	font-weight: 600;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn-home:hover {
	background: #007a5c;
}
</style>
</head>

<body>
	<%
	// Check if the user is authenticated

	String customerName= (String) session.getAttribute("customerName");
	
	String username = (String) session.getAttribute("username");

	Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

	if (isAuthenticated == null || !isAuthenticated) {

		// Redirect to login page if not authenticated

		request.getRequestDispatcher("loginHome.jsp").forward(request, response);

	}
	%>
	<div class="container">
		<h2>Bill Registration Successful!</h2>
		<div class="info-box">
			<h6>
				Consumer ID:
				<%= request.getAttribute("consumerId") %></h6>
			<h6>
				Due Amount:
				<%= request.getAttribute("dueAmount") %></h6>
			<h6>
				Month:
				<%= request.getAttribute("month") %></h6>
		</div>
		<button class="btn-home" onclick="location.href='adminHome.jsp'">Go
			Back</button>
	</div>
</body>

</html>
