<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Web Page</title>
<link rel="stylesheet" href="css\login_home.css">
</head>

<body>

	<h2>Electricity Management System: Portal</h2>
	<div class="container" id="container">
		<div class="form-container sign-up-container">

			<form onsubmit="saveData(); return false;">
				<!--  <h1>Create Account</h1>
				<div class="form-row">
					<select id="title" required>
						<option value="">--Select Title--</option>
						<option value="Mr">Mr</option>
						<option value="Mrs">Mrs</option>
						<option value="Ms">Ms</option>
					</select> <input type="text" id="customerName" placeholder="Name" required />
				</div>
				<input type="email" id="email" placeholder="Email" required /> -->
				<button type="submit">Sign Up</button>
			</form>

		</div>


		<div class="form-container sign-in-container">
			<form action="LoginServlet" method="POST">

				<h1>Sign in</h1>

				<input type="email" name="email" placeholder="Email" required /> <input
					type="password" name="password" placeholder="Password" required />

				<a href="forgotPassword.jsp">Forgot your password?</a>

				<button type="submit">Sign In</button>

			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Welcome Back!</h1>
					<p>Log in to manage your electricity bills and account details</p>
					<button class="ghost" id="signIn">Sign In</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Hello, Friend!</h1>
					<p>Register to access your electricity account and view your
						bill</p>
					<button class="ghost" id="signUp" onclick="window.location.href='register.jsp';">Sign Up</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<!-- Link to the JavaScript file -->
	<script src="js\home_script.js"></script>
</body>
</html>