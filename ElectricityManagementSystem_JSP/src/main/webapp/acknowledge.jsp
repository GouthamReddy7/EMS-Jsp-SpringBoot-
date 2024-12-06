<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="ISO-8859-1">

<title>Registration Success</title>

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

h3 {
	font-size: 1.6em;
	color: #444444;
	margin-bottom: 20px;
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

.login-link {
	margin-top: 20px;
}

.login-link a {
	color: #007acc;
	font-weight: bold;
	text-decoration: none;
}

.login-link a:hover {
	text-decoration: underline;
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

<%
String Consumer_id = (String) session.getAttribute("consumerId ");

String Consumer_Name = (String) request.getAttribute("CustomerName");

String Mobile_No = (String) request.getAttribute("mobileNumber");

String Email = (String) request.getAttribute("email");
%>

</head>

<body>

	<div class="container">

		<h2>Registration Successful!</h2>

		<h3>Welcome to the Electricity Management System</h3>

		<div class="info-box">

			<!-- <h6>Your Consumer ID: <spanConsumer_id_id %></span></h6> -->

			<h6>
				Name: <span><%=Consumer_Name%></span>
			</h6>

			<h6>
				Mobile Number: <span><%=Mobile_No%></span>
			</h6>

			<h6>
				Email: <span><%=Email%></span>
			</h6>

		</div>

		<div class="login-link">

			<p>
				Return to the <a href="home.jsp">Login</a> page.
			</p>

		</div>

		<button class="btn-home" onclick="location.href='loginHome.jsp'">Go
			to Login</button>

	</div>

</body>

</html>