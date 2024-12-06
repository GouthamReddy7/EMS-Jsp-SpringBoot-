<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/c_registration.css">
</head>
<body>
	<div class="main-registration-card">
		<h1 class="main-heading">Register User</h1>

		<form id="registrationForm" method="post" action="CustomerServlet"
			onsubmit="return validateForm()">

			<h3 class="section-heading">Consumer Details</h3>
			<hr>
			<div class="form-group">
				<label>Consumer Number: <span class="required">*</span></label>
				<input type="text" id="consumerID" name="consumerID" placeholder="13-digit Consumer Number"
					maxlength="13" required>
			</div>
			<div class="form-group">
				<label>Bill Number: <span class="required">*</span></label>
				<input type="text" id="billNumber" name="billNumber" placeholder="Last 5 digits"
					maxlength="5" required>
			</div>

			<div class="form-group">
				<label for="mobileNumber">Mobile Number: <span class="required">*</span></label>
				<div style="display: flex; align-items: center;">
					<select id="mobileCode" name="mobileCode"
						style="width: 90px; height: 100%; border: 1px solid #ddd; border-radius: 5px;">
						<option value="+91" selected>+91 (India)</option>
						<option value="+1">+1 (US)</option>
						<option value="+61">+61 (Australia)</option>
					</select>
					<input type="text" id="mobileNumber" name="mobileNumber" placeholder="10-digit Mobile No"
						maxlength="10" required
						style="flex-grow: 1; margin-left: 5px; border: 1px solid #ddd; border-radius: 5px; padding: 8px;">
				</div>
			</div>

			<h3 class="section-heading">Login Details</h3>
			<hr>
			<div class="form-group">
				<label>User ID: <span class="required">*</span></label>
				<input type="text" id="userID" name="userID" placeholder="User ID" maxlength="20" required>
			</div>
			<div class="form-group">
				<label>Password: <span class="required">*</span></label>
				<input type="password" id="password" name="password" placeholder="Password" minlength="6" required>
			</div>
			<div class="form-group">
				<label>Confirm Password: <span class="required">*</span></label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password"
					minlength="6" required>
			</div>

			<div class="button-container">
				<button type="button" class="button button-reset" onclick="resetForm()">Reset</button>
				<button type="submit" class="button button-register" name="action" value="register">Register</button>
			</div>
		</form>

		<div id="acknowledgment" style="display: none;">
			<div class="success-header">Registration Successful</div>
			<div class="acknowledgment-content">
				<p>
					Customer ID: <span id="ackCustomerId"></span>
				</p>
				<p>
					Customer Name: <span id="ackCustomerName"></span>
				</p>
				<p>
					Mobile Number: <span id="ackMobileNumber"></span>
				</p>
				<a href="loginHome.jsp"><button class="back-btn">Back to Login</button></a>
			</div>
		</div>
	</div>

	<script src="js/c_registration.js"></script>
</body>
</html>
