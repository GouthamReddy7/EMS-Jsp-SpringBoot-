<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="ISO-8859-1">
<title>Operation Failed</title>

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
	background: #FF4B2B;
	background: -webkit-linear-gradient(to right, #FF416C, #FF4B2B);
	background: linear-gradient(to right, #FF416C, #FF4B2B);
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
	color: #d9534f;
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
	background: #fce4e4;
	border: 1px solid #f5c6cb;
	border-radius: 5px;
}

.info-box span {
	color: #d9534f;
	font-weight: bold;
}

.error-message {
	font-size: 1.2em;
	color: #d9534f;
	font-weight: bold;
}

.btn-home {
	margin-top: 25px;
	padding: 12px 25px;
	background: #d9534f;
	color: #fff;
	font-size: 1em;
	font-weight: 600;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn-home:hover {
	background: #c9302c;
}
</style>

</head>

<body>

	<div class="container">

		<h2>Operation Failed!</h2>

		<div class="info-box">
			<h6 class="error-message">
				Error:
				<%=request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage")
		: "An unexpected error occurred. Please try again later."%>
			</h6>
		</div>

		<button class="btn-home" onclick="location.href='adminHome.jsp'">Go Back</button>

	</div>

</body>

</html>
