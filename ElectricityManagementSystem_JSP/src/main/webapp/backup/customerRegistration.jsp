<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css\c_registration.css">
</head>
<body>

	<div class="main-registration-card">
		<h1 class="main-heading">Register User</h1>

		<form id="registrationForm" method="post" action="CustomerServlet">
			<h3 class="section-heading">Consumer Details</h3>
			<hr>
			<div class="form-group">
				<label>Consumer Number: <span class="required">*</span></label> <input
					type="text" id="consumerID" placeholder="13-digit Consumer Number"
					maxlength="13" required>
			</div>
			<div class="form-group">
				<label>Bill Number: <span class="required">*</span></label> <input
					type="text" id="billNumber" placeholder="Last 5 digits"
					maxlength="5" required>
			</div>

			<div class="form-group">

				<label>Mobile Number: <span class="required">*</span></label>

				<div style="display: flex; align-items: center;">

					<select id="countryCode"
						style="width: 90px; height: 100%; border: 1px solid #ddd; border-radius: 5px;">

						<option value="+91" selected>+91 (India)</option>

						<option value="+1">+1 (US)</option>

						<option value="+61">+61 (Australia)</option>

					</select> <input type="text" id="mynumber" placeholder="10-digit Mobile No"
						maxlength="10" required
						style="flex-grow: 1; margin-left: 5px; border: 1px solid #ddd; border-radius: 5px; padding: 8px;">

				</div>

			</div>


			<h3 class="section-heading">Login Details</h3>
			<hr>
			<div class="form-group">
				<label>User ID: <span class="required">*</span></label> <input
					type="text" id="UserId" placeholder="User ID" maxlength="5"
					required>
			</div>
			<div class="form-group">
				<label>Password: <span class="required">*</span></label> <input
					type="password" id="password" placeholder="Password" required>
			</div>
			<div class="form-group">
				<label>Confirm Password: <span class="required">*</span></label> <input
					type="password" id="confirmPassword" placeholder="Confirm Password"
					required>
			</div>

			<div class="button-container">
				<button type="button" class="button button-reset"
					onclick="resetForm()">Reset</button>
				<button type="button" class="button button-register"
					onclick="register()">Register</button>
			</div>
		</form>

		<div id="acknowledgment">
			<div class="success-header">Registration Successful</div>
			<div class="acknowledgment-content">
				<p>
					Customer ID: <span id="customerId"></span>
				</p>
				<p>
					Customer Name: <span id="customerName"></span>
				</p>
				<p>
					Mobile Number: <span id="customerMobile"></span>
				</p>
				<a href="login_home.html"><button class="back-btn">Back
						to Login</button></a>
			</div>
		</div>
	</div>
	<script src="js\c_registration.js"></script>
</body>
</html>
